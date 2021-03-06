/**
 * @author Kyle Bryant and Tim Bujnevicie
 */
package hanto.kcbtsb.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentkcbtsb.tournament.HantoPlayer;

/**
 * <p> Singleton game manager that holds all the data used in the game.
 * Keeps track of current turn, player instances, cell manager instances, 
 * pieces allowed to be used each game, and what iteration the game is.
 * </p>
 */
public class HantoGameManager {
	private static HantoGameManager instance = null;
	
	private HantoBasePlayer redPlayer;
	
	private HantoBasePlayer bluePlayer;
	
	private HantoCellManager cellManager;
		
	private HantoPlayerTurn colorTurn;
	
	private HantoGame game;
	
	private int turnCounter;
	
	private List<HantoPieceType> pieceLineup = new ArrayList<HantoPieceType>();

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
	/**
	 * Clears the Instance of the Game Manager Singleton
	 */
	public static void clearInstance(){
		instance = null;
	}
	/**
	 * Generate a cell manager
	 */
	public void initialize(){
		cellManager = new HantoCellManager(this);
	}
	
	/**
	 * Set up blue and red players
	 */
	public void setUp(){
		bluePlayer = new HantoBasePlayer(HantoPlayerColor.BLUE, pieceLineup);
		redPlayer = new HantoBasePlayer(HantoPlayerColor.RED, pieceLineup);
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
	public HantoBasePlayer getRedPlayer() {
		return redPlayer;
	}
	/**
	 * Setter for the RedP Player Instance
	 * @param p
	 */
	public void setRedPlayer(HantoBasePlayer p){
		redPlayer = p;
	}

	/**
	 * Getter for Blue Player instance
	 * @return
	 * 		Blue Player instance
	 */
	public HantoBasePlayer getBluePlayer() {
		return bluePlayer;
	}
	/**
	 * Setter for the RedP Player Instance
	 * @param p
	 */
	public void setBluePlayer(HantoBasePlayer p){
		bluePlayer = p;
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
	 * @param colorTurn
	 * 		color to be set as current turn
	 */
	public void setColorTurn(final HantoPlayerColor colorTurn) {
		switch(colorTurn){
		case BLUE:
			this.colorTurn = HantoPlayerTurn.BLUE;
			break;
		case RED:
			this.colorTurn = HantoPlayerTurn.RED;
			break;
		}
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
		colorTurn = colorTurn.getNext();
		turnCounter++;
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
	
	/**
	 * Adds a set amount of a specific {@link HantoPieceType} to the array of 
	 * pieces that can be used
	 * @param aPiece
	 * 			piece type
	 * @param amount
	 * 			number to be added.
	 * @param moveType
	 * 			type of move the piece has
	 * @param moveDist
	 * 			distance a piece can move;
	 */
	public void addPieceToLineup(final HantoPieceType aPiece, final int amount, final HantoMove moveType, final int moveDist){
		for (int i = 0; i < amount; i++){
			pieceLineup.add(aPiece);
		}
		HantoPieceFactory.setupPiece(aPiece, moveType, moveDist);
	}
}
