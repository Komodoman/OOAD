/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;
import java.util.List;

/**
 *<p> Manager of the Hanto game board.  Contains all the methods
 *for adding to the board, checking cells, and also
 *contains the board itself.<p> 
 */
public class HantoCellManager {
	
	private final List<HantoCell> occupiedCells;
	
	public HantoCellManager(){
		occupiedCells = new ArrayList<HantoCell>();
	}
	
	/**
	 * Determines if a cell is occupied or not
	 * @param x coordinate
	 * @param y	coordinate
	 * @return 
	 * 		if cell is occupied
	 */
	public boolean isCellOccupied(final int x, final int y){
		boolean isOccupied = false;

		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == x && occupiedCells.get(i).getY() == y){
				isOccupied = true;
				break;
			}
		}
		
		return isOccupied;
	}
	/**
	 * Method to check whether the board is empty or not
	 * @return
	 * 		boolean check if occupied cells is empty.
	 */
	public boolean isEmpty(){
		return occupiedCells.isEmpty();
	}
	
	/**
	 * Adds a {@link HantoPiece} to the array of occupied cells
	 * @param x
	 * @param y
	 */
	public void addCell(final int x, final int y){
		occupiedCells.add(new HantoCell(x, y));
	}
}
