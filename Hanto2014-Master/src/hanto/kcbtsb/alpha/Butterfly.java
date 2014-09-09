/**
 * 
 */
package hanto.kcbtsb.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author Kyle
 *
 */
public class Butterfly implements HantoPiece {
	
	private HantoPlayerColor color;
	
	public Butterfly(HantoPlayerColor playerColor){
		color = playerColor;
	}
	
	/* (non-Javadoc)
	 * @see hanto.common.HantoPiece#getColor()
	 */
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoPiece#getType()
	 */
	@Override
	public HantoPieceType getType() {
		return HantoPieceType.BUTTERFLY;
	}

}
