/**
 * 
 */
package hanto.kcbtsb.common;

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
	
	/**
	 * 
	 * @param color
	 */
	public HantoPlayer(final HantoPlayerColor color){
		playerColor = color;
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
		if( HantoGameManager.getInstance().getPlayerTurn() == playerColor){
			result = HantoGameManager.getInstance().getGameType().makeMove
			(pieceType, new HantoCell(), destCell);
		}
		else{
			throw new HantoException("It is not this player's turn");
		}
		return result;
	}
	
	public HantoPlayerColor getPlayerColor(){
		return playerColor;
	}

}
