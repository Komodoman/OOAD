/**
 * @author Kyle Bryant and Tim Bujnevicie
 */

package hanto.kcbtsb.epsilon;



import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoMove;


/**
 * Epsilon realization of BaseHantoGame
 * @author tsbujnevicie
 *
 */
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

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}


}
