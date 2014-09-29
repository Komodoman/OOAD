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

import hanto.common.*;

/**
 * 
 * @author kcbryant
 *
 */
public class HantoPieceFactory{


	public HantoPieceFactory(){}
	
	/**
	 * 
	 * @param pieceId
	 * @param pieceColor
	 * @return Piece that is made by the factory
	 */
	public static HantoPiece makeHantoPiece
	(final HantoPieceType pieceId, final HantoPlayerColor pieceColor) {
		HantoPiece piece = null;
		switch (pieceId) {
		case BUTTERFLY:
			piece = new Butterfly(pieceColor);
			break;
		case SPARROW:
			piece = new Sparrow(pieceColor);
			break;
		case CRAB:
			piece = new Crab(pieceColor);
			break;
		default:
			break;
		}
		return piece;
	}
	
	/**
	 * 
	 * @param pieceId
	 * @param pieceColor
	 * @return Piece that is made by the factory
	 */
	public static HantoPiece makeHantoPiece
	(final HantoPieceType pieceId, final HantoPlayerColor pieceColor, final HantoMove moveType, final int moveDistance) {
		HantoPiece piece = null;
		switch (pieceId) {
		case BUTTERFLY:
			piece = new Butterfly(pieceColor, moveType, moveDistance);
			break;
		case SPARROW:
			piece = new Sparrow(pieceColor, moveType, moveDistance);
			break;
		case CRAB:
			piece = new Crab(pieceColor, moveType, moveDistance);
			break;
		default:
			break;
		}
		return piece;
	}
}