package hanto.kcbtsb.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import common.HantoTestGame;

public class DeltaHantoTestGame extends DeltaHantoGame implements HantoTestGame {

	public DeltaHantoTestGame(HantoPlayerColor firstTurnColor) {
		super(firstTurnColor);
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTurnNumber(int turnNumber) {
		super.gameManager.setTurnCount(turnNumber);
	}


	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		// TODO Auto-generated method stub

	}

}
