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
public class Sparrow implements HantoPiece{
	
	private HantoPlayerColor color;
	
	/**
	 * Constructor
	 * @param playerColor
	 */
	public Sparrow(HantoPlayerColor playerColor){
		color = playerColor;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.BUTTERFLY;
	}
}