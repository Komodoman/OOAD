/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.kcbtsb.epsilon;


import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static hanto.common.MoveResult.*;
import static org.junit.Assert.*;
import hanto.common.*;
import hanto.kcbtsb.HantoGameFactory;
import org.junit.*;

import common.*;
import common.HantoTestGame.PieceLocationPair;

/**
 * Description
 * @version Oct 2, 2014
 */
public class HantoGameEpsilonTest
{
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
	private HantoGame game;
	private HantoTestGame testGame;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoTestGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		testGame = factory.makeHantoTestGame(HantoGameID.EPSILON_HANTO);
		game = testGame;
	}
	
	@Test
	public void bluePlacesButterflyFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
	}
	
	@Test
	public void blueMovesHorseCorrectlyNorthSouth() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		final MoveResult mr = game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 3));
		assertEquals(OK, mr);
	}
	
	@Test(expected=HantoException.class)
	public void blueMovesHorseOvershootNorthSouth() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 4));
	}
	
	@Test(expected=HantoException.class)
	public void blueMovesHorseOvershootNorthSouthWithContinuity() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(CRAB, null, makeCoordinate(-1, 2));
		game.makeMove(CRAB, null, makeCoordinate(-1, 3));
		game.makeMove(CRAB, null, makeCoordinate(-1, 4));
		game.makeMove(CRAB, null, makeCoordinate(-1, 5));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 4));
	}
	
	@Test(expected=HantoException.class)
	public void blueMovesHorseNotStraight() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(-1, 2));
	}
	
	@Test
	public void sparrowFliesOver() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 2));
		assertEquals(OK, mr);
	}
	
	@Test(expected=HantoException.class)
	public void sparrowFliesOverTooFar() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(SPARROW, makeCoordinate(0, -2), makeCoordinate(0, 4));
	}
	
	@Test(expected=HantoException.class)
	public void playerForfeitsWithPiecesLeft() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, null);
		
	}
	
	@Test
	public void resegnation() throws HantoException
	{
		PieceLocationPair[] pairs = {plPair(BLUE,BUTTERFLY,0,0),
				plPair(RED,BUTTERFLY,1,0), plPair(BLUE,HORSE,2,0),
				};
		testGame.initializeBoard(pairs);
		testGame.setTurnNumber(4);
		testGame.setPlayerMoving(RED);
		assertEquals(game.makeMove(BUTTERFLY,null,null),MoveResult.BLUE_WINS);
	}
	
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
	/**
	 * Factory method to create a piece location pair.
	 * @param player the player color
	 * @param pieceType the piece type
	 * @param x starting location
	 * @param y end location
	 * @return
	 */
	private PieceLocationPair plPair(HantoPlayerColor player, HantoPieceType pieceType, 
			int x, int y)
	{
		return new PieceLocationPair(player, pieceType, new TestHantoCoordinate(x, y));
	}
}
