package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

public abstract class HantoBaseGame implements HantoGame {
	
	private HantoGameManager gameManager;
	
	public HantoBaseGame(){
		gameManager = HantoGameManager.getInstance();
		gameManager.setCellManager(new HantoCellManager());
		gameManager.setColorTurn(HantoPlayerTurn.BLUE);
		gameManager.setTurnCount(1);
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		// TODO Auto-generated method stub
		return null;
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
	


	protected void preCheck(HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			throw new HantoException("Cell is already occupied.");
		} else if (!gameManager.getCellManager().isContiguous(to.getX(), to.getY())){
			if (gameManager.getTurnCount() != 1){
				throw new HantoException("Cell is not contiguous to another piece");
			}
		}
	}
	
	protected MoveResult postCheck(){
		MoveResult result = MoveResult.OK;
		
		if (gameManager.getBluePlayer().getPieceCount() == 0){
			result = MoveResult.DRAW;
		}
		
		if (isVictory()){
			//System.out.println("There's a winner");
			switch (gameManager.getPlayerTurn()){
				case BLUE:
					result = MoveResult.BLUE_WINS;
				case RED:
					result = MoveResult.RED_WINS;
			}
		}
		
		HantoGameManager.getInstance().nextTurn();
		return result;
	}
	
	
	private boolean isVictory(){
		return gameManager.getCellManager().isVictory(gameManager.getPlayerTurn());
	}

}
