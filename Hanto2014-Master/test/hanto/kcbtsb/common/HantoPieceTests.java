package hanto.kcbtsb.common;

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

public class HantoPieceTests {
		/**
		 * Internal class for these test cases.
		 * @version Sep 13, 2014
		 */
		
		@Test
		public void createAButterflyWithoutCell() {
			Butterfly aButterfly = new Butterfly(HantoPlayerColor.BLUE);
			assertTrue(aButterfly.getColor() == HantoPlayerColor.BLUE);
			assertFalse(aButterfly.getColor() == HantoPlayerColor.RED);
		}
		
		@Test
		public void createASparrow() {
			Sparrow aSparrow = new Sparrow(HantoPlayerColor.BLUE);
			assertTrue(aSparrow.getColor() == HantoPlayerColor.BLUE);
			assertTrue(aSparrow.getType() == HantoPieceType.SPARROW);
		}
		
		@Test
		public void createABasePiece() {
			HantoBasePiece aButterfly = new Butterfly(HantoPlayerColor.BLUE);
			aButterfly.setCell(new HantoCell(0,0));
			assertTrue(aButterfly.getColor() == HantoPlayerColor.BLUE);
			assertTrue(aButterfly.getType() == HantoPieceType.BUTTERFLY);
			assertTrue(aButterfly.getCell().getX() == new HantoCell(0,0).getX());
			assertTrue(aButterfly.getCell().getY() == new HantoCell(0,0).getY());
		}
	
	}

