/**
 * 
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

/**
 * @author Kyle
 *
 */
public class HantoPlayer {
	
	private final HantoPlayerColor playerColor;
	
	private final List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
	
	private int turnCounter;
	
	/**
	 * 
	 * @param color
	 */
	public HantoPlayer(final HantoPlayerColor color){
		playerColor = color;
		pieceLineup.addAll(HantoGameManager.getInstance().getPieceLineup());
		turnCounter = 1;
	}
	
	/**
	 * 
	 * @param pieceType
	 * @param destCell
	 * @return result of piece placement
	 * @throws HantoException
	 */
	public MoveResult placePiece(final HantoPieceType pieceType, final HantoCell destCell) 
	throws HantoException{
		MoveResult result = null;
		int turn = HantoGameManager.getInstance().getTurnCount();
		if( HantoGameManager.getInstance().getPlayerTurn() == playerColor){
			if (turn == 1 && (destCell.getX() != 0 || destCell.getY() != 0)){
				throw new HantoException("Player must place first piece at 0,0");
			} else if (turnCounter >= 7 && pieceLineup.contains(HantoPieceType.BUTTERFLY)){
				throw new HantoException("Player must place butterfly by fourth turn.");
			}
			
			result = HantoGameManager.getInstance().getGame().makeMove(pieceType, null, destCell);
			pieceLineup.remove(pieceType);
			HantoGameManager.getInstance().nextTurn();
		}
		else{
			throw new HantoException("It is not this player's turn");
		}
		
		return result;
	}
	
	public int getPieceCount(){
		return pieceLineup.size();
	}
	
	public HantoPlayerColor getPlayerColor(){
		return playerColor;
	}
}
