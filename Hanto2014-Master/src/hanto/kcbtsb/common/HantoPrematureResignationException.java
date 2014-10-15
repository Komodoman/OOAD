/**
 * @author Kyle Bryant and Tim Bujnevicie
 */

package hanto.kcbtsb.common;

import hanto.common.HantoException;

/**
 * Hanto Exception specifically for dealing with resigning early
 * @author tsbujnevicie
 *
 */
public class HantoPrematureResignationException extends HantoException {

	public HantoPrematureResignationException(String message) {
		super(message);
	}

}
