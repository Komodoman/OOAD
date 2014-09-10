/**
 * 
 */
package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;

/**
 * @author Kyle
 *
 */
public class HantoCell implements HantoCoordinate {
	
	private int xLoc;
	
	private int yLoc;
	
	public HantoCell(){
	}
	
	public HantoCell(int x, int y){
		xLoc = x;
		yLoc = y;
	}
	
	/* (non-Javadoc)
	 * @see hanto.common.HantoCoordinate#getX()
	 */
	@Override
	public int getX() {
		return xLoc;
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoCoordinate#getY()
	 */
	@Override
	public int getY() {
		return yLoc;
	}

}
