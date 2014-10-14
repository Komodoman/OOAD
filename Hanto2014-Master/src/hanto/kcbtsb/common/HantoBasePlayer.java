/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author Kyle
 *
 */
public class HantoBasePlayer {
	
	private final HantoPlayerColor playerColor;
	
	private List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
	
	/**
	 * <p> Constructor for HantoPlayer
	 * @param color
	 * @param pieces
	 */
	public HantoBasePlayer(final HantoPlayerColor color, List<HantoPieceType> pieces){
		playerColor = color;
		pieceLineup.addAll(pieces);
	}
	
	/**
	 * <p> Constructor for HantoPlayer
	 * @param color
	 */
	public HantoBasePlayer(final HantoPlayerColor color){
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
		return pieceLineup;
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
