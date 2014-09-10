package hanto.kcbtsb.alpha;

import java.util.ArrayList;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoCellManager;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoPieceFactory;
import hanto.kcbtsb.common.HantoPlayer;
import hanto.kcbtsb.common.HantoPlayerTurn;

public class HantoGameAlpha implements HantoGame {
	
	public HantoGameAlpha(){
		HantoGameManager.gameType = this;
		HantoGameManager.cellManager = new HantoCellManager();
		HantoGameManager.colorTurn = HantoPlayerTurn.BLUE;
		ArrayList<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
		pieceLineup.add(HantoPieceType.BUTTERFLY);
		HantoGameManager.bluePlayer = new HantoPlayer(HantoPlayerColor.BLUE, pieceLineup);
		HantoGameManager.redPlayer = new HantoPlayer(HantoPlayerColor.RED, pieceLineup);
	}
	
	
	@Override
	final public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		MoveResult result = MoveResult.OK;
		
		if (HantoGameManager.cellManager.isEmpty()){
			// check for first move of game
			if (to.getX() == 0 && to.getY() == 0 && pieceType == HantoPieceType.BUTTERFLY){
				HantoGameManager.cellManager.addCell(to.getX(), to.getY());
			}
			else{
				throw new HantoException("First butterfly must be placed at 0,0");
			}
		}
		
		else if (!isLegalMove(to)){
			throw new HantoException("Not a Legal Move!");
		}
		
		else if (HantoGameManager.cellManager.isCellOccupied(to.getX(), to.getY())){
			// throw exception if destination is already occupied
			throw new HantoException("Cell is already occupied");
		}
		
		else{
			// add cell to cell manager
			HantoGameManager.cellManager.addCell(to.getX(), to.getY());
			if (HantoGameManager.getPlayerTurn() == HantoPlayerColor.RED){
				result = MoveResult.DRAW;
			}
		}
		HantoGameManager.colorTurn = HantoGameManager.colorTurn.getNext();
		return result;
	}
	
	private boolean isLegalMove(HantoCoordinate to){
		boolean isLegal = true;
		if (Math.abs(to.getX()) > 1 || Math.abs(to.getY()) > 1){
			isLegal = false;
		}
		return isLegal;
	}

	@Override
	final public HantoPiece getPieceAt(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	final public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
