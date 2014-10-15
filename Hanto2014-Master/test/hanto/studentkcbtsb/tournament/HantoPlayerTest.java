package hanto.studentkcbtsb.tournament;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

public class HantoPlayerTest {

	@Test
	public void bothPlayersPlaceButterfly() throws HantoException
	{	
		HantoGameManager.clearInstance();
		HantoPlayer bluePlayer = new HantoPlayer();
		bluePlayer.RANDOM_SEED = 5;
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoMoveRecord blueMove = bluePlayer.makeMove(null);
		HantoMoveRecord r = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCell(0,1));
		bluePlayer.makeMove(r);
		r = new HantoMoveRecord(HantoPieceType.HORSE, null, new HantoCell(1, 0));
		bluePlayer.makeMove(r);
		r = new HantoMoveRecord(HantoPieceType.HORSE, null, new HantoCell(0,3));
		bluePlayer.makeMove(r);
		r = new HantoMoveRecord(HantoPieceType.HORSE, null, new HantoCell(0,4));
		bluePlayer.makeMove(r);
		r = new HantoMoveRecord(HantoPieceType.HORSE, null, new HantoCell(0,5));
		bluePlayer.makeMove(r);
		r = new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCell(0,6));
		bluePlayer.makeMove(r);
		r = new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCell(0,7));
		bluePlayer.makeMove(r);
	}
	
}
