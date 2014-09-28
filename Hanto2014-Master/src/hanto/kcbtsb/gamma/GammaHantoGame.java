/**
 * G
 */
package hanto.kcbtsb.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.common.HantoBaseGame;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;

/**
 * 
 * @author kcbryant
 *
 */
public class GammaHantoGame extends HantoBaseGame {
	HantoGameManager gameManager;
	
	public GammaHantoGame(HantoPlayerColor color){
		super(color);
		gameManager = HantoGameManager.getInstance();
		gameManager.setGame(this);
		gameManager.addPieceToLineup(HantoPieceType.SPARROW, 5);
		gameManager.addPieceToLineup(HantoPieceType.BUTTERFLY, 1);
		gameManager.setUp();
	}
	
	@Override
	protected void movePiece(HantoCoordinate from, HantoCoordinate to, HantoPieceType pieceType) throws HantoException{
		HantoCell toCell = new HantoCell(to.getX(), to.getY());
		
		if (from == null && gameManager.getCellManager().isContiguousToEnemy(toCell, gameManager.getPlayerTurn())){
			if (gameManager.getTurnCount() > 2){
				throw new HantoException("Can't place piece next to enemy.");
			}
		} else if (from != null){
			HantoCell fromCell = new HantoCell(from.getX(), from.getY());
			gameManager.getCellManager().remCell(fromCell);
			if (!gameManager.getCellManager().isLegalMovement(fromCell, toCell, pieceType)){
				throw new HantoException("Illegal Movement");
			}
		}
		super.movePiece(from, to, pieceType);
	}
	
	@Override
	protected MoveResult postCheck(HantoPieceType pieceType){
		
		MoveResult mr = super.postCheck(pieceType);
		if(mr == MoveResult.OK && gameManager.getTurnCount() >= 20){
			mr = MoveResult.DRAW;
		}
		
		return mr;
	}
}
