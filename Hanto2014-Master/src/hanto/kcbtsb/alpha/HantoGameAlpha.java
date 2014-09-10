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
import hanto.kcbtsb.common.HantoPlayer;
import hanto.kcbtsb.common.HantoPlayerTurn;

/**
 */
public class HantoGameAlpha implements HantoGame {
	
	/**
	 * Constructor for HantoGameAlpha.
	 */
	HantoGameManager gameManager;
	
	public HantoGameAlpha(){
		gameManager = HantoGameManager.getInstance();
		
		gameManager.setGameType(this);
		gameManager.setCellManager(new HantoCellManager());
		gameManager.setColorTurn(HantoPlayerTurn.BLUE);
		gameManager.setBluePlayer(new HantoPlayer(HantoPlayerColor.BLUE));
		gameManager.setRedPlayer(new HantoPlayer(HantoPlayerColor.RED));
	}
	
	
	@Override
	final public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		MoveResult result = MoveResult.OK;
		
		if (gameManager.getCellManager().isEmpty()){
			// check for first move of game
			if (to.getX() == 0 && to.getY() == 0 && pieceType == HantoPieceType.BUTTERFLY){
				gameManager.getCellManager().addCell(to.getX(), to.getY());
			}
			else{
				throw new HantoException("First butterfly must be placed at 0,0");
			}
		}
		
		else if (!isLegalMove(to)){
			throw new HantoException("Not a Legal Move!");
		}
		
		else if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			// throw exception if destination is already occupied
			throw new HantoException("Cell is already occupied");
		}
		
		else{
			// add cell to cell manager
			gameManager.getCellManager().addCell(to.getX(), to.getY());
			if (gameManager.getPlayerTurn() == HantoPlayerColor.RED){
				result = MoveResult.DRAW;
			}
		}
		gameManager.setColorTurn(gameManager.getColorTurn().getNext());
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
