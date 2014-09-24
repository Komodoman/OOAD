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


public class HantoPieceFactory{


	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @param movesFirst the player color that moves first
	 * @return the game instance
	 */
	public static HantoPiece makeHantoPiece
	(final HantoPieceType pieceId, final HantoPlayerColor pieceColor) {
		HantoPiece piece = null;
		switch (pieceId) {
		case BUTTERFLY:
			piece = new Butterfly(pieceColor);
			break;
		default:
			break;
		}
		return piece;
	}
}