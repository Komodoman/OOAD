/**
 * @author Kyle Bryant and Tim Bujnevicie
 */

package hanto.kcbtsb.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoMove;
import hanto.kcbtsb.common.HantoPieceFactory;

/**
 * Delta Implementation of the HantoBaseGame
 * @author tsbujnevicie
 *
 */
public class DeltaHantoGame extends HantoBaseGame {

	HantoGameManager gameManager;
	
	boolean gameOver = false;
	
	public DeltaHantoGame(HantoPlayerColor firstTurnColor) {
		super(firstTurnColor);
		HantoPieceFactory.setUp(HantoMove.FLY, HantoMove.WALK, HantoMove.WALK);
		gameManager = HantoGameManager.getInstance();
		gameManager.setGame(this);
		gameManager.addPieceToLineup(HantoPieceType.CRAB, 4);
		gameManager.addPieceToLineup(HantoPieceType.SPARROW, 4);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.setUp();
		
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		MoveResult moveResult = null;
		if (from == null && to == null){
			switch(gameManager.getPlayerTurn()){
			case BLUE:
				moveResult = MoveResult.RED_WINS;
				break;
			case RED:
				moveResult = MoveResult.BLUE_WINS;
				break;
			}
		} else {
			moveResult = super.makeMove(pieceType, from, to);
		}
		if (gameOver){
			throw new HantoException("The game is over bro!");
		} else {
			gameOver = isOver(moveResult);
		}
		return moveResult;
	}
	
	private boolean isOver(MoveResult result){
		boolean isGameOver = false;
		switch(result){
		case BLUE_WINS:
			isGameOver = true;
			break;
		case DRAW:
			isGameOver = true;
			break;
		case RED_WINS:
			isGameOver = true;
			break;
		case OK:
			isGameOver = false;
			break;
		}
		return isGameOver;
	}
	
	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
