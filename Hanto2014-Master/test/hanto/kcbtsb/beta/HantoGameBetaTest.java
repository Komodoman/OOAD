package hanto.kcbtsb.beta;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
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
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 0));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 1));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 2));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 3));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 4));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 5));
		System.out.println(manager.getTurnCount());
		MoveResult result = manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 6));
	}
	
	@Test(expected=HantoException.class)
	public void redShouldFailPlaceSparrowOnOccupiedCell() throws HantoException {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 0));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 0));
	}
	
	@Test
	public void blueShouldPlaceASparrow() throws HantoException {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		MoveResult result = manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 0));
		System.out.println("____" + result + "____");
		assertTrue("Should place sparrow: ", result == MoveResult.OK);
	}
	
	@Test(expected=HantoException.class)
	public void blueShouldFailPlaceFirstSparrow() throws HantoException {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(1, 0));
	}
	
	@Test(expected=HantoException.class)
	public void redShouldFailPlaceNonContiguousSparrow() throws HantoException {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 0));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 3));
	}
	
	@Test
	public void gameShouldTie() throws HantoException {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		manager.getBluePlayer().placePiece(HantoPieceType.BUTTERFLY, new HantoCell(0, 0));
		manager.getRedPlayer().placePiece(HantoPieceType.BUTTERFLY, new HantoCell(0, -1));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, -2));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 1));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 2));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 3));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 4));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 5));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 6));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 7));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 8));
		MoveResult result = manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 9));
		assertTrue("Game should Draw: ", result == MoveResult.DRAW);
	}
	
	@Test
	public void redShouldWin() throws HantoException {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoGameFactory.getInstance();
		HantoGameFactory.makeHantoGame(HantoGameID.BETA_HANTO);
		manager.getBluePlayer().placePiece(HantoPieceType.BUTTERFLY, new HantoCell(0, 0));
		manager.getRedPlayer().placePiece(HantoPieceType.BUTTERFLY, new HantoCell(0, -1));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, -2));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(-1, 0));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, -3));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(-1, 1));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, -4));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, 1));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, -5));
		manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(1, 0));
		manager.getBluePlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(0, -6));
		MoveResult result = manager.getRedPlayer().placePiece(HantoPieceType.SPARROW, new HantoCell(1, -1));
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