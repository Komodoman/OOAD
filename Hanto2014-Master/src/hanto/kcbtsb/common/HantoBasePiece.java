package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public abstract class HantoBasePiece implements HantoPiece
{
	protected HantoPlayerColor color;
	protected HantoPieceType type;
	protected HantoCell currentCell;
	protected HantoMove moveType;
	protected int moveDistance;
	
	
	public HantoBasePiece(HantoPlayerColor playerColor, HantoMove aMoveType, int aDistance){
		color = playerColor;
		moveType = aMoveType;
		moveDistance = aDistance;
	}
	public HantoBasePiece(HantoPlayerColor playerColor, HantoCell cell){
		color = playerColor;
		currentCell = cell;
	}
	public HantoBasePiece(HantoPlayerColor playerColor){
		color = playerColor;
		currentCell = null;
	}
	
	@Override
	public HantoPlayerColor getColor(){
		return color;
	}
	
	@Override
	public HantoPieceType getType(){
		return type;
	}
	
	public void setCell(HantoCell aCell){
		currentCell = aCell;
	}
	
	public HantoCell getCell(){
		return currentCell;
	}

}
