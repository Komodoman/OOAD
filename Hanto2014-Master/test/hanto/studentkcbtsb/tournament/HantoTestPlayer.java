package hanto.studentkcbtsb.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

public class HantoTestPlayer extends HantoPlayer {
	
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		gameManager = HantoGameManager.getInstance();
		this.myColor = myColor;
		tournyGame = setupGame(doIMoveFirst, version);
	}

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		HantoMoveRecord responseMove = null;
		if (opponentsMove == null){
			responseMove = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCell(0, 0));
		} else{
			try {
				//tournyGame.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
				responseMove = findRandomMove();
			} catch (HantoException e) {
				e.printStackTrace();
			}
		}
		try {
			tournyGame.makeMove(responseMove.getPiece(), responseMove.getFrom(), responseMove.getTo());
		} catch (HantoException e) {
			e.printStackTrace();
		}
		return responseMove;
	}
}
