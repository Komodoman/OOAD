package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public class Butterfly implements HantoPiece{
	
	private HantoPlayerColor color;
	
	public Butterfly(HantoPlayerColor playerColor){
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
