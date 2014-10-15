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

	private static HantoPieceFactory instance = null;
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
	 * 
	 * @return the instance
	 */
	public static HantoPieceFactory getInstance()
	{
		if(instance == null){
			instance = new HantoPieceFactory();
		}
		return instance;
	}

	/**
	 * Sets up move type and move distance for each piece
	 * @param aPiece
	 * @param moveType
	 * @param moveDist
	 */
	
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