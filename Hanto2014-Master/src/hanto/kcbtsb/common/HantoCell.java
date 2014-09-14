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
	
	/**
	 * Constructor for HantoCell.
	 */
	public HantoCell(){
	}
	
	/**
	 * Constructor for HantoCell.
	 * @param x int
	 * @param y int
	 */
	public HantoCell(final int x, final int y){
		xLoc = x;
		yLoc = y;
	}
	
	@Override
	public int getX() {
		return xLoc;
	}

	@Override
	public int getY() {
		return yLoc;
	}

}
