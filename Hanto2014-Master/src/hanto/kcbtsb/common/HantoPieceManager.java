package hanto.kcbtsb.common;

import hanto.common.HantoPieceType;

public class HantoPieceManager {

	private int SPARROW_MOVE_DISTANCE = 2;
	private int BUTTERFLY_MOVE_DISTANCE = 1;
	private int CRAB_MOVE_DISTANCE = 1;
	
	private HantoMove SPARROW_MOVE_TYPE = HantoMove.FLY;
	private HantoMove BUTTERFLY_MOVE_TYPE = HantoMove.WALK;
	private HantoMove CRAB_MOVE_TYPE = HantoMove.WALK;
	
	public void setMoveType(HantoPieceType pieceType, HantoMove aMove){
		switch(pieceType){
		case BUTTERFLY:
			BUTTERFLY_MOVE_TYPE = aMove;
			break;
		case CRAB:
			CRAB_MOVE_TYPE = aMove;
			break;
		case SPARROW:
			SPARROW_MOVE_TYPE = aMove;
			break;
		default:
			break;
		
		}
	}
	
	public void setMoveDistance(HantoPieceType pieceType, int distance){
		switch(pieceType){
		case BUTTERFLY:
			BUTTERFLY_MOVE_DISTANCE = distance;
			break;
		case CRAB:
			CRAB_MOVE_DISTANCE = distance;
			break;
		case SPARROW:
			SPARROW_MOVE_DISTANCE = distance;
			break;
		default:
			break;
		
		}
	}
	
	public HantoMove getMoveType(HantoPieceType pieceType){
		HantoMove moveType = HantoMove.WALK;
		switch(pieceType){
		case BUTTERFLY:
			moveType = BUTTERFLY_MOVE_TYPE;
			break;
		case CRAB:
			moveType = CRAB_MOVE_TYPE;
			break;
		case SPARROW:
			moveType = SPARROW_MOVE_TYPE;
			break;
		default:
			break;
		
		}
		return moveType;
	}
	
	public int getMoveDistance(HantoPieceType pieceType){
		int moveDistance = 1;
		switch(pieceType){
		case BUTTERFLY:
			moveDistance = BUTTERFLY_MOVE_DISTANCE;
			break;
		case CRAB:
			moveDistance = CRAB_MOVE_DISTANCE;
			break;
		case SPARROW:
			moveDistance = SPARROW_MOVE_DISTANCE;
			break;
		default:
			break;
		
		}
		return moveDistance;
	}
}
