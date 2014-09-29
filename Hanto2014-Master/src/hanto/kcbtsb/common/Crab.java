package hanto.kcbtsb.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * A HantoPiece of type crab
 */
public class Crab extends HantoBasePiece {

	public Crab(HantoPlayerColor playerColor, HantoMove aMoveType, int aDistance){
		super(playerColor, aMoveType, aDistance);
		type = HantoPieceType.CRAB;
	}
	
	public Crab(HantoPlayerColor playerColor){
		super(playerColor, HantoMove.WALK, 1);
		type = HantoPieceType.CRAB;
	}
}
