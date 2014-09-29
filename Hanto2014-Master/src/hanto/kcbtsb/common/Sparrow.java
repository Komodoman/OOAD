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
public class Sparrow extends HantoBasePiece{
	
	public Sparrow(HantoPlayerColor playerColor, HantoMove aMoveType, int aDistance){
		super(playerColor, aMoveType, aDistance);
		type = HantoPieceType.SPARROW;
	}
	public Sparrow(HantoPlayerColor playerColor){
		super(playerColor, HantoMove.WALK, 1);
		type = HantoPieceType.SPARROW;
	}
	
}