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
	private static HantoMove sparrowMoveType = HantoMove.FLY;
	private static HantoMove butterflyMoveType = HantoMove.WALK;
	private static HantoMove crabMoveType = HantoMove.WALK;
	private static HantoMove horseMoveType = HantoMove.JUMP;
	
	private static int sparrowMoveDist = 1;
	private static int butterflyMoveDist = 1;
	private static int crabMoveDist = 1;
	private static int horseMoveDist = 1;
	
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
	
//	/**
//	 * Sets up move Types for all pieces
//	 * @param s
//	 * @param b
//	 * @param c
//	 * @param h
//	 */
//	public static void setUp(HantoMove s, HantoMove b, HantoMove c, HantoMove h)
//	{
//		sparrowMoveType = s;
//		butterflyMoveType = b;
//		crabMoveType = c;
//		horseMoveType = h;
//	}
	
	public static void setupPiece(final HantoPieceType aPiece, final HantoMove moveType, final int moveDist){
		
		switch(aPiece){
		case BUTTERFLY:
			butterflyMoveType = moveType;
			butterflyMoveDist = moveDist;
			break;
		case CRAB:
			crabMoveType = moveType;
			crabMoveDist = moveDist;
			break;
		case HORSE:
			horseMoveType = moveType;
			horseMoveDist = moveDist;
			break;
		case SPARROW:
			sparrowMoveType = moveType;
			sparrowMoveDist = moveDist;
			break;
		default:
			break;
		
		}
	}
	
	public HantoMove getMoveType(HantoPieceType aPiece){
		HantoMove aMoveType = null;
		switch(aPiece){
		case BUTTERFLY:
			aMoveType = butterflyMoveType;
			break;
		case CRAB:
			aMoveType = crabMoveType;
			break;
		case HORSE:
			aMoveType = horseMoveType;
			break;
		case SPARROW:
			aMoveType = sparrowMoveType;
			break;
		default:
			break;
		
		}
		return aMoveType;
	}
		
	public int getMoveDist(HantoPieceType aPiece){
			int moveDist = 0;
			switch(aPiece){
			case BUTTERFLY:
				moveDist = butterflyMoveDist;
				break;
			case CRAB:
				moveDist = crabMoveDist;
				break;
			case HORSE:
				moveDist = horseMoveDist;
				break;
			case SPARROW:
				moveDist = sparrowMoveDist;
				break;
			default:
				break;
			
			}
		return moveDist;	
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
			piece = new HantoBasePiece(pieceColor, pieceId, butterflyMoveType, butterflyMoveDist);
			break;
		case SPARROW:
			piece = new HantoBasePiece(pieceColor, pieceId, sparrowMoveType, sparrowMoveDist);
			break;
		case CRAB:
			piece = new HantoBasePiece(pieceColor, pieceId, crabMoveType, crabMoveDist);
			break;
		case HORSE:
			piece = new HantoBasePiece(pieceColor, pieceId, horseMoveType, horseMoveDist);
			break;
		default:
			break;
		}
		return piece;
	}
}