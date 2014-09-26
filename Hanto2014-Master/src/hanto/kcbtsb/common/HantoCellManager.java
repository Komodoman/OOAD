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
		
		if (findCell(x, y) != null){
			isOccupied = true;
		}
		
		return isOccupied;
	}
	
	/**
	 * 
	 * @param cell HantoCell
	 * @return if cell is occupied
	 */
	public boolean isCellOccupied(final HantoCell cell){
		boolean isOccupied = false;

		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == cell.getX() && occupiedCells.get(i).getY() == cell.getY()){
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
	 * @return if cell is contiguous to another
	 */
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
	
	/**
	 * 
	 * @param color
	 * @return if a victory condition has been met
	 */
	public boolean isVictory(final HantoPlayerColor color){
		
		boolean isVictory = false;
		HantoCell butterflyCell = null;
		int gridSize = occupiedCells.size();

		for(int i = 0; i < gridSize - 1; i++){
			HantoPiece curPiece = occupiedCells.get(i).getPiece();
			if (curPiece.getType() == HantoPieceType.BUTTERFLY && curPiece.getColor() != color){
				butterflyCell = occupiedCells.get(i);
				break;
			}
		}
		
		
		if (butterflyCell != null){
			isVictory = isSurroundingCellsOccupied(butterflyCell);
		}
		
		return isVictory;
	}
	
	private boolean isSurroundingCellsOccupied(final HantoCell butterfly){
		boolean allOccupied = true;
		
		final HantoCell neCell = new HantoCell(butterfly.getX() + 1, butterfly.getY());
		if (!isCellOccupied(neCell)){
			allOccupied = false;
		}
		final HantoCell seCell = new HantoCell(butterfly.getX() + 1, butterfly.getY() - 1);
		if (!isCellOccupied(seCell)){
			allOccupied = false;
		}
		final HantoCell sCell = new HantoCell(butterfly.getX(), butterfly.getY() - 1);
		if (!isCellOccupied(sCell)){
			allOccupied = false;
		}
		final HantoCell swCell = new HantoCell(butterfly.getX() - 1, butterfly.getY());
		if (!isCellOccupied(swCell)){
			allOccupied = false;
		}
		final HantoCell nwCell = new HantoCell(butterfly.getX() - 1, butterfly.getY() + 1);
		if (!isCellOccupied(nwCell)){
			allOccupied = false;
		}
		final HantoCell nCell = new HantoCell(butterfly.getX(), butterfly.getY() + 1);
		if (!isCellOccupied(nCell)){
			allOccupied = false;
		}
		
		return allOccupied;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private HantoCell findCell(final int x, final int y){
		HantoCell aCell = null;
		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == x && occupiedCells.get(i).getY() == y){
				aCell = occupiedCells.get(i);
				break;
			}
		}
		
		return aCell;
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
	 * @param aPiece HantoPieceType
	 */
	public void addCell(final int x, final int y, final HantoPieceType aPiece){
		occupiedCells.add(new HantoCell(x, y, aPiece));
	}
	
	
	/**
	 * @param aCell HantoCell
	 * @return the cell's piece
	 */
	public HantoPiece getCellPiece(final HantoCell aCell){
		return findCell(aCell.getX(), aCell.getY()).getPiece();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return the cell's piece
	 */
	public HantoPiece getCellPiece(final int x, final int y){
		return findCell(x, y).getPiece();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return the cell's color
	 */
	public HantoPlayerColor getCellColor(final int x, final int y){
		return findCell(x, y).getCellColor();
	}
	
	/**
	 * 
	 * @param cell
	 * @return the cell's color
	 */
	public HantoPlayerColor getCellColor(HantoCell cell){
		return findCell(cell.getX(), cell.getY()).getCellColor();
	}
}
