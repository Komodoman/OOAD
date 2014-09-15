/**
 * 
 */
package hanto.kcbtsb.alpha;

import java.util.ArrayList;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoCellManager;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoPlayer;
import hanto.kcbtsb.common.HantoPlayerTurn;

/**
 * <p> Realization of the {@link HantoGame} interface specifically for the Alpha version
 * of Hanto.  HantoGameAlpha only initalizes enough code to meet the requirements of Alpha
 * Hanto.  Blue player moves first always, and each player only places one ButterFly.  All
 * games end in a draw.</p>
 * 
 */
public class HantoGameAlpha implements HantoGame {
	
	
	HantoGameManager gameManager;
	
	/**
	 * Constructor for HantoGameAlpha. Initializes and sets up the HantoGameManager singleton
	 */
	public HantoGameAlpha(){
		gameManager = HantoGameManager.getInstance();	
		gameManager.setGameType(this);
		gameManager.setCellManager(new HantoCellManager());
		gameManager.setColorTurn(HantoPlayerTurn.BLUE);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.setBluePlayer(new HantoPlayer(HantoPlayerColor.BLUE));
		gameManager.setRedPlayer(new HantoPlayer(HantoPlayerColor.RED));
		
	}
	
	/**
	 * @see HantoGame.makeMove
	 */
	@Override
	final public MoveResult makeMove(final HantoPieceType pieceType, final HantoCoordinate from,
	final HantoCoordinate to) throws HantoException {
		MoveResult result = MoveResult.OK;
		
		if (gameManager.getCellManager().isEmpty()){
			// check for first move of game
			if (to.getX() == 0 && to.getY() == 0 && pieceType == HantoPieceType.BUTTERFLY){
				gameManager.getCellManager().addCell(to.getX(), to.getY());
			}
			else{
				throw new HantoException("First butterfly must be placed at 0,0");
			}
		}
		
		else if (!isLegalMove(to)){
			throw new HantoException("Not a Legal Move!");
		}
		
		else if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			// throw exception if destination is already occupied
			throw new HantoException("Cell is already occupied");
		}
		
		else{
			// add cell to cell manager
			gameManager.getCellManager().addCell(to.getX(), to.getY());
			if (gameManager.getPlayerTurn() == HantoPlayerColor.RED){
				result = MoveResult.DRAW;
			}
		}
		gameManager.setColorTurn(gameManager.getColorTurn().getNext());
		return result;
	}
	
	/**
	 * <p> Determines whether a move is legal or not in {@link HantoGameAlpha} </p>
	 * @param to
	 * 			the position on the hanto board a piece is moving to.
	 * @return
	 * 			a boolean representing whether the move was legal or not
	 */
	private static boolean isLegalMove(final HantoCoordinate to){
		boolean isLegal = true;
		if (Math.abs(to.getX()) > 1 || Math.abs(to.getY()) > 1){
			isLegal = false;
		}
		return isLegal;
	}

	/**
	 * <p> Currently unimplemented getter for getting {@link HantoPiece}'s from the board </p>
	 * 
	 * @param where
	 * 			the coordinate where the a reference of the HantoPiece will be taken
	 * 
	 * @return will return a HantoPiece given the coordinate in later iterations.  As of
	 * now, it returns null.
	 */
	@Override
	final public HantoPiece getPieceAt(final HantoCoordinate where) {
		return null;
	}
	
	/**
	 * <p> Currently unimplemented utility tool for printing out the current hantoBoard as a string
	 * </p>
	 * @return will return a printed representation of the board in future iterations.
	 */
	@Override
	final public String getPrintableBoard() {
		return null;
	}
	

}
