/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

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
	 * 
	 * @param x
	 * @param y
	 * @return if cell is occupied
	 */
	public boolean isCellOccupied(HantoCell cell){
		boolean isOccupied = false;

		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == cell.getX() && occupiedCells.get(i).getY() == cell.getY()){
				isOccupied = true;
				break;
			}
		}
		
		return isOccupied;
	}
	
	public boolean isContiguous(final int x, final int y){
		boolean isContiguous = false;
		
		if (isCellOccupied(x, y + 1)){
			isContiguous = true;
		} else if (isCellOccupied(x + 1, y)){
			isContiguous = true;
		} else if (isCellOccupied(x - 1, y)){
			isContiguous = true;
		} else if (isCellOccupied(x, y - 1)){
			isContiguous = true;
		} else if (isCellOccupied(x + 1, y - 1)){
			isContiguous = true;
		} else if (isCellOccupied(x - 1, y + 1)){
			isContiguous = true;
		}
		
		return isContiguous;
	}
	
	/**
	 * Method to check whether the board is empty or not
	 * @return
	 * 		boolean check if occupied cells is empty.
	 */
	public boolean isEmpty(){
		return occupiedCells.isEmpty();
	}
	
	public boolean isVictory(HantoPlayerColor color){
		
		boolean isVictory = false;
		HantoCell butterflyCell = null;
		
		for(int i = 0; i < occupiedCells.size(); i++){
			HantoCell curCell = occupiedCells.get(i);
			if (curCell.getPiece().getType() == HantoPieceType.BUTTERFLY && curCell.getCellColor() != color){
				butterflyCell = curCell;
			}
		}
		
		if (butterflyCell != null){
			//System.out.println("checking surrounding cells");
			isVictory = areSurroundingCellsEnemies(butterflyCell);
		}
		
		return isVictory;
	}
	
	private boolean areSurroundingCellsEnemies(HantoCell butterfly){
		boolean areEnemies = true;
		
		HantoCell neCell = new HantoCell(butterfly.getX() + 1, butterfly.getY());
		if (!isCellOccupied(neCell) ||  getCellColor(neCell) == butterfly.getCellColor()){
			areEnemies = false;
		}
		HantoCell seCell = new HantoCell(butterfly.getX() + 1, butterfly.getY() - 1);
		if (!isCellOccupied(seCell) ||  getCellColor(seCell) == butterfly.getCellColor()){
			areEnemies = false;
		}
		HantoCell sCell = new HantoCell(butterfly.getX(), butterfly.getY() - 1);
		if (!isCellOccupied(sCell) ||  getCellColor(sCell) == butterfly.getCellColor()){
			areEnemies = false;
		}
		HantoCell swCell = new HantoCell(butterfly.getX() - 1, butterfly.getY());
		if (!isCellOccupied(swCell) ||  getCellColor(swCell) == butterfly.getCellColor()){
			areEnemies = false;
		}
		HantoCell nwCell = new HantoCell(butterfly.getX() - 1, butterfly.getY() + 1);
		if (!isCellOccupied(nwCell) ||  getCellColor(nwCell) == butterfly.getCellColor()){
			areEnemies = false;
		}
		HantoCell nCell = new HantoCell(butterfly.getX(), butterfly.getY() + 1);
		if (!isCellOccupied(nCell) ||  getCellColor(nCell) == butterfly.getCellColor()){
			areEnemies = false;
		}
		
		return areEnemies;
	}
	
	/**
	 * Adds a {@link HantoPiece} to the array of occupied cells
	 * @param x
	 * @param y
	 */
	public void addCell(final int x, final int y){
		occupiedCells.add(new HantoCell(x, y));
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void addCell(final int x, final int y, HantoPieceType aPiece){
		occupiedCells.add(new HantoCell(x, y, aPiece));
	}
	
	public HantoPiece getCellPiece(final int x, final int y){
		
		HantoCell aCell = null;
		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == x && occupiedCells.get(i).getY() == y){
				aCell = occupiedCells.get(i);
				break;
			}
		}
		
		return aCell.getPiece();
	}
	
	public HantoPlayerColor getCellColor(final int x, final int y){
		
		HantoCell aCell = null;
		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == x && occupiedCells.get(i).getY() == y){
				aCell = occupiedCells.get(i);
				break;
			}
		}
		
		return aCell.getCellColor();
	}
	
	public HantoPlayerColor getCellColor(HantoCell cell){
		
		HantoCell aCell = null;
		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == cell.getX() && occupiedCells.get(i).getY() == cell.getY()){
				aCell = occupiedCells.get(i);
				break;
			}
		}
		
		return aCell.getCellColor();
	}
}
