package hanto.kcbtsb.common;

import static org.junit.Assert.*;

import java.util.ArrayList;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.kcbtsb.HantoGameFactory;

import org.junit.Test;

/**
 * The class <code>HantoPlayerTest</code> contains tests for the class {@link
 * <code>HantoPlayer</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 9/10/14 2:19 PM
 *
 * @author tsbujnevicie
 *
 * @version $Revision$
 */
public class HantoPlayerTest {

	@Test ()
	public void testHantoPlayerGetter() {
		HantoBasePlayer player = new HantoBasePlayer(HantoPlayerColor.RED);
		assertEquals(HantoPlayerColor.RED, player.getPlayerColor());
		
	}
	
}

/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = 
 * assertTrue = false
 * callTestMethod = true
 * createMain = false
 * createSetUp = false
 * createTearDown = false
 * createTestFixture = false
 * createTestStubs = false
 * methods = 
 * package = hanto.kcbtsb.common
 * package.sourceFolder = Hanto2014-Master/test
 * superclassType = junit.framework.TestCase
 * testCase = HantoPlayerTest
 * testClassType = HantoPlayer
 */