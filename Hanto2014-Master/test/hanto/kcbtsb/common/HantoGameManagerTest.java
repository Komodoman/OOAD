package hanto.kcbtsb.common;

import static org.junit.Assert.*;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.kcbtsb.HantoGameFactory;
import hanto.kcbtsb.epsilon.EpsilonHantoGame;

import org.junit.Test;

/**
 * The class <code>HantoGameManagerTest</code> contains tests for the class
 * {@link <code>HantoGameManager</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 9/10/14 1:51 PM
 *
 * @author tsbujnevicie
 *
 * @version $Revision$
 */
public class HantoGameManagerTest {
	

	@Test (expected = NullPointerException.class)
	public void hantoCellShouldBeOccupied() {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.ALPHA_HANTO);
		manager.setColorTurn(null);
		manager.getPlayerTurn();
	}
	
	@Test
	public void setCellManage()
	{
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.ALPHA_HANTO);
		HantoCellManager cellManager = new HantoCellManager();
		manager.setCellManager(cellManager);
		assertEquals(manager.getCellManager(),cellManager);
	}
	@Test
	public void testGetGame()
	{
		EpsilonHantoGame game =  (EpsilonHantoGame) HantoGameFactory.makeHantoGame(HantoGameID.EPSILON_HANTO);
		assertNotNull(HantoGameManager.getInstance().getGame());
	}
	@Test
	public void testGetTurnColor()
	{
		EpsilonHantoGame game =  (EpsilonHantoGame) HantoGameFactory.makeHantoGame(HantoGameID.EPSILON_HANTO);
		assertNotNull(HantoGameManager.getInstance().getColorTurn());
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