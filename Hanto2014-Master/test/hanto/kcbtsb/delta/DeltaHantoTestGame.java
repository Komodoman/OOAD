package hanto.kcbtsb.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoPieceFactory;
import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;

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
		gameManager.setColorTurn(player);
	}

}
