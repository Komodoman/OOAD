/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import hanto.common.HantoException;
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
	 * Determines if move is valid
	 * @param from
	 * @param to
	 * @param piece
	 * @return boolean
	 * @throws HantoException
	 */
	public boolean isLegalMovement(HantoCell from, HantoCell to, HantoBasePiece piece)throws HantoException{
		boolean isLegal = true;
		int cellDistance = getDistance(from, to);
		
		
		if (!isAdjacent(to.getX(), to.getY())){
			throw new HantoException("Cell is not adjacent");
		}
		if (breaksContinuity(from, to)){
			throw new HantoException("Contiunity would break");
		}
		if(piece.getMoveType() == HantoMove.WALK)
		{
			if(!slideCheck(from, to))
			{
				throw new HantoException("Slide Moves are illegal");
			}
		} else if (piece.getMoveType() == HantoMove.JUMP){
			if (!isStraight(from, to)){
				throw new HantoException("This Piece Must Move Straight but is Not");
			}
		}
		if (cellDistance > piece.getMoveDistance()){
			System.out.println(piece.getMoveDistance());
			throw new HantoException("Distance too far for piece");
		}
		return isLegal;
	}
	
	public boolean isLegalMovePresent(HantoPlayerColor aColor){
		boolean legalMoves = false;
		
		List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
		switch(aColor){
		case BLUE:
			pieceLineup.addAll(HantoGameManager.getInstance().getBluePlayer().getPiecesRemaining());
			break;
		case RED:
			pieceLineup.addAll(HantoGameManager.getInstance().getRedPlayer().getPiecesRemaining());
			break;
		default:
			break;
		}
		System.out.println(pieceLineup);
		if (pieceLineup.size() > 0){
			legalMoves = true;
		}
		else
		{
			legalMoves = checkPlayerPiecesForMoves(aColor);
		}
		
		
		
		return legalMoves;
	}
	
	public List<HantoCell> getPlayerCells(HantoPlayerColor aColor){
		List<HantoCell> boardPieces = new ArrayList<HantoCell>();
		for(int i = 0; i < occupiedCells.size(); i++)
		{
			if(occupiedCells.get(i).getPiece().getColor() == aColor)
			{
				boardPieces.add(occupiedCells.get(i));
			}
		}
		return boardPieces;
	}
	
	private boolean checkPlayerPiecesForMoves(HantoPlayerColor aColor)
	{
		boolean legalMove = false;
		
		List<HantoCell> possibleLocations = new ArrayList<HantoCell>();	
		List<HantoCell> boardPieces = getPlayerCells(aColor);
		
		possibleLocations = generatePossibleMoves();
		for(int j = 0; j < boardPieces.size(); j++)
		{
			for(int k = 0; k < possibleLocations.size();k++)
			{
				if(checkPieceForLegality(boardPieces.get(j),possibleLocations.get(k)))
				{
					legalMove = true;
				}
			}
		}
		
		return legalMove;	
	}
	
	public boolean isALegalMove(HantoPiece piece, HantoCell from, HantoCell to){
		boolean legalMove = true;
		HantoBasePiece bPiece = (HantoBasePiece) piece;
		int cellDistance = getDistance(from, to);
		if (isAdjacent(to.getX(), to.getY())){
			legalMove = true;
		}
		else{
			legalMove = false;
		}
		if (!breaksContinuity(from, to) && legalMove == true){
			legalMove = true;
		}
		else{
			legalMove = false;
		}
		if(bPiece.getMoveType() == HantoMove.WALK){
			if(slideCheck(from, to) && legalMove == true){
				legalMove = true;
			}else{
				legalMove = false;
			}
		} else if (bPiece.getMoveType() == HantoMove.JUMP){
			if (isStraight(from, to) && legalMove == true){
				legalMove = true;
			}else{
				legalMove = false;
			}
		}
		if (cellDistance <= bPiece.getMoveDistance() && legalMove == true){
			legalMove = true;
		}
		else
		{
			legalMove = false;
		}
		return legalMove;
	}
	
	private boolean checkPieceForLegality(HantoCell from, HantoCell to)
	{
		boolean legalMove = false;
		HantoBasePiece piece = (HantoBasePiece) from.getPiece();
		List<HantoCell> possibleCells = new ArrayList<HantoCell>();
		possibleCells = generatePossibleMoves();
		
		
		for(int i = 0; i < possibleCells.size(); i++)
		{
			legalMove = isALegalMove(piece, from, to);
		}
		return legalMove;
	}
	
	public List<HantoCell> generatePossibleMoves()
	{
		List<HantoCell> cells = new ArrayList<HantoCell>();
		
		for(int i = 0; i < occupiedCells.size(); i++)
		{
			List<HantoCell> localCells = new ArrayList<HantoCell>();
			localCells = checkAdjacentCells(occupiedCells.get(i));
			for(int j = 0; j < localCells.size(); j++)
			{
				if(!cells.contains(localCells.get(j)))
				{
					cells.add(localCells.get(j));
				}
			}
		}
		
		return cells;
	}
	private List<HantoCell> checkAdjacentCells(HantoCell cell)
	{
		List<HantoCell> emptyCells = new ArrayList<HantoCell>();
		int x = cell.getX();
		int y = cell.getY();
		
		
		if (!isCellOccupied(x, y + 1)){
			emptyCells.add(new HantoCell(x,y+1));
		} if (!isCellOccupied(x + 1, y)){
			emptyCells.add(new HantoCell(x + 1,y));
		} if (!isCellOccupied(x - 1, y)){
			emptyCells.add(new HantoCell(x - 1,y));
		} if (!isCellOccupied(x, y - 1)){
			emptyCells.add(new HantoCell(x,y -1));
		} if (!isCellOccupied(x + 1, y - 1)){
			emptyCells.add(new HantoCell(x + 1,y - 1));
		} if (!isCellOccupied(x - 1, y + 1)){
			emptyCells.add(new HantoCell(x - 1,y + 1));
		}
		return emptyCells;
	}
	private boolean isStraight(HantoCell from, HantoCell to){
		boolean isStraight = false;
		
		// Check North/South Move
		if (from.getX() == to.getX()){
			isStraight = isVerticallyOccupied(from, to);
		}
		
		// Check NE/SW Move
		else if (from.getY() == to.getY()){
			isStraight = isHorizontallyOccupied(from, to);	
		}
		
		// Check NW/SE Move
		else if (from.getX() + from.getY() == to.getX() + to.getY()){
			isStraight = isDiagonallyOccupied(from, to);
		}
		
		return isStraight;
	}
	
	private boolean isDiagonallyOccupied(HantoCell from, HantoCell to){
		boolean isOccupied = false;
		if (to.getX() > from.getX()){
			for (int i = from.getX() + 1; i < to.getX(); i++){
				if (findCell(i, -i) == null){
					isOccupied = false;
					System.out.println("MISSING A PIECE");
					break;
				}
			}
		} else {
			for (int i = to.getX() - 1; i < from.getX(); i++){
				if (findCell(i, -i) == null){
					isOccupied = false;
					break;
				}
			}
		}
		return isOccupied;
	}
	
	private boolean isVerticallyOccupied(HantoCell from, HantoCell to){
		boolean isOccupied = true;
		if (to.getY() < from.getY()){
			for (int i = from.getY() - 1; i > to.getY(); i--){
				if (findCell(to.getX(), i) == null){
					isOccupied = false;
					System.out.println("MISSING A PIECE");
					break;
				}
			}
		} else {
			for (int i = from.getY() + 1; i < to.getY(); i++){
				if (findCell(to.getX(), i) == null){
					isOccupied = false;
					break;
				}
			}
		}
		return isOccupied;
	}
	
	private boolean isHorizontallyOccupied(HantoCell from, HantoCell to){
		boolean isOccupied = true;
		
		if (to.getX() < from.getX()){
			for (int i = from.getX() - 1; i > to.getX(); i--){
				if (findCell(i, to.getY()) == null){
					isOccupied = false;
					break;
				}
			}
		} else {
			for (int i = from.getX() + 1; i < to.getX(); i++){
				if (findCell(i, to.getY()) == null){
					isOccupied = false;
					break;
				}
			}
		}
		return isOccupied;
	}
	
	private boolean breaksContinuity(HantoCell from, HantoCell to){
		boolean breaksContinuity = false;
		
		// Create new board
		List<HantoCell> resultingBoard = new ArrayList<HantoCell>();
		resultingBoard.addAll(occupiedCells);
		resultingBoard.remove(from);
		resultingBoard.add(to);
		
		// Get cell 
		List<HantoCell> contigCells = getContiguousCells(resultingBoard, to);
		resultingBoard.remove(to);
		
		for (int i = 0; i < contigCells.size(); i++){
			contigCells.addAll(getContiguousCells(resultingBoard, contigCells.get(i)));
			resultingBoard.remove(contigCells.get(i));
		}
		
		if (resultingBoard.size() != 0){
			breaksContinuity = true;
		}
		return breaksContinuity;
	}
	
	private List<HantoCell> getContiguousCells(List<HantoCell> board, HantoCell aCell){
		List<HantoCell> contigCells = new ArrayList<HantoCell>();
		if (aCell != null){
			int x = aCell.getX();
			int y = aCell.getY();
			
			if (isCellOccupied(x, y + 1)){
				contigCells.add(findCell(x, y + 1, board));
			} if (isCellOccupied(x + 1, y)){
				contigCells.add(findCell(x + 1, y, board));
			} if (isCellOccupied(x - 1, y)){
				contigCells.add(findCell(x - 1, y, board));
			} if (isCellOccupied(x, y - 1)){
				contigCells.add(findCell(x, y - 1, board));
			} if (isCellOccupied(x + 1, y - 1)){
				contigCells.add(findCell(x + 1, y - 1, board));
			} if (isCellOccupied(x - 1, y + 1)){
				contigCells.add(findCell(x - 1, y + 1, board));
			}
			
		}
		
		return contigCells;
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
		return Math.abs(distance);
	}
	
	private boolean slideCheck(HantoCell from, HantoCell to)
	{
		List<HantoCell> fromCells = getContiguousCells(occupiedCells, from);
		
		List<HantoCell> resultingBoard = new ArrayList<HantoCell>();
		resultingBoard.addAll(occupiedCells);
		resultingBoard.remove(from);
		
		List<HantoCell> toCells = getContiguousCells(resultingBoard, to);
		
		int matches = 0;
		boolean canSlide = true;
		
		for(int i = 0; i < fromCells.size(); i++){
			if (toCells.contains(fromCells.get(i))){
				matches++;
			}
		}
		if(matches > 1){
			canSlide = false;
		}
		
		return canSlide;
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return if cell is contiguous to another
	 */
	public boolean isAdjacent(final int x, final int y){
		boolean isAdjacent = false;
		
		if (isCellOccupied(x, y + 1)){
			isAdjacent = true;
		} else if (isCellOccupied(x + 1, y)){
			isAdjacent = true;
		} else if (isCellOccupied(x - 1, y)){
			isAdjacent = true;
		} else if (isCellOccupied(x, y - 1)){
			isAdjacent = true;
		} else if (isCellOccupied(x + 1, y - 1)){
			isAdjacent = true;
		} else if (isCellOccupied(x - 1, y + 1)){
			isAdjacent = true;
		}
		
		return isAdjacent;
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
	
	/***
	 * Determines if any piece is adjacent to a certain piece
	 * @param to
	 * @param myColor
	 * @return boolean
	 */
	public boolean isAdjacentToEnemy(HantoCell to, HantoPlayerColor myColor){
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
	 * Find a cell from occupied cells
	 * @param x
	 * @param y
	 * @return HantoCell
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
	 * Find a cell from occupied cells
	 * @param aCell
	 * @return HantoCell
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
	 * Find a cell from occupied cells
	 * @param x
	 * @param y
	 * @param board
	 * @return HantoCell
	 */
	public HantoCell findCell(final int x, final int y, List<HantoCell> board){
		HantoCell aCell = null;
		for (int i = 0; i < board.size(); i++){
			if (board.get(i).getX() == x && board.get(i).getY() == y){
				aCell = board.get(i);
				break;
			}
		}
		
		return aCell;
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
	 * @param cell
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
	public void addCell(final int x, final int y, final HantoPiece aPiece){
		occupiedCells.add(new HantoCell(x, y, aPiece));
	}
	
	/**
	 * Adds a {@link HantoPiece} to the array of occupied cells
	 * @param aCell
	 */
	public void addCell(HantoCell aCell){
		occupiedCells.add(aCell);
	}
	
	
	/**
	 * @param aCell HantoCell
	 * @return the cell's piece
	 */
	public HantoPiece getCellPiece(final HantoCell aCell){
		HantoCell cell = findCell(aCell.getX(), aCell.getY());
		HantoPiece piece = null;
		if(cell != null)
		{
			piece = cell.getPiece(); 
		}
		return piece;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return the cell's piece
	 */
	public HantoPiece getCellPiece(final int x, final int y){
		HantoCell cell = findCell(x, y);
		HantoPiece piece = null;
		if(cell != null)
		{
			piece = cell.getPiece(); 
		}
		return piece;
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
