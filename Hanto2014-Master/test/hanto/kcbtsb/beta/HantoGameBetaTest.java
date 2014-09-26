package hanto.kcbtsb.beta;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.HantoGameFactory;
import hanto.kcbtsb.common.HantoCell;
import hanto.kcbtsb.common.HantoGameManager;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * The class <code>HantoGameBetaTest</code> contains tests for the class {@link
 * <code>HantoGameBeta</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 9/14/14 1:12 PM
 *
 * @author Kyle
 *
 * @version $Revision$
 */
public class HantoGameBetaTest {
	
    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
       Field instance = HantoGameManager.class.getDeclaredField("instance");
       instance.setAccessible(true);
       instance.set(null, null);
    }
	
	@Test(expected=HantoException.class)
	public void blueShouldFailPlaceSparrowOnFourth() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 0));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 1));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 2));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 3));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 4));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 5));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 6));
	}
	
	@Test(expected=HantoException.class)
	public void redShouldFailPlaceSparrowOnOccupiedCell() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 0));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 0));
	}
	
	@Test
	public void blueShouldPlaceASparrow() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		MoveResult result = betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 0));
		assertTrue("Should place sparrow: ", result == MoveResult.OK);
	}
	
	@Test(expected=HantoException.class)
	public void blueShouldFailPlaceFirstSparrow() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(2, 0));
	}
	
	@Test(expected=HantoException.class)
	public void redShouldFailPlaceNonContiguousSparrow() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 0));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 3));
	}
	
	@Test
	public void gameShouldTie() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		betaHanto.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCell(0, 0));
		betaHanto.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCell(0, 1));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 2));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 3));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 4));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 5));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 6));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 7));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 8));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 9));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 10));
		MoveResult result = betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 11));
		assertTrue("Game should Draw: ", result == MoveResult.DRAW);
	}
	
	@Test
	public void redShouldWin() throws HantoException {
		HantoGame betaHanto = HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		betaHanto.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCell(0, 0));
		betaHanto.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCell(0, -1));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, -2));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(-1, 0));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, -3));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(-1, 1));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, -4));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, 1));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, -5));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(1, 0));
		betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(0, -6));
		MoveResult result = betaHanto.makeMove(HantoPieceType.SPARROW, null, new HantoCell(1, -1));
		assertTrue("Red should win: ", result == MoveResult.RED_WINS);
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
 * package = hanto.kcbtsb.beta
 * package.sourceFolder = Hanto2014-Master/test
 * superclassType = junit.framework.TestCase
 * testCase = HantoGameBetaTest
 * testClassType = hanto.kcbtsb.beta.HantoGameBeta
 */