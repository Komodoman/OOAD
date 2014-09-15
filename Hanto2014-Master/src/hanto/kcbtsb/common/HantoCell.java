/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;

/**
 *@see HantoCoordinate
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
	/**
	 * Getter for the x coordinate
	 * @return 
	 * 		x coodinate
	 */
	@Override
	public int getX() {
		return xLoc;
	}
	/**
	 * Getter for the y coordinate
	 * @return 
	 * 		y coodinate
	 */
	@Override
	public int getY() {
		return yLoc;
	}

}
