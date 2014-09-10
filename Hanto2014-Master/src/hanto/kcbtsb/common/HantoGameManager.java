package hanto.kcbtsb.common;

import hanto.common.HantoGame;
import hanto.common.HantoPlayerColor;

public class HantoGameManager {
	
	public static HantoPlayer redPlayer;
	
	public static HantoPlayer bluePlayer;
	
	public static HantoCellManager cellManager;
	
	public static HantoPlayerTurn colorTurn;
	
	public static HantoGame gameType;
	
	
	public static HantoPlayerColor getPlayerTurn(){
		HantoPlayerColor color = null;
		switch(colorTurn){
		case BLUE:
			color = HantoPlayerColor.BLUE;
			break;
		case RED:
			color = HantoPlayerColor.RED;
			break;
		}
		return color;	
	}
}
