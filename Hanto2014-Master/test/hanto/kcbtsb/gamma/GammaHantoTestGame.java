package hanto.kcbtsb.gamma;

import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoPieceFactory;

import common.HantoTestGame;
import common.HantoTestGameFactory;

public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame {

	public GammaHantoTestGame(HantoPlayerColor firstTurnColor) {
		super(firstTurnColor);
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		// TODO Auto-generated method stub
		for(int i = 0; i < initialPieces.length; i++)
		{
			PieceLocationPair pair = initialPieces[i];
			HantoPiece piece = HantoPieceFactory.makeHantoPiece(pair.pieceType, pair.player);
			gameManager.getCellManager().addCell(pair.location.getX(), pair.location.getY(),piece);
			switch(pair.player)
			{
				case BLUE:
					gameManager.getBluePlayer().removePieceFromLineup(pair.pieceType);
				case RED:
					gameManager.getRedPlayer().removePieceFromLineup(pair.pieceType);
			}
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		super.gameManager.setTurnCount(turnNumber);
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		// TODO Auto-generated method stub
		gameManager.setColorTurn(player);
	}

}
