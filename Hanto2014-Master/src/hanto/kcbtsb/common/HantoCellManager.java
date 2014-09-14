/**
 * 
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kyle
 *
 */
public class HantoCellManager {
	
	private final List<HantoCell> occupiedCells;
	
	public HantoCellManager(){
		occupiedCells = new ArrayList<HantoCell>();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return if cell is occupied
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
	
	public boolean isEmpty(){
		return occupiedCells.isEmpty();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void addCell(final int x, final int y){
		occupiedCells.add(new HantoCell(x, y));
	}
}
