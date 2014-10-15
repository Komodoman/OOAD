package hanto.studentkcbtsb.tournament;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoPieceFactory;
import hanto.studentkcbtsb.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;



public class HantoPlayerTest {

	@Test
	public void blueMakesFirstMove() throws HantoException
	{
		HantoGameManager.clearInstance();
		HantoPlayer bluePlayer = new HantoPlayer();
		bluePlayer.RANDOM_SEED = 5;
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		bluePlayer.makeMove(null);
	}
	@Test
	public void lineupIsEmpty() throws HantoException
	{
		HantoGameManager.clearInstance();
		TournamentTestPlayer bluePlayer = new TournamentTestPlayer();
		bluePlayer.RANDOM_SEED = 5;
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		assertTrue("No pieces left: ", bluePlayer.testLineupChecker());
	}
	@Test
	public void bothPlayersPlaceButterfly() throws HantoException
	{	
		HantoGameManager.clearInstance();
		HantoPlayer bluePlayer = new HantoPlayer();
		bluePlayer.RANDOM_SEED = 5;
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoMoveRecord r = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCell(0,0));
		bluePlayer.makeMove(r);
		
	}
	@Test
	public void bluePlayerGoesSecond() throws HantoException
	{	
		HantoGameManager.clearInstance();
		HantoPlayer bluePlayer = new HantoPlayer();
		bluePlayer.RANDOM_SEED = 5;
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
		HantoMoveRecord r = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCell(0,0));
		HantoMoveRecord blueMove = bluePlayer.makeMove(r);
	}
	@Test
	public void randomGen()
	{
		assertNotNull(HantoPlayer.randInt(0,5));
		assertEquals(HantoPlayer.randInt(0, -1), 0);
		HantoPlayer.RANDOM_SEED = Integer.MAX_VALUE;
		assertNotNull(HantoPlayer.randInt(0,5));
		
	}
	
	@Test
	public void bothPlayersPlaceButterflyAndContinueSeed5() throws HantoException
	{	
		HantoGameManager.clearInstance();
		HantoPlayer bluePlayer = new HantoPlayer();
		HantoPlayer redPlayer = new HantoPlayer();
		bluePlayer.RANDOM_SEED = 5;
		bluePlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		redPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord b = bluePlayer.makeMove(null);
		if (b.getFrom() != null){
			assertEquals(true, redPlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(b.getPiece(), HantoPlayerColor.BLUE), (HantoCell) b.getFrom(), (HantoCell) b.getTo()));
		}
		HantoMoveRecord r = redPlayer.makeMove(b);
		if (b.getFrom() != null){
			assertEquals(true, bluePlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(r.getPiece(), HantoPlayerColor.RED), (HantoCell) r.getFrom(), (HantoCell) r.getTo()));
		}
		b = bluePlayer.makeMove(r);
		if (b.getFrom() != null){
			assertEquals(true, redPlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(b.getPiece(), HantoPlayerColor.BLUE), (HantoCell) b.getFrom(), (HantoCell) b.getTo()));
		}
		r = redPlayer.makeMove(b);
		if (b.getFrom() != null){
			assertEquals(true, bluePlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(r.getPiece(), HantoPlayerColor.RED), (HantoCell) r.getFrom(), (HantoCell) r.getTo()));
		}
		b = bluePlayer.makeMove(r);
		if (b.getFrom() != null){
			assertEquals(true, redPlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(b.getPiece(), HantoPlayerColor.BLUE), (HantoCell) b.getFrom(), (HantoCell) b.getTo()));
		}
		r = redPlayer.makeMove(b);
		if (b.getFrom() != null){
			assertEquals(true, bluePlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(r.getPiece(), HantoPlayerColor.RED), (HantoCell) r.getFrom(), (HantoCell) r.getTo()));
		}
		b = bluePlayer.makeMove(r);
		if (b.getFrom() != null){
			assertEquals(true, redPlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(b.getPiece(), HantoPlayerColor.BLUE), (HantoCell) b.getFrom(), (HantoCell) b.getTo()));
		}
		r = redPlayer.makeMove(b);
		if (b.getFrom() != null){
			assertEquals(true, bluePlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(r.getPiece(), HantoPlayerColor.RED), (HantoCell) r.getFrom(), (HantoCell) r.getTo()));
		}
		b = bluePlayer.makeMove(r);
		if (b.getFrom() != null){
			assertEquals(true, redPlayer.getManager().getCellManager().isALegalMove((HantoPiece) HantoPieceFactory.makeHantoPiece(b.getPiece(), HantoPlayerColor.BLUE), (HantoCell) b.getFrom(), (HantoCell) b.getTo()));
		}
		
		HantoMoveRecord randMove = null;
		List<HantoCell> possCells = new ArrayList<HantoCell>();
		possCells.addAll(redPlayer.getManager().getCellManager().generatePossibleMoves());
		randMove = redPlayer.placeRandomPiece(possCells);
		assertNotNull(randMove);
		assertNotNull(redPlayer.getGame());
		assertNotNull(redPlayer.placeFailMoveCheck());
		assertNotNull(redPlayer.getColor());
		assertNotNull(redPlayer.getPlayer());
		
	}
	

}
