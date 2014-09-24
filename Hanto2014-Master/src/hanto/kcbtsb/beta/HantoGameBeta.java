/**
 * 
 */
package hanto.kcbtsb.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoCellManager;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.common.HantoPlayer;
import hanto.kcbtsb.common.HantoPlayerTurn;

/**
 * @author Kyle
 *
 */
public class HantoGameBeta implements HantoGame {
	/**
	 * Constructor for HantoGameAlpha.
	 */
	HantoGameManager gameManager;
	
	public HantoGameBeta(){
		gameManager = HantoGameManager.getInstance();
		gameManager.setGameType(this);
		gameManager.setCellManager(new HantoCellManager());
		gameManager.setColorTurn(HantoPlayerTurn.BLUE);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.addPieceToLineup(HantoPieceType.SPARROW, 4);
		gameManager.setBluePlayer(new HantoPlayer(HantoPlayerColor.BLUE));
		gameManager.setRedPlayer(new HantoPlayer(HantoPlayerColor.RED));
		gameManager.setTurnCount(1);
	}
	
	@Override
	public MoveResult makeMove(final HantoPieceType pieceType, final HantoCoordinate from,
	final HantoCoordinate to) throws HantoException{
		
		MoveResult result = MoveResult.OK;
		if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			throw new HantoException("Cell is already occupied.");
		} else if (!gameManager.getCellManager().isContiguous(to.getX(), to.getY())){
			if (gameManager.getTurnCount() != 1){
				throw new HantoException("Cell is not contiguous to another piece");
			}
		}
		
		gameManager.getCellManager().addCell(to.getX(), to.getY(), pieceType);
		
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
		
		gameManager.setColorTurn(gameManager.getColorTurn().getNext());
		
		return result;
	}
	
	private boolean isVictory(){
		return gameManager.getCellManager().isVictory(gameManager.getPlayerTurn());
	}
	

	@Override
	public HantoPiece getPieceAt(final HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
