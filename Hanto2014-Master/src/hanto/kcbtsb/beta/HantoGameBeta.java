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
	}
	
	@Override
	public MoveResult makeMove(final HantoPieceType pieceType, final HantoCoordinate from,
	final HantoCoordinate to) throws HantoException{
		if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			throw new HantoException("Cell is already occupied.");
		} else if (!isContiguous){
			if (to.getX() != 0 && to.getY() != 0){
				throw new HantoException("Cell is not contiguous to another piece");
			}
		}
		gameManager.setColorTurn(gameManager.getColorTurn().getNext());
		gameManager.getCellManager().addCell(to.getX(), to.getY());
		return MoveResult.OK;
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
