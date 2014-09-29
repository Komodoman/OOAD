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
public class Butterfly extends HantoBasePiece{
	
	public Butterfly(HantoPlayerColor playerColor,HantoMove aMoveType, int aDistance){
		super(playerColor, aMoveType, aDistance);
		type = HantoPieceType.BUTTERFLY;
	}
	
	public Butterfly(HantoPlayerColor playerColor){
		super(playerColor, HantoMove.WALK, 1);
		type = HantoPieceType.BUTTERFLY;
	}

}
