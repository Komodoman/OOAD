/**
 * 
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.alpha.HantoGameAlpha;

/**
 * @author Kyle
 *
 */
public class HantoPlayer {
	
	private HantoPlayerColor playerColor;
		
	public HantoPlayer(HantoPlayerColor color){
		playerColor = color;
	}
	
	public MoveResult placePiece(HantoPieceType pieceType, HantoCell destCell) throws HantoException{
		MoveResult result = null;
		if( HantoGameManager.getInstance().getPlayerTurn() == playerColor){
			result = HantoGameManager.getInstance().getGameType().makeMove(pieceType, (HantoCoordinate) new HantoCell(), destCell);
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
