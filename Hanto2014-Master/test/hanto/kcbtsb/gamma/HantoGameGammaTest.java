package hanto.kcbtsb.gamma;

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
import hanto.common.HantoException;

public class HantoGameGammaTest {
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
			game = (GammaHantoTestGame) factory.makeHantoTestGame(HantoGameID.GAMMA_HANTO);
		}
		
		@Test
		public void createGammaHantoGame()
		{
			assertNotNull(HantoGameFactory.makeHantoGame(HantoGameID.GAMMA_HANTO));
		}
		@Test
		public void bluePlacesInitialHantoPieceAtOrigin() throws HantoException
		{
			final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			assertEquals(MoveResult.OK, mr);
			final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
			assertEquals(HantoPlayerColor.BLUE, p.getColor());
			assertEquals(HantoPieceType.BUTTERFLY, p.getType());
		}
		
		@Test
		public void redPlacesInitialHantoPieceAtOrigin() throws HantoException
		{	
			game = (GammaHantoTestGame) factory.makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.RED);
			final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			assertEquals(MoveResult.OK, mr);
			final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
			assertEquals(HantoPlayerColor.RED, p.getColor());
			assertEquals(HantoPieceType.BUTTERFLY, p.getType());
		}
		
		@Test
		public void gameShouldDrawAfterTwentyTurns() throws HantoException
		{	
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 1));
			game.setTurnNumber(19);
			final MoveResult mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -1));
			assertEquals(MoveResult.DRAW, mr);
		}
		
		@Test
		public void blueShouldWalkWithSparrow() throws HantoException
		{	
			System.out.println("BEGIN TEST_______________");
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, 2));
			MoveResult mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(0, -1), makeCoordinate(1, -1));
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
			game.makeMove(HantoPieceType.SPARROW, makeCoordinate(0, 1), makeCoordinate(-1, 0));
		}
		
		@Test(expected=HantoException.class)
		public void blueShouldFailWalkIntoNonContiguousRegion() throws HantoException {
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, -1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, 1));
			game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -2));
			game.makeMove(HantoPieceType.SPARROW, makeCoordinate(0, 1), makeCoordinate(0, 2));
		}
		@Test(expected=HantoException.class)
		public void moveshouldBeIlligal() throws HantoException{
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

