/**
 * 
 */
package hanto.kcbtsb.common;

public enum HantoPlayerTurn {
	BLUE, RED;
	
	public HantoPlayerTurn getNext() { // $codepro.audit.disable methodShouldBeStatic
		return values()[(ordinal() + 1) % values().length];
	}
}
