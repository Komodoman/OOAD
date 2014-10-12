/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/


package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Realization of the HantoPiece Interface
 * @author tsbujnevicie
 *
 */
public class HantoBasePiece implements HantoPiece
{
	private HantoPlayerColor color;
	private HantoPieceType type;
	private HantoCell currentCell;
	private HantoMove moveType;
	private int moveDistance;

	
	
	/**
	 * Constructor for Base Piece
	 * @param playerColor
	 * @param type
	 * @param moveType
	 */
	public HantoBasePiece(HantoPlayerColor playerColor, HantoPieceType type, HantoMove moveType, int moveDistance){
		color = playerColor;
		this.type = type;
		switch(type)
		{
		case BUTTERFLY:
			this.moveType = moveType;
			this.moveDistance = moveDistance;
			break;
		case CRAB:
			this.moveType = moveType;
			this.moveDistance = moveDistance;
			break;
		case SPARROW:
			this.moveType = moveType;
			this.moveDistance = moveDistance;
			break;
		case HORSE:
			this.moveType = moveType;
			this.moveDistance = moveDistance;
			break;
		default:
			this.moveType = HantoMove.WALK;
			this.moveDistance = moveDistance;
			break;
		}
		
	}
	
	/**
	 * @see HantoPiece.getColor
	 */
	@Override
	public HantoPlayerColor getColor(){
		return color;
	}
	/**
	 * @see HantoPiece.getType
	 */
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
	
	public HantoMove getMoveType() {
		return moveType;
	}
	public void setMoveType(HantoMove moveType) {
		this.moveType = moveType;
	}
	public int getMoveDistance() {
		return moveDistance;
	}
	public void setMoveDistance(int moveDistance) {
		this.moveDistance = moveDistance;
	}
	public void setType(HantoPieceType type) {
		this.type = type;
	}
	


}
