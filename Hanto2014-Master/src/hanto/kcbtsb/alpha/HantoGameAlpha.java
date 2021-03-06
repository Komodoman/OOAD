/**
 * 
 */
package hanto.kcbtsb.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoPieceFactory;
import hanto.kcbtsb.common.HantoBasePlayer;

/**
 * <p> Realization of the {@link HantoGame} interface specifically for the Alpha version
 * of Hanto.  HantoGameAlpha only initalizes enough code to meet the requirements of Alpha
 * Hanto.  Blue player moves first always, and each player only places one ButterFly.  All
 * games end in a draw.</p>
 * 
 */
public class HantoGameAlpha extends HantoBaseGame {
	
	private HantoGameManager gameManager;
	
	/**
	 * Constructor for HantoGameAlpha. Initializes and sets up the HantoGameManager singleton
	 */
	public HantoGameAlpha(HantoPlayerColor color){
		super(color);
		gameManager = HantoGameManager.getInstance();
		gameManager.setGame(this);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.setUp();
	}
	
	/**
	 * @see HantoGame.makeMove
	 */
	@Override
	final public MoveResult makeMove(final HantoPieceType pieceType, final HantoCoordinate from,
	final HantoCoordinate to) throws HantoException {
		MoveResult result = MoveResult.OK;
		
		if (from != null){
			throw new HantoException("Moving not available");
		}
		
		if (gameManager.getCellManager().isEmpty()){
			// check for first move of game
			if (to.getX() == 0 && to.getY() == 0 && pieceType == HantoPieceType.BUTTERFLY){
				gameManager.getCellManager().addCell(to.getX(), to.getY(), HantoPieceFactory.makeHantoPiece(pieceType, gameManager.getPlayerTurn()));
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
			gameManager.getCellManager().addCell(to.getX(), to.getY(), HantoPieceFactory.makeHantoPiece(pieceType, gameManager.getPlayerTurn()));
			if (gameManager.getPlayerTurn() == HantoPlayerColor.RED){
				result = MoveResult.DRAW;
			}
		}
		HantoGameManager.getInstance().nextTurn();
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
	 * <p> Currently unimplemented utility tool for printing out the current hantoBoard as a string
	 * </p>
	 * @return will return a printed representation of the board in future iterations.
	 */
	@Override
	final public String getPrintableBoard() {
		return null;
	}


}
