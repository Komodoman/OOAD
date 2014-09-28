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
	
	public boolean isLegalMovement(HantoCell from, HantoCell to, HantoPieceType pieceType){
		boolean isLegal = true;
		int cellDistance = getDistance(from, to);
		
		if (cellDistance > 1){
			isLegal = false;
		} else if (!isContiguous(to.getX(), to.getY())){
			isLegal = false;
		}
		return isLegal;
	}
	
	private int getDistance(HantoCell from, HantoCell to){
		int distance = 0;
		if (to.getY() > to.getX()){
			distance = (to.getX() - from.getX()) + (to.getY() - from.getY());
		} else if ((from.getX() + from.getY()) > (to.getX() + to.getY())){
			distance = from.getY() - to.getY();
		} else{
			distance = to.getX() - from.getX();
		}
		return distance;
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
	
	public boolean isContiguousToEnemy(HantoCell to, HantoPlayerColor myColor){
		boolean isEnemy = false;
		final HantoCell neCell = new HantoCell(to.getX() + 1, to.getY());
		if (isCellOccupied(neCell)){
			if (!findCell(neCell).getCellColor().equals(myColor)){
				isEnemy = true;
			}
		}
		final HantoCell seCell = new HantoCell(to.getX() + 1, to.getY() - 1);
		if (isCellOccupied(seCell)){
			if (!findCell(seCell).getCellColor().equals(myColor)){
				isEnemy = true;
			}
		}
		final HantoCell sCell = new HantoCell(to.getX(), to.getY() - 1);
		if (isCellOccupied(sCell)){
			if (!findCell(sCell).getCellColor().equals(myColor)){
				isEnemy = true;
			}
		}
		final HantoCell swCell = new HantoCell(to.getX() - 1, to.getY());
		if (isCellOccupied(swCell)){
			if (!findCell(swCell).getCellColor().equals(myColor)){
				isEnemy = true;
			}
		}
		final HantoCell nwCell = new HantoCell(to.getX() - 1, to.getY() + 1);
		if (isCellOccupied(nwCell)){
			if (!findCell(nwCell).getCellColor().equals(myColor)){
				isEnemy = true;
			}
		}
		final HantoCell nCell = new HantoCell(to.getX(), to.getY() + 1);
		if (isCellOccupied(nCell)){
			if (!findCell(nCell).getCellColor().equals(myColor)){
				isEnemy = true;
			}
		}
		return isEnemy;
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
	public HantoCell findCell(final int x, final int y){
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
	 * @param HantoCell
	 * @return
	 */
	public HantoCell findCell(final HantoCell aCell){
		HantoCell realCell = null;
		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == aCell.getX() && occupiedCells.get(i).getY() == aCell.getY()){
				realCell = occupiedCells.get(i);
				break;
			}
		}
		
		return realCell;
	}
	
	/**
	 * Removes a {@link HantoPiece} from the array of occupied cells
	 * @param x
	 * @param y
	 */
	public void remCell(final int x, final int y){
		HantoCell aCell = findCell(x, y);
		occupiedCells.remove(aCell);
	}
	
	/**
	 * Removes a {@link HantoPiece} from the array of occupied cells
	 * @param HantoCell
	 */
	public void remCell(HantoCell cell){
		HantoCell aCell = findCell(cell);
		occupiedCells.remove(aCell);
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
	 * Adds a {@link HantoPiece} to the array of occupied cells
	 * @param x
	 * @param y
	 */
	public void addCell(HantoCell aCell){
		occupiedCells.add(aCell);
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
