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
import hanto.kcbtsb.common.HantoGameManager;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

public class HantoPlayerTest {

	@Test
	public void bothPlayersPlaceButterfly() throws HantoException
	{
		HantoTestPlayer redPlayer = new HantoTestPlayer();
		HantoTestPlayer bluePlayer = new HantoTestPlayer();
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		redPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord blueMove = bluePlayer.makeMove(null);
		System.out.println("IT IS # " + HantoGameManager.getInstance().getTurnCount() + " TURN.");
		HantoMoveRecord redMove = redPlayer.makeMove(blueMove);
		assertEquals(blueMove.getPiece(), HantoPieceType.BUTTERFLY);
		assertEquals(redMove.getPiece(), HantoPieceType.BUTTERFLY);
		
	}
	
	@Test
	public void bothPlayersPlayAWhile() throws HantoException
	{
		HantoTestPlayer redPlayer = new HantoTestPlayer();
		HantoTestPlayer bluePlayer = new HantoTestPlayer();
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		redPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord blueMove = bluePlayer.makeMove(null);
		HantoMoveRecord redMove = redPlayer.makeMove(blueMove);
		blueMove = bluePlayer.makeMove(redMove);
		redMove = redPlayer.makeMove(blueMove);
		blueMove = bluePlayer.makeMove(redMove);
		
	}
}
