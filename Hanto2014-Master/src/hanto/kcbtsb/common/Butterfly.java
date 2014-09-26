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
public class Butterfly extends HantoPlayerPiece{
	

	/**
	 * Constructor
	 * @param playerColor
	 */
	public Butterfly(HantoPlayerColor playerColor, HantoCell cell){
		color = playerColor;
		currentCell = cell;
		type = HantoPieceType.BUTTERFLY;
	}
	public Butterfly(HantoPlayerColor playerColor){
		color = playerColor;
		currentCell = null;
		type = HantoPieceType.BUTTERFLY;
	}

}
