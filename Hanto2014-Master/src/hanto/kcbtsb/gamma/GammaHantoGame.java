/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoBasePiece;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoMove;
import hanto.kcbtsb.common.HantoPieceFactory;

/**
 * 
 *
 */
public class GammaHantoGame extends HantoBaseGame {
	HantoGameManager gameManager;
	
	public GammaHantoGame(HantoPlayerColor color){
		super(color);
		gameManager = HantoGameManager.getInstance();
		gameManager.setGame(this);
		gameManager.addPieceToLineup(HantoPieceType.SPARROW, 5);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.setUp();
		HantoPieceFactory.setUp(HantoMove.WALK, HantoMove.WALK, HantoMove.WALK);
	}
	
	/**
	 * @see HantoBaseGame.postCheck
	 */
	@Override
	protected MoveResult postCheck(HantoPieceType pieceType){
		
		MoveResult mr = super.postCheck(pieceType);
		if(mr == MoveResult.OK && gameManager.getTurnCount() >= 20){
			mr = MoveResult.DRAW;
		}
		
		return mr;
	}
	/**
	 * @see HantoBaseGame.getPrintableBoard
	 */
	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}
}
