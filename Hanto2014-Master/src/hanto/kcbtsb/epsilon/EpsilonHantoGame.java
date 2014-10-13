package hanto.kcbtsb.epsilon;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoMove;
import hanto.kcbtsb.common.HantoPieceFactory;

public class EpsilonHantoGame extends HantoBaseGame {

	HantoGameManager gameManager;
	
	public EpsilonHantoGame(HantoPlayerColor color) {
		super(color);
		gameManager = HantoGameManager.getInstance();
		gameManager.setGame(this);
		gameManager.addPieceToLineup(HantoPieceType.HORSE, 4, HantoMove.JUMP, Integer.MAX_VALUE);
		gameManager.addPieceToLineup(HantoPieceType.CRAB, 6, HantoMove.WALK, 1);
		gameManager.addPieceToLineup(HantoPieceType.SPARROW, 2, HantoMove.FLY, 4);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1, HantoMove.WALK, 1);
		gameManager.setUp();
	}
	
	/**
	 * <h2>Base Hanto Pre-Check </h2>
	 * <p>Performs the checks required before moving or placing a Hanto Piece.<p> 
	 * @param from coordinate
	 * @param to coordinate
	 * @throws HantoException
	 */
	@Override
	protected void preCheck(HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		if (from == null && to == null){
			System.out.println("Checking for moves left.");
			if(gameManager.getCellManager().isLegalMovePresent(gameManager.getPlayerTurn())){
				throw new HantoException("Forfeited when legal moves were present.");
			}
		}
		super.preCheck(from, to);
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}


}
