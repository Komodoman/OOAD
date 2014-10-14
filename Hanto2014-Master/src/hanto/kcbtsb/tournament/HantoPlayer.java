package hanto.kcbtsb.tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.HantoGameFactory;
import hanto.kcbtsb.common.HantoBasePlayer;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

public class HantoPlayer implements HantoGamePlayer {
	
	private HantoGame tournyGame;
	
	private HantoPlayerColor myColor;
	
	private HantoBasePlayer myPlayer;
	
	private HantoGameManager gameManager;
	
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		gameManager = HantoGameManager.getInstance();
		this.myColor = myColor;
		tournyGame = setupGame(doIMoveFirst, version);
	}

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		HantoMoveRecord responseMove = null;
		if (opponentsMove == null){
			try {
				responseMove = findRandomMove();
			} catch (HantoException e) {
				e.printStackTrace();
			}
		} else{
			try {
				tournyGame.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
				responseMove = findRandomMove();
			} catch (HantoException e) {
				e.printStackTrace();
			}
		}
		try {
			tournyGame.makeMove(responseMove.getPiece(), responseMove.getFrom(), responseMove.getTo());
		} catch (HantoException e) {
			e.printStackTrace();
		}
		return responseMove;
	}
	
	
	private HantoMoveRecord findRandomMove() throws HantoException{
		HantoMoveRecord aMove = null;
		HantoCoordinate to = null;
		List<HantoCell> possCells = gameManager.getCellManager().generatePossibleMoves();
		List<HantoMoveRecord> possMoves = getPossMoves(possCells);
		
		
		if (gameManager.getTurnCount() == 1 || gameManager.getTurnCount() == 2){
			// place a butterfly
			// if more than one possible move, place randomly
			// else place in one possible location
			if (possCells.size() > 1){
				to = possCells.get(randInt(0, possCells.size() - 1));
			} else {
				to = possCells.get(0);
			}
			aMove = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, to);
		}
		else if (arePiecesLeftInLineup() && possMoves.isEmpty() == false){
			// Flip a coin, 0 - place a piece, 1 - move a piece
			if (randInt(0,1) == 0){
				// place a piece
			} else {
				// move a piece
				aMove = possMoves.get(randInt(0, possMoves.size() - 1));
			}	
		} else if (arePiecesLeftInLineup()) {
			// place a piece
		} else if (possMoves.isEmpty() == false){
			// move a piece
			aMove = possMoves.get(randInt(0, possMoves.size() - 1));
		} else {
			// forfeit 
			aMove = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, null);
		}	
		
		tournyGame.makeMove(aMove.getPiece(), aMove.getFrom(), aMove.getTo());
		return aMove;
	}
	
	private List<HantoMoveRecord> getPossMoves(List<HantoCell> openCells){
		List<HantoMoveRecord> possibleMoves = new ArrayList<HantoMoveRecord>();
		List<HantoCell> boardPieces = gameManager.getCellManager().getPlayerCells(myColor);
		for (int i = 0; i < boardPieces.size(); i++){
			for (int j = 0; j < openCells.size(); j++){
				if (gameManager.getCellManager().isALegalMove(boardPieces.get(i).getPiece(), boardPieces.get(i), openCells.get(j))){
					possibleMoves.add(new HantoMoveRecord(boardPieces.get(i).getPiece().getType(), boardPieces.get(i), openCells.get(j)));
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
	
	
	private HantoGame setupGame(boolean doIMoveFirst, HantoGameID version){
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

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
