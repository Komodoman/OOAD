package hanto.kcbtsb.common;

import static org.junit.Assert.*;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import org.junit.Test;

/**
 * The class <code>HantoGameManagerTest</code> contains tests for the class
 * {@link <code>HantoGameManager</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 9/10/14 1:51 PM
 *
 * @author kcbryant
 *
 * @version $Revision$
 */
public class HantoPieceFactoryTest {
	

	@Test
	public void shouldMakeBlueButterfly(){
		HantoPiece butterfly = HantoPieceFactory.makeHantoPiece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		assertTrue("Should be butterfly:", butterfly.getType() == HantoPieceType.BUTTERFLY);
		assertTrue("Should be blue:", butterfly.getColor() == HantoPlayerColor.BLUE);
	}
	
	@Test (expected = NullPointerException.class)
	public void shouldFailMakeNull(){
		HantoPieceFactory.makeHantoPiece(null, null);
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
 * testCase = HantoGameManagerTest
 * testClassType = HantoGameManager
 */