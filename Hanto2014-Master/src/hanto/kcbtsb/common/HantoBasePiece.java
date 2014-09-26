package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public abstract class HantoBasePiece implements HantoPiece
{
	protected HantoPlayerColor color;
	protected HantoPieceType type;
	protected HantoCell currentCell;
	
	protected void checkMoveValidity(){
		
	}
	
	protected void checkWalk(){
		
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
