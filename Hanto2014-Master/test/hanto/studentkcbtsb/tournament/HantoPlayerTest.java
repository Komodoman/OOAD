package hanto.studentkcbtsb.tournament;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.studentkcbtsb.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
	}

}
