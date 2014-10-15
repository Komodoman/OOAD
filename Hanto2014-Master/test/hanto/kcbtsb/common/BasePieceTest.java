package hanto.kcbtsb.common;

import static org.junit.Assert.*;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import org.junit.Test;



public class BasePieceTest {

	@Test
	public void testSetCell()
	{
		HantoBasePiece piece = new HantoBasePiece(HantoPlayerColor.RED, HantoPieceType.CRAB, HantoMove.WALK, 1);
		HantoCell cell = new HantoCell(0,0);
		piece.setCell(cell);
		assertEquals(piece.getCell(), cell);
	}
	@Test
	public void testSetMoveType()
	{
		HantoBasePiece piece = new HantoBasePiece(HantoPlayerColor.RED, HantoPieceType.CRAB, HantoMove.WALK, 1);
		piece.setMoveType(HantoMove.FLY);
		assertEquals(piece.getMoveType(), HantoMove.FLY);
	}
	@Test
	public void testSetMoveDistance()
	{
		HantoBasePiece piece = new HantoBasePiece(HantoPlayerColor.RED, HantoPieceType.CRAB, HantoMove.WALK, 1);
		piece.setMoveDistance(50);
		assertEquals(piece.getMoveDistance(), 50);
	}
	@Test
	public void testSetType()
	{
		HantoBasePiece piece = new HantoBasePiece(HantoPlayerColor.RED, HantoPieceType.CRAB, HantoMove.WALK, 1);
		piece.setType(HantoPieceType.BUTTERFLY);
		assertEquals(piece.getType(), HantoPieceType.BUTTERFLY);
	}
	
	
}
