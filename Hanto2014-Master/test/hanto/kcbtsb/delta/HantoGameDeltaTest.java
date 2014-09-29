package hanto.kcbtsb.delta;

import static org.junit.Assert.*;

import org.junit.*;

import common.HantoTestGame;
import common.HantoTestGameFactory;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.HantoGameFactory;
import hanto.kcbtsb.common.HantoCell;

public class HantoGameDeltaTest {
		/**
		 * Internal class for these test cases.
		 * @version Sep 13, 2014
		 */
		class TestHantoCoordinate implements HantoCoordinate
		{
			private final int x, y;
			
			public TestHantoCoordinate(int x, int y)
			{
				this.x = x;
				this.y = y;
			}
			/*
			 * @see hanto.common.HantoCoordinate#getX()
			 */
			@Override
			public int getX()
			{
				return x;
			}

			/*
			 * @see hanto.common.HantoCoordinate#getY()
			 */
			@Override
			public int getY()
			{
				return y;
			}

		}
		
		private static HantoTestGameFactory factory;
		private HantoTestGame game;
		
		@BeforeClass
		public static void initializeClass()
		{
			factory = HantoTestGameFactory.getInstance();
		}
		
		@Before
		public void setup()
		{
			// By default, blue moves first.
			game = factory.makeHantoTestGame(HantoGameID.DELTA_HANTO);
		}
		
		@Test
		public void bluePlacesInitialHantoPieceAtOrigin() throws HantoException
		{
			final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			assertEquals(MoveResult.OK, mr);
			final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
			System.out.println(p);
			assertEquals(HantoPlayerColor.BLUE, p.getColor());
			assertEquals(HantoPieceType.BUTTERFLY, p.getType());
		}
		
		@Test
		public void redPlacesInitialHantoPieceAtOrigin() throws HantoException
		{	
			game = (DeltaHantoTestGame) factory.makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
			final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			assertEquals(MoveResult.OK, mr);
			final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
			assertEquals(HantoPlayerColor.RED, p.getColor());
			assertEquals(HantoPieceType.BUTTERFLY, p.getType());
		}
		
		@Test
		public void gameShouldNotDrawAfterTwentyTurns() throws HantoException
		{	
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 1));
			game.setTurnNumber(19);
			final MoveResult mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -1));
			assertEquals(MoveResult.OK, mr);
		}
		
		@Test
		public void blueShouldWalkWithSparrow() throws HantoException
		{	
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, 2));
			MoveResult mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
			assertEquals(MoveResult.OK, mr);
		}
		
		@Test(expected=HantoException.class)
		public void blueShouldFailPlaceSparrowNearEnemies() throws HantoException {
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, -1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -2));
		}
		
		@Test(expected=HantoException.class)
		public void blueShouldFailWalkMoreThanOneCellWithSparrow() throws HantoException {
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, -1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, 1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -2));
			game.makeMove(HantoPieceType.SPARROW, makeCoordinate(0, 1), makeCoordinate(1, -1));
		}
		
		@Test(expected=HantoException.class)
		public void blueShouldFailWalkIntoNonContiguousRegion() throws HantoException {
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, -1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, 1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -2));
			game.makeMove(HantoPieceType.SPARROW, makeCoordinate(0, 1), makeCoordinate(0, 2));
		}

		// Helper methods
		private HantoCoordinate makeCoordinate(int x, int y)
		{
			return new TestHantoCoordinate(x, y);
		}
	}

