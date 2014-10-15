/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/


package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

/**
 * Base Realization of HantoGame
 * @author tsbujnevicie
 *
 */
public abstract class HantoBaseGame implements HantoGame {
	
	private HantoGameManager gameManager;
	
	/**
	 * Constructor encapulating the shared behavoir of all hanto games
	 * @param firstTurnColor
	 */
	protected HantoBaseGame(HantoPlayerColor firstTurnColor){
		HantoGameManager.clearInstance();
		gameManager = HantoGameManager.getInstance();
		gameManager.initialize();
		gameManager.setColorTurn(firstTurnColor);
		gameManager.setTurnCount(1);
		
	}

	/**
	 * @see HantoPiece.makeMove
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
		HantoCoordinate to) throws HantoException {
		
		preCheck(from, to);
		movePiece(from, to, pieceType);
		return postCheck(pieceType);
	}
	
	/**
	 * @see HantoPiece.getPieceAt
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return gameManager.getCellManager().getCellPiece(where.getX(), where.getY());
	}
	
	/**
	 * <h2>Base Hanto Move Piece </h2>
	 * <p>Either moves or places a Hanto Piece.  If placing a piece, performs a check to make sure
	 * the player has the piece to place <p>
	 * @param from coordinate
	 * @param to coordinate
	 * @param pieceType
	 * @throws HantoException
	 */
	protected void movePiece(HantoCoordinate from, HantoCoordinate to, HantoPieceType pieceType) throws HantoException{
		if (!getCurrentPlayer().getPiecesRemaining().contains(pieceType) && from == null){
			throw new HantoException("None of those pieces remaining");
		}
		HantoCell toCell = new HantoCell(to.getX(), to.getY());
		HantoBasePiece piece = generatePiece(from, pieceType);
		
		if (from == null && gameManager.getCellManager().isAdjacentToEnemy(toCell, gameManager.getPlayerTurn())){
			if (gameManager.getTurnCount() > 2){
				throw new HantoException("Can't place piece next to enemy.");
			}
		} else if (from != null){
			HantoCell fromCell = new HantoCell(from.getX(), from.getY());
			gameManager.getCellManager().remCell(fromCell);
			if (!gameManager.getCellManager().isLegalMovement(fromCell, toCell, piece)){
				throw new HantoException("Illegal Movement");
			}
		}
		gameManager.getCellManager().addCell(to.getX(), to.getY(), HantoPieceFactory.makeHantoPiece(pieceType, gameManager.getPlayerTurn()));
	}
	
	/**
	 * <h2>Base Hanto Pre-Check </h2>
	 * <p>Performs the checks required before moving or placing a Hanto Piece.<p> 
	 * @param from coordinate
	 * @param to coordinate
	 * @throws HantoException
	 */
	protected void preCheck(HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		int turn = HantoGameManager.getInstance().getTurnCount();
		if (turn == 1 && (to.getX() != 0 || to.getY() != 0)){
			throw new HantoException("Player must place first piece at 0,0");
		} else if (turn >= 7 && getCurrentPlayer().getPiecesRemaining().contains(HantoPieceType.BUTTERFLY)){
			throw new HantoException("Player must place butterfly by fourth turn.");
		}
		if(from != null)
		{
			if(!gameManager.getCellManager().isCellOccupied(from.getX(), from.getY()))
			{
				throw new HantoException("From cell isn't occupied.");
			}
		}
		if (gameManager.getCellManager().isCellOccupied(to.getX(), to.getY())){
			throw new HantoException("Cell is already occupied.");
		} else if (!gameManager.getCellManager().isAdjacent(to.getX(), to.getY())){
			if (gameManager.getTurnCount() != 1){
				throw new HantoException("Cell is not contiguous to another piece");
			}
		}
	}
	
	/**
	 * <h2>Base Hanto Post-Check </h2>
	 * <p>Performs the checks required after moving or placing a Hanto Piece.
	 * This includes checking if the move resulted in a win or a draw, and
	 * incementing turn.</p>
	 * @param pieceType
	 * @return moveResult
	 */
	protected MoveResult postCheck(HantoPieceType pieceType){
		MoveResult result = MoveResult.OK;
		
		if (isVictory(HantoPlayerColor.BLUE)){
			result = MoveResult.BLUE_WINS;
		} else if (isVictory(HantoPlayerColor.RED)){
			result = MoveResult.RED_WINS;
		} else if (gameManager.getBluePlayer().getPieceCount() == 0){
			result = MoveResult.DRAW;
		} else {
			getCurrentPlayer().removePieceFromLineup(pieceType);
			HantoGameManager.getInstance().nextTurn();
		}
		return result;
	}
	/**
	 * <h2>Base Hanto Piece Generator </h2>
	 * <p> Generates a piece for placing, or pulls the piece instance from 
	 * its corresponding HantoCell<p>
	 * @param from Coordinate
	 * @param type of Hanto Piece
	 * @return HantoPiece
	 * @throws HantoException
	 */
	protected HantoBasePiece generatePiece(HantoCoordinate from, HantoPieceType type) throws HantoException
	{
		HantoBasePiece piece = null;
		if(from == null)
		{
			piece = HantoPieceFactory.makeHantoPiece(type, gameManager.getPlayerTurn());
		}
		else
		{
			piece = (HantoBasePiece) gameManager.getCellManager().getCellPiece(from.getX(), from.getY());

			if(piece.getType() != type)
			{
				throw new HantoException("Type does not match piece specified");
			}
		}
		return piece;
	}
	
	public HantoGameManager getManager(){
		return gameManager;
	}
	
	/**
	 * <h2>Base Hanto Get Current Player </h2>
	 * <p> Returns the player who currently allowed to move or place pieces
	 * @return HantoPlayer
	 */
	public HantoBasePlayer getCurrentPlayer(){
		HantoBasePlayer curPlayer = null;
		switch(gameManager.getPlayerTurn()){
		case BLUE:
			curPlayer = gameManager.getBluePlayer();
			break;
		case RED:
			curPlayer = gameManager.getRedPlayer();
			break;
		}
		
		return curPlayer;
	}
	
	/**
	 * <h2>Base Hanto Victory Check </h2>
	 * <p> Checks the hanto board to see if any player has won </p>
	 * @param winnerColor
	 * @return RED_WINS <br> BLUE_WINS <br> DRAW
	 */
	private boolean isVictory(HantoPlayerColor winnerColor){
		return gameManager.getCellManager().isVictory(winnerColor);
	}

}
