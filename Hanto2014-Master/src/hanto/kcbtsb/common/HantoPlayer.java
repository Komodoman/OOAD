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
	
	private List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
	
	/**
	 * 
	 * @param color
	 */
	public HantoPlayer(final HantoPlayerColor color, List<HantoPieceType> pieces){
		playerColor = color;
		pieceLineup.addAll(pieces);
	}
	
	/**
	 * 
	 * @param color
	 */
	public HantoPlayer(final HantoPlayerColor color){
		playerColor = color;
		pieceLineup = null;
	}
	
	
	public int getPieceCount(){
		return pieceLineup.size();
	}
	
	public ArrayList<HantoPieceType> getPiecesRemaining(){
		return (ArrayList<HantoPieceType>) pieceLineup;
	}
	
	public void removePieceFromLineup(HantoPieceType pieceType){
		pieceLineup.remove(pieceType);
	}
	
	public HantoPlayerColor getPlayerColor(){
		return playerColor;
	}
}
