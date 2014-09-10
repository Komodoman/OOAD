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
	
	private ArrayList<HantoPieceType> allPieces;
	
	public HantoPlayer(HantoPlayerColor color, ArrayList<HantoPieceType> pieces){
		playerColor = color;
		allPieces = pieces;
	}
	
	public MoveResult placePiece(HantoPieceType pieceType, HantoCell destCell) throws HantoException{
		MoveResult result = null;
		if( HantoGameManager.getPlayerTurn() == playerColor){
			result = HantoGameManager.gameType.makeMove(pieceType, (HantoCoordinate) new HantoCell(), destCell);
		}
		else{
			throw new HantoException("It is not this player's turn");
		}
		return result;
	}
	
	public MoveResult movePiece(HantoPiece aPiece){
		MoveResult result = null;
		return result;
	}
	
	public HantoPlayerColor getPlayerColor(){
			return playerColor;
	}
	
	public int getPiecesLeft(){
		return allPieces.size();
	}
	
	public void removePiece(HantoPieceType aPiece){
		allPieces.remove(aPiece);
	}
}
