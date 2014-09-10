package hanto.kcbtsb.common;

import hanto.common.HantoGame;
import hanto.common.HantoPlayerColor;

public class HantoGameManager {
	
	private static HantoGameManager instance;
	
	private HantoPlayer bluePlayer;
	
	private HantoCellManager cellManager;
	
	private HantoPlayerTurn colorTurn;
	
	private HantoGame gameType;
	
	
	private void HantoGameManger(){
		
	}

	
	public static HantoGameManager getInstance(){
		if(instance == null){
			instance = new HantoGameManager();
		}
		return instance;
	}
	
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


	public void setRedPlayer(HantoPlayer redPlayer) {
		this.redPlayer = redPlayer;
	}


	public HantoPlayer getBluePlayer() {
		return bluePlayer;
	}


	public void setBluePlayer(HantoPlayer bluePlayer) {
		this.bluePlayer = bluePlayer;
	}


	public HantoCellManager getCellManager() {
		return cellManager;
	}


	public void setCellManager(HantoCellManager cellManager) {
		this.cellManager = cellManager;
	}


	public HantoPlayerTurn getColorTurn() {
		return colorTurn;
	}


	public void setColorTurn(HantoPlayerTurn colorTurn) {
		this.colorTurn = colorTurn;
	}


	public HantoGame getGameType() {
		return gameType;
	}


	public void setGameType(HantoGame gameType) {
		this.gameType = gameType;
	}


	private HantoPlayer redPlayer;

}
