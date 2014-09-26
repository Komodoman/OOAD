/**
 * G
 */
package hanto.kcbtsb.gamma;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoGameManager;

/**
 * 
 * @author kcbryant
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
	}
	
	@Override
	protected MoveResult postCheck(HantoPieceType pieceType){
		
		MoveResult mr = super.postCheck(pieceType);
		if(mr == MoveResult.OK && gameManager.getTurnCount() >= 20){
			mr = MoveResult.DRAW;
		}
		
		return mr;
	}
}
