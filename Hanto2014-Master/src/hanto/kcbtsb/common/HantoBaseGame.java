package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;
import hanto.kcbtsb.common.ExtendedHantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

public abstract class HantoBaseGame implements HantoGame {
	
	private HantoGameManager gameManager;
	
	
	public HantoBaseGame(HantoPlayerColor firstTurnColor){
		gameManager = HantoGameManager.getInstance();
		gameManager.setCellManager(new HantoCellManager());
		gameManager.setColorTurn(firstTurnColor);
		gameManager.setTurnCount(1);
		
	}


	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws ExtendedHantoException {
		preCheck(from, to);
		movePiece(to, pieceType);
		return postCheck(pieceType);
	}
	
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return gameManager.getCellManager().getCellPiece(where.getX(), where.getY());
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void movePiece(HantoCoordinate to, HantoPieceType pieceType) throws ExtendedHantoException{
		if (!getCurrentPlayer().getPiecesRemaining().contains(pieceType)){
			throw new ExtendedHantoException("None of those pieces remaining");
		}
		gameManager.getCellManager().addCell(to.getX(), to.getY(), pieceType);
	}

	protected void preCheck(HantoCoordinate from, HantoCoordinate to) throws ExtendedHantoException
	{	
		int turn = HantoGameManager.getInstance().getTurnCount();
		if (turn == 1 && (to.getX() != 0 || to.getY() != 0)){
			throw new ExtendedHantoException("Player must place first piece at 0,0");
		} else if (turn >= 7 && getCurrentPlayer().getPiecesRemaining().contains(HantoPieceType.BUTTERFLY)){
			throw new ExtendedHantoException("Player must place butterfly by fourth turn.");
		}
			
		if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			throw new ExtendedHantoException("Cell is already occupied.");
		} else if (!gameManager.getCellManager().isContiguous(to.getX(), to.getY())){
			if (gameManager.getTurnCount() != 1){
				throw new ExtendedHantoException("Cell is not contiguous to another piece");
			}
		}
	}
	
	protected MoveResult postCheck(HantoPieceType pieceType){
		MoveResult result = MoveResult.OK;
		boolean resetGameManager = false;
		
		if (gameManager.getBluePlayer().getPieceCount() == 0){
			result = MoveResult.DRAW;
			resetGameManager = true;
		}
		
		if (isVictory(HantoPlayerColor.BLUE)){
			result = MoveResult.BLUE_WINS;
			resetGameManager = true;
		} else if (isVictory(HantoPlayerColor.RED)){
			result = MoveResult.RED_WINS;
			resetGameManager = true;
		}
		getCurrentPlayer().removePieceFromLineup(pieceType);
		HantoGameManager.getInstance().nextTurn();
		if (resetGameManager){
			gameManager.clearInstance();
		}
		return result;
	}
	
	public HantoPlayer getCurrentPlayer(){
		HantoPlayer curPlayer = null;
		switch(gameManager.getPlayerTurn()){
		case BLUE:
			curPlayer = gameManager.getBluePlayer();
			break;
		case RED:
			curPlayer = gameManager.getRedPlayer();
			break;
		}
		
		return curPlayer;
	}
	
	
	private boolean isVictory(HantoPlayerColor winnerColor){
		return gameManager.getCellManager().isVictory(winnerColor);
	}

}
