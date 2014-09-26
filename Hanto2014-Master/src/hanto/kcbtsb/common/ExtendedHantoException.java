package hanto.kcbtsb.common;

import hanto.kcbtsb.common.HantoGameManager;
import hanto.common.HantoException;

public class ExtendedHantoException extends HantoException {

	public ExtendedHantoException(String message) {
		super(message);
		HantoGameManager.clearInstance();
	}
	
	public ExtendedHantoException(String message, Throwable cause)
	{
		super(message, cause);
		HantoGameManager.clearInstance();
	}
}
