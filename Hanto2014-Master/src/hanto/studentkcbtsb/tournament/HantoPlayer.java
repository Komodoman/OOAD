/**
 * @author Kyle Bryant and Tim Bujnevicie
 */

package hanto.studentkcbtsb.tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.HantoGameFactory;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoBasePlayer;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * Hanto Player AI to play hanto Games
 * @author tsbujnevicie
 *
 */
public class HantoPlayer implements HantoGamePlayer {
	
	public static int RANDOM_SEED = Integer.MAX_VALUE;
	
	protected HantoGame tournyGame;
	
	protected HantoPlayerColor myColor;
	
	protected HantoBasePlayer myPlayer;
	
	protected HantoGameManager gameManager;
	
	
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		HantoGameManager.clearInstance();
		this.myColor = myColor;
		tournyGame = setupGame(doIMoveFirst, version);
		
	}

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		HantoMoveRecord responseMove = null;
		if (opponentsMove == null){
			responseMove = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCell(0, 0));
			try {
				tournyGame.makeMove(responseMove.getPiece(), responseMove.getFrom(), responseMove.getTo());
			} catch (HantoException e) {
				e.printStackTrace();
			}
		} 
		else{
			
				try {
					tournyGame.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
					responseMove = findRandomMove();
					tournyGame.makeMove(responseMove.getPiece(), responseMove.getFrom(), responseMove.getTo());
				} 
				catch (HantoException e) {
					e.printStackTrace();
				}
		} 
		
		return responseMove;
	}
	
	/**
	 * Finds a random move to play
	 * @return
	 * 		random HantoMoveRecord
	 */
	protected HantoMoveRecord findRandomMove(){
		HantoMoveRecord aMove = null;
		List<HantoCell> possCells = new ArrayList<HantoCell>();
		possCells.addAll(gameManager.getCellManager().generatePossibleMoves());
		List<HantoMoveRecord> possMoves = getPossMoves(possCells);
		
		if (gameManager.getTurnCount() == 1){
			aMove = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCell(0, 1));
		}
		else if (arePiecesLeftInLineup() && !possMoves.isEmpty()){
			// Flip a coin, 0 - place a piece, 1 - move a piece
			if (randInt(0, 1) == 0){
				// place a piece
				aMove = placeRandomPiece(possCells);
			} else {
				// move a piece
				aMove = possMoves.get(randInt(0, possMoves.size() - 1));
			}	  
		} else if (arePiecesLeftInLineup()) {
			// place a piece
			aMove = placeRandomPiece(possCells);
		} else if (!possMoves.isEmpty()){
			// move a piece
			aMove = possMoves.get(randInt(0, possMoves.size() - 1));
		} else {
			// forfeit 
			aMove = new HantoMoveRecord(null, null, null);
		}	
		return aMove;
	}
	
	private HantoMoveRecord placeFailMoveCheck()
	{
		HantoMoveRecord move = null;
		List<HantoCell> possCells = new ArrayList<HantoCell>();
		possCells.addAll(gameManager.getCellManager().generatePossibleMoves());
		List<HantoMoveRecord> possMoves = getPossMoves(possCells);
		
		if(!possMoves.isEmpty())
		{
			move = possMoves.get(randInt(0, possMoves.size() - 1));
		}
		else{
			move = new HantoMoveRecord(null, null, null);
		}
		
		return move;
	}
	
	private HantoMoveRecord placeRandomPiece(List<HantoCell> possCells){
		HantoMoveRecord move = null;
		List<HantoCell> goodMoves = new ArrayList<HantoCell>();
		HantoPieceType chosenPiece = myPlayer.getPiecesRemaining().get(randInt(0, myPlayer.getPieceCount() - 1));
		for (int i =0; i < possCells.size(); i++){
			if (!gameManager.getCellManager().isAdjacentToEnemy(possCells.get(i), myColor)){
				goodMoves.add(possCells.get(i));
			} 
		}
		if(goodMoves.isEmpty()){
			move = placeFailMoveCheck();
		}
		else{
			move = new HantoMoveRecord(chosenPiece, null, goodMoves.get(randInt(0, goodMoves.size() - 1)));
		}
		
		return move;
	}
	
	private List<HantoMoveRecord> getPossMoves(List<HantoCell> openCells){
		List<HantoMoveRecord> possibleMoves = new ArrayList<HantoMoveRecord>();
		List<HantoCell> boardPieces = new ArrayList<HantoCell>();
		if (gameManager.getCellManager().getPlayerCells(myColor) != null){
			boardPieces.addAll(gameManager.getCellManager().getPlayerCells(myColor));
			for (int i = 0; i < boardPieces.size(); i++){
				for (int j = 0; j < openCells.size(); j++){
					if (gameManager.getCellManager().isALegalMove(boardPieces.get(i).getPiece(), boardPieces.get(i), openCells.get(j))){
						possibleMoves.add(new HantoMoveRecord(boardPieces.get(i).getPiece().getType(), boardPieces.get(i), openCells.get(j)));
					}
				}
			}
		}
		return possibleMoves;
	}
	
	private boolean arePiecesLeftInLineup(){
		boolean piecesLeft = true;
		if (myPlayer.getPiecesRemaining().isEmpty()){
			piecesLeft = false;
		}
		return piecesLeft;
	}
	
	/**
	 * Sets up game for player
	 * @param doIMoveFirst
	 * @param version
	 * @return
	 * 		game instance
	 */
	protected HantoGame setupGame(boolean doIMoveFirst, HantoGameID version){
		HantoGame aGame = null;
		if (doIMoveFirst){
			aGame = HantoGameFactory.makeHantoGame(version, myColor);
		} else {
			switch(myColor){
			case RED:
				aGame = HantoGameFactory.makeHantoGame(version, HantoPlayerColor.BLUE);
				break;
			case BLUE:
				aGame = HantoGameFactory.makeHantoGame(version, HantoPlayerColor.RED);
				break;
			}
		}
		
		gameManager = ((HantoBaseGame) aGame).getManager();
		
		switch(myColor){
		case RED:
			myPlayer = gameManager.getRedPlayer();
			break;
		case BLUE:
			myPlayer = gameManager.getBluePlayer();
			break;
		}
		return aGame;
		
	}
	
	public HantoBaseGame getGame(){
		return (HantoBaseGame) tournyGame;
	}
	
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		int randomNum;
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
		Random rand = new Random();
		if (RANDOM_SEED != Integer.MAX_VALUE){
			rand = new Random(RANDOM_SEED);
		} 
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
		if(max < 0){
			randomNum = 0;
		}
		else{
			randomNum = rand.nextInt((max - min) + 1) + min;
		}
	    
	    return randomNum;
	}
	
	public HantoGameManager getManager()
	{
		return gameManager;
	}

}
