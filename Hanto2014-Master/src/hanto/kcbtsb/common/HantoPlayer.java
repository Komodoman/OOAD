/**
 * @author Kyle Bryant and Tim Bujnevicie
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
	 * <p> Constructor for HantoPlayer
	 * @param color
	 * @param pieces
	 */
	public HantoPlayer(final HantoPlayerColor color, List<HantoPieceType> pieces){
		playerColor = color;
		pieceLineup.addAll(pieces);
	}
	
	/**
	 * <p> Constructor for HantoPlayer
	 * @param color
	 */
	public HantoPlayer(final HantoPlayerColor color){
		playerColor = color;
		pieceLineup = null;
	}
	
	/**
	 * <p> Piece Count Getter </p>
	 * @return size of piece lineup
	 */
	public int getPieceCount(){
		return pieceLineup.size();
	}
	
	/**
	 * <p> Pieces Remaining getter
	 * @return piece lineup
	 */
	public List<HantoPieceType> getPiecesRemaining(){
		return (ArrayList<HantoPieceType>) pieceLineup;
	}
	/**
	 * <p> Removes Piece from Lineup
	 * @param pieceType
	 */
	public void removePieceFromLineup(HantoPieceType pieceType){
		pieceLineup.remove(pieceType);
	}
	/**
	 * Getter for Player Color
	 * @return
	 */
	public HantoPlayerColor getPlayerColor(){
		return playerColor;
	}
}
