/**
 * 
 */
package hanto.kcbtsb.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.common.HantoException;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoCellManager;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoPieceFactory;
import hanto.kcbtsb.common.HantoBasePlayer;
import hanto.kcbtsb.common.HantoPlayerTurn;

/**
 * @author Kyle
 *
 */
public class HantoGameBeta extends HantoBaseGame {
	HantoGameManager gameManager;
	
	/**
	 * Constructor for HantoGameBeta.
	 */
	public HantoGameBeta(HantoPlayerColor color){
		super(color);
		gameManager = HantoGameManager.getInstance();
		gameManager.setGame(this);
		gameManager.addPieceToLineup(HantoPieceType.SPARROW, 5);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.setUp();
	}
	
	@Override
	protected void preCheck(HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		if (from != null){
			throw new HantoException("Can't make moves in Beta Hanto");
		}
		
		super.preCheck(from, to);
	}

	/**
	 * @see HantoBaseGame.movePiece
	 */
	@Override
	protected void movePiece(HantoCoordinate from, HantoCoordinate to, HantoPieceType pieceType) throws HantoException{
		if (!getCurrentPlayer().getPiecesRemaining().contains(pieceType)){
			throw new HantoException("None of those pieces remaining");
		}
		gameManager.getCellManager().addCell(to.getX(), to.getY(), HantoPieceFactory.makeHantoPiece(pieceType, gameManager.getPlayerTurn()));
	}
	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
