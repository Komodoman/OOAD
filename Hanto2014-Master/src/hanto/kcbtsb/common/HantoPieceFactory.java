/**
 * 
 */
package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author Kyle
 *
 */
public class HantoPieceFactory {

/**
 * 
 * @param type
 * @param color
 * @return
 */
public static HantoPiece buildHantoPiece(final HantoPieceType type, final HantoPlayerColor color){
	HantoPiece aPiece = null;
	switch(type){
	case BUTTERFLY:
		aPiece = new Butterfly(color);
	case CRAB:
		break;
	case CRANE:
		break;
	case DOVE:
		break;
	case HORSE:
		break;
	case SPARROW:
		break;
	default:
		break;
	}
	
	return aPiece;
}
}
