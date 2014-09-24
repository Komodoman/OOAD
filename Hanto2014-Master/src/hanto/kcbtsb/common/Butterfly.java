/*
 * Butterfly
 */
package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;


/**
 * A HantoPiece of type butterfly
 */
public class Butterfly implements HantoPiece{
	
	private HantoPlayerColor color;
	
	private HantoCell curCell;
	
	/**
	 * Constructor
	 * @param playerColor
	 */
	public Butterfly(HantoPlayerColor playerColor){
		color = playerColor;
		curCell = null;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.BUTTERFLY;
	}
	
	public void setCell(HantoCell aCell){
		curCell = aCell;
	}
	
	public HantoCell getCell(){
		return curCell;
	}

}
