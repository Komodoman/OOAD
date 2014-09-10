package hanto.kcbtsb.common;

import hanto.common.HantoCoordinate;

import java.util.ArrayList;

public class HantoCellManager {
	
	private ArrayList<HantoCell> occupiedCells;
	
	public HantoCellManager(){
		occupiedCells = new ArrayList<HantoCell>();
	}
	
	public boolean isCellOccupied(int x, int y){
		boolean isOccupied = false;

		for (int i = 0; i < occupiedCells.size(); i++){
			if (occupiedCells.get(i).getX() == x && occupiedCells.get(i).getY() == y){
				isOccupied = true;
				break;
			}
		}
		
		return isOccupied;
	}
	
	public boolean isEmpty(){
		return occupiedCells.isEmpty();
	}
	
	public void addCell(int x, int y){
		occupiedCells.add(new HantoCell(x, y));
	}
}
