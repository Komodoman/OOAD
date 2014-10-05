/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import hanto.common.*;

/**
 * 
 * @author kcbryant
 *
 */
public class HantoPieceFactory{

	private static final HantoPieceFactory INSTANCE = new HantoPieceFactory();
	private static HantoMove sparrowMoveType = null;
	private static HantoMove butterflyMoveType = null;
	private static HantoMove crabMoveType = null;
	
	/**
	 * Default private descriptor.
	 */
	private HantoPieceFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoPieceFactory getInstance()
	{
		return INSTANCE;
	}
	/**
	 * Sets up move Types for all pieces
	 * @param s
	 * @param b
	 * @param c
	 */
	public static void setUp(HantoMove s, HantoMove b, HantoMove c)
	{
		sparrowMoveType = s;
		butterflyMoveType = b;
		crabMoveType = c;
	}
	
	/**
	 * 
	 * @param pieceId
	 * @param pieceColor
	 * @return Piece that is made by the factory
	 */
	public static HantoBasePiece makeHantoPiece
	(final HantoPieceType pieceId, final HantoPlayerColor pieceColor) {
		HantoBasePiece piece = null;
		switch (pieceId) {
		case BUTTERFLY:
			piece = new HantoBasePiece(pieceColor, pieceId, butterflyMoveType);
			break;
		case SPARROW:
			piece = new HantoBasePiece(pieceColor, pieceId, sparrowMoveType);
			break;
		case CRAB:
			piece = new HantoBasePiece(pieceColor, pieceId, crabMoveType);
			break;
		default:
			break;
		}
		return piece;
	}
}