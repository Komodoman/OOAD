/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 *@see HantoCoordinate
 */
public class HantoCell implements HantoCoordinate {
	
	private int xLoc;
	
	private int yLoc;
	
	private HantoPieceType pieceType;
	
	private HantoPlayerColor curColor;
	
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
	 * Constructor for HantoCell.
	 * @param x int
	 * @param y int
	 */
	public HantoCell(final int x, final int y, HantoPieceType aPiece){
		xLoc = x;
		yLoc = y;
		pieceType = aPiece;
		curColor = HantoGameManager.getInstance().getPlayerTurn();
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
	
	public HantoPieceType getPiece() {
		return pieceType;
	}
	
	public HantoPlayerColor getCellColor() {
		return curColor;
	}

}
