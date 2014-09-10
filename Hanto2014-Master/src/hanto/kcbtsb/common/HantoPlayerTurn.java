package hanto.kcbtsb.common;

public enum HantoPlayerTurn {
	BLUE, RED;
	
	public HantoPlayerTurn getNext() {
		return values()[(ordinal() + 1) % values().length];
	}
}
