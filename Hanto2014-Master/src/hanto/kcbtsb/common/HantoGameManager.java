/**
 * 
 */
package hanto.kcbtsb.common;

import hanto.common.HantoGame;
import hanto.common.HantoPlayerColor;

/**
 * 
 * @author Kyle
 *
 */
public class HantoGameManager {
	
	private static HantoGameManager instance;
	
	private HantoPlayer bluePlayer;
	
	private HantoCellManager cellManager;
	
	private HantoPlayerTurn colorTurn;
	
	private HantoGame gameType;
	
	
	@SuppressWarnings("unused")
	private static void HantoGameManger(){}

	/**
	 * 
	 * @return instance of HantoGameManager
	 */
	public static HantoGameManager getInstance(){
		if(instance == null){
			instance = new HantoGameManager();
		}
		return instance;
	}
	
	/**
	 * 
	 * @return HantoPlayerColor what colors turn
	 */
	public HantoPlayerColor getPlayerTurn(){
		HantoPlayerColor color = null;
		switch(colorTurn){
		case BLUE:
			color = HantoPlayerColor.BLUE;
			break;
		case RED:
			color = HantoPlayerColor.RED;
			break;
		default:
			break;
		}
		return color;
	}
	
	
	public HantoPlayer getRedPlayer() {
		return redPlayer;
	}


	public void setRedPlayer(final HantoPlayer redPlayer) {
		this.redPlayer = redPlayer;
	}


	public HantoPlayer getBluePlayer() {
		return bluePlayer;
	}


	public void setBluePlayer(final HantoPlayer bluePlayer) {
		this.bluePlayer = bluePlayer;
	}


	public HantoCellManager getCellManager() {
		return cellManager;
	}


	public void setCellManager(final HantoCellManager cellManager) {
		this.cellManager = cellManager;
	}


	public HantoPlayerTurn getColorTurn() {
		return colorTurn;
	}


	public void setColorTurn(final HantoPlayerTurn colorTurn) {
		this.colorTurn = colorTurn;
	}


	public HantoGame getGameType() {
		return gameType;
	}


	public void setGameType(final HantoGame gameType) {
		this.gameType = gameType;
	}


	private HantoPlayer redPlayer;

}
