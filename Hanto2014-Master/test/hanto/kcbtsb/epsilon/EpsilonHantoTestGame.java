package hanto.kcbtsb.epsilon;

import hanto.common.HantoPlayerColor;
import common.HantoTestGame;

public class EpsilonHantoTestGame extends EpsilonHantoGame implements
		HantoTestGame {

	public EpsilonHantoTestGame(HantoPlayerColor firstTurnColor) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		// TODO Auto-generated method stub

	}

}
