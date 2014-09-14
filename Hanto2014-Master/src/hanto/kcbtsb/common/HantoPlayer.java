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
	
	private int turnCounter;
	
	private final List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
	
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
		if( HantoGameManager.getInstance().getPlayerTurn() == playerColor){
			if (turnCounter == 4 && pieceLineup.contains(HantoPieceType.BUTTERFLY)){
				throw new HantoException("Player must place butterfly by fourth turn.");
			}
			result = HantoGameManager.getInstance().getGameType().makeMove
			(pieceType, new HantoCell(), destCell);
			pieceLineup.remove(pieceType);
			turnCounter++;
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
