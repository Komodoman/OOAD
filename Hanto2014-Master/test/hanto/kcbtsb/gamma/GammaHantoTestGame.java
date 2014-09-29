package hanto.kcbtsb.gamma;

import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.common.HantoBaseGame;

import common.HantoTestGame;

public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame {

	public GammaHantoTestGame(HantoPlayerColor firstTurnColor) {
		super(firstTurnColor);
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
