/**
 * @author Kyle
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * <p> Singleton game manager that holds all the data used in the game.
 * Keeps track of current turn, player instances, cell manager instances, 
 * pieces allowed to be used each game, and what iteration the game is.
 * </p>
 */
public class HantoGameManager {
	private static HantoGameManager instance = null;
	
	private HantoPlayer redPlayer;
	
	private HantoPlayer bluePlayer;
	
	private HantoCellManager cellManager;
	
	private HantoPlayerTurn colorTurn;
	
	private HantoGameID gameType;
	
	private HantoGame game;
	
	private int turnCounter;
	
	private List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();
	
	//Static instance of the Singleton
	
	@SuppressWarnings("unused")
	private void HantoGameManger(){}

	/**
	 * Creates an instance of HantoGameManager if none exists.
	 * If there is one, it will return that instance.
	 * @return instance of HantoGameManager
	 */
	public static HantoGameManager getInstance(){
		if(instance == null){
			instance = new HantoGameManager();
		}
		return instance;
	}
	
	public static void clearInstance(){
		instance = null;
	}
	
	/**
	 * Toggles between red turn and blue turn each time this method is
	 * called
	 * 
	 * @return HantoPlayerColor current turn
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
	
	/**
	 * Getter for Red Player instance
	 * @return
	 * 		Red Player instance
	 */
	public HantoPlayer getRedPlayer() {
		return redPlayer;
	}

	/**
	 * Setter for Red Player instance
	 * @param redPlayer
	 * 		instance to be set as Red Player
	 */
	public void setRedPlayer(final HantoPlayer redPlayer) {
		this.redPlayer = redPlayer;
	}

	/**
	 * Getter for Blue Player instance
	 * @return
	 * 		Blue Player instance
	 */
	public HantoPlayer getBluePlayer() {
		return bluePlayer;
	}

	/**
	 * Setter for Blue Player instance
	 * @param redPlayer
	 * 		instance to be set as Blue Player
	 */
	public void setBluePlayer(final HantoPlayer bluePlayer) {
		this.bluePlayer = bluePlayer;
	}

	/**
	 * Getter for cell manager instance
	 * @return
	 * 		cell manager instance
	 */
	public HantoCellManager getCellManager() {
		return cellManager;
	}

	/**
	 * Setter for cell manager instance
	 * @param redPlayer
	 * 		instance to be set as cell manager
	 */
	public void setCellManager(final HantoCellManager cellManager) {
		this.cellManager = cellManager;
	}

	/**
	 * Getter for what the current turn is
	 * @return
	 * 		current turn color
	 */
	public HantoPlayerTurn getColorTurn() {
		return colorTurn;
	}

	/**
	 * Setter for what the current turn is
	 * @param redPlayer
	 * 		color to be set as current turn
	 */
	public void setColorTurn(final HantoPlayerTurn colorTurn) {
		this.colorTurn = colorTurn;
	}

	/**
	 * Getter for game type instance
	 * @return
	 * 		game type instance
	 */
	public HantoGameID getGameType() {
		return gameType;
	}

	/**
	 * Setter for game type instance
	 * @param redPlayer
	 * 		instance to be set as game type
	 */
	public void setGameType(final HantoGameID gameType) {
		this.gameType = gameType;
	}
	
	
	/**
	 * Getter for game type instance
	 * @return
	 * 		game type instance
	 */
	public HantoGame getGame() {
		return game;
	}

	/**
	 * Setter for game type instance
	 * @param redPlayer
	 * 		instance to be set as game type
	 */
	public void setGame(final HantoGame aGame) {
		game = aGame;
	}
	
	public int getTurnCount(){
		
		return turnCounter;
	}
	
	public void setTurnCount(int turn){
		turnCounter = turn;
	}
	
	/**
	 * increments the value of turnCounter
	 */
	public void nextTurn(){
		turnCounter++;
	}
	
	/**
	 * Getter for piece line up
	 * @return
	 * 		piece line up array instance
	 */
	public List<HantoPieceType> getPieceLineup(){
		return pieceLineup;
	}
	
	/**
	 * Adds a set amount of a specific {@link HantoPieceType} to the array of 
	 * pieces that can be used
	 * @param aPiece
	 * 			piece type
	 * @param amount
	 * 			number to be added.
	 */
	public void addPieceToLineup(final HantoPieceType aPiece, final int amount){
		for (int i = 0; i < amount; i++){
			pieceLineup.add(aPiece);
		}
	}


	

}
