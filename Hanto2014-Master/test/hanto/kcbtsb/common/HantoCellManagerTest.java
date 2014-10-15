package hanto.kcbtsb.common;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPieceType.HORSE;

import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import common.HantoTestGame;
import common.HantoTestGameFactory;
import common.HantoTestGame.PieceLocationPair;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.HantoGameFactory;
import hanto.kcbtsb.common.HantoGameManager;
import hanto.kcbtsb.gamma.GammaHantoTestGame;
import hanto.kcbtsb.epsilon.EpsilonHantoTestGame;
import hanto.studentkcbtsb.tournament.HantoPlayer;

/**
 * The class <code>HantoCellManagerTest</code> contains tests for the class
 * {@link <code>HantoCellManager</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 9/9/14 4:37 PM
 *
 * @author Kyle
 *
 * @version $Revision$
 */
public class HantoCellManagerTest{

	private static HantoTestGameFactory factory;
	private HantoTestGame game;
	private HantoGame epsilonGame; 
	private HantoTestGame epsilonTestGame;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoTestGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		game = (GammaHantoTestGame) factory.makeHantoTestGame(HantoGameID.GAMMA_HANTO);
		epsilonTestGame = (EpsilonHantoTestGame)factory.makeHantoTestGame(HantoGameID.EPSILON_HANTO);
		epsilonGame = epsilonTestGame;
	}
	
	@Test
	public void hantoCellShouldBeOccupied() {
		HantoGameManager manager = HantoGameManager.getInstance();
		manager.getCellManager().addCell(0, 0);
		assertTrue("Should be occupied: ", manager.getCellManager().isCellOccupied(0, 0));
	}
	
	@Test
	public void hantoCellShouldNotBeOccupied() {
		HantoGameManager manager = HantoGameManager.getInstance();
		manager.getCellManager().addCell(0, 0);
		assertFalse("Should not be occupied: ", manager.getCellManager().isCellOccupied(1, 1));
	}
	
	@Test
	public void hantoCellShouldBeOccupiedWithBlueButterfly() {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoCell firstCell = new HantoCell(0, 0, HantoPieceType.BUTTERFLY);
		manager.getCellManager().addCell(firstCell);
		assertTrue(firstCell.getPiece() == manager.getCellManager().getCellPiece(firstCell));
		assertTrue(firstCell.getCellColor() == manager.getCellManager().getCellColor(firstCell));
		assertTrue(firstCell.getCellColor() == manager.getCellManager().getCellColor(firstCell.getX(), firstCell.getY()));
	}
	
	@Test
	public void hantoCellShouldBeEmptyAfterRemoval() {
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoCell firstCell = new HantoCell(0, 0, HantoPieceType.BUTTERFLY);
		manager.getCellManager().addCell(firstCell);
		manager.getCellManager().remCell(firstCell.getX(), firstCell.getY());
		assertTrue(manager.getCellManager().findCell(firstCell) == null);
	}
	
	
	@Test 
	public void checkPossibleMoveTrueWithPieces() {
		
		HantoGameManager manager = HantoGameManager.getInstance();
		HantoCell firstCell = new HantoCell(0, 0, HantoPieceType.BUTTERFLY);
		manager.getCellManager().addCell(firstCell);
		assertTrue(manager.getCellManager().isLegalMovePresent(HantoPlayerColor.RED));
	}
	@Test
	public void checkPossibleMoveTrueWithoutPieces(){
		HantoGameManager manager = HantoGameManager.getInstance();
		ArrayList<HantoPieceType> list= new ArrayList<HantoPieceType>();
		manager.setRedPlayer(new HantoBasePlayer(HantoPlayerColor.RED,list));
		HantoBasePiece bButterfly = new HantoBasePiece(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, HantoMove.WALK, 1);
		HantoBasePiece rButterfly = new HantoBasePiece(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, HantoMove.WALK, 1);
		HantoBasePiece bCrab = new HantoBasePiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB, HantoMove.WALK, 1);
		HantoBasePiece bCrab2 = new HantoBasePiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB, HantoMove.WALK, 1);
		HantoBasePiece rCrab = new HantoBasePiece(HantoPlayerColor.RED, HantoPieceType.CRAB, HantoMove.WALK, 1);
		manager.getCellManager().addCell(0,0,bButterfly);
		manager.getCellManager().addCell(0,1,rButterfly);
		manager.getCellManager().addCell(-1,0,bCrab);
		manager.getCellManager().addCell(1,-1,bCrab2);
		manager.getCellManager().addCell(0,2,rCrab);
		assertTrue(manager.getCellManager().isLegalMovePresent(HantoPlayerColor.RED));
	}
	
	
	@Test
	public void checkHorizontalJump() throws HantoException{
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, 1, 0),
			    plPair(BLUE, SPARROW, -1, 0), plPair(RED, HORSE, 2, 0)
			    
			};
		epsilonTestGame.initializeBoard(board);
		epsilonTestGame.setPlayerMoving(RED);
		epsilonTestGame.setTurnNumber(4);
		assertEquals(epsilonGame.makeMove(HORSE, new TestHantoCoordinate(2,0), new TestHantoCoordinate(-2,0)), MoveResult.OK);
	}
	@Test
	public void checkHorizontalJumpOtherway() throws HantoException{
		
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, -1, 0),
			    plPair(BLUE, SPARROW, 1, 0), plPair(RED, HORSE, -2, 0)
			    
			};
		epsilonTestGame.initializeBoard(board);
		epsilonTestGame.setPlayerMoving(RED);
		epsilonTestGame.setTurnNumber(4);
		assertEquals(epsilonGame.makeMove(HORSE, new TestHantoCoordinate(-2,0), new TestHantoCoordinate(2,0)), MoveResult.OK);
	}
	@Test (expected=HantoException.class)
	public void checkHorizontalJumpBreak() throws HantoException{
		
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, 1, 0),
			    plPair(BLUE, SPARROW, -1, 1), plPair(RED, HORSE, 2, 0),
			    plPair(RED,SPARROW,-2,1)
			    
			};
		epsilonTestGame.initializeBoard(board);
		epsilonTestGame.setPlayerMoving(RED);
		epsilonTestGame.setTurnNumber(4);
		assertEquals(epsilonGame.makeMove(HORSE, new TestHantoCoordinate(2,0), new TestHantoCoordinate(-2,0)), MoveResult.OK);
	}
	@Test
	public void checkVerticalJump() throws HantoException{
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, 0, 1),
			    plPair(BLUE, SPARROW, 0, -1), plPair(RED, HORSE, 0, 2)
			    
			};
		epsilonTestGame.initializeBoard(board);
		epsilonTestGame.setPlayerMoving(RED);
		epsilonTestGame.setTurnNumber(4);
		assertEquals(epsilonGame.makeMove(HORSE, new TestHantoCoordinate(0,2), new TestHantoCoordinate(0,-2)), MoveResult.OK);
	}
	@Test (expected=HantoException.class)
	public void checkVerticalJumpBreak() throws HantoException{
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, 0, 1),
			    plPair(BLUE, SPARROW, -1, 0), plPair(RED, HORSE, 0, 2),
			    plPair(BLUE, SPARROW,-1,-1)
			    
			};
		epsilonTestGame.initializeBoard(board);
		epsilonTestGame.setPlayerMoving(RED);
		epsilonTestGame.setTurnNumber(4);
		assertEquals(epsilonGame.makeMove(HORSE, new TestHantoCoordinate(0,2), new TestHantoCoordinate(0,-2)), MoveResult.OK);
	}
	@Test (expected=HantoException.class)
	public void checkVerticalJumpBreakOtherWay() throws HantoException{
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, -1, 1),
			    plPair(BLUE, SPARROW, 0, -1), plPair(RED, HORSE, 0, -2),
			    plPair(BLUE, SPARROW,-1,2)
			    
			};
		epsilonTestGame.initializeBoard(board);
		epsilonTestGame.setPlayerMoving(RED);
		epsilonTestGame.setTurnNumber(4);
		assertEquals(epsilonGame.makeMove(HORSE, new TestHantoCoordinate(0,-2), new TestHantoCoordinate(0,2)), MoveResult.OK);
	}
	
	
	
	/**
	 * Factory method to create a piece location pair.
	 * @param player the player color
	 * @param pieceType the piece type
	 * @param x starting location
	 * @param y end location
	 * @return
	 */
	private PieceLocationPair plPair(HantoPlayerColor player, HantoPieceType pieceType, 
			int x, int y)
	{
		return new PieceLocationPair(player, pieceType, new TestHantoCoordinate(x, y));
	}
	
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

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
 * package = 
 * package.sourceFolder = Hanto2014-Master/test
 * superclassType = junit.framework.TestCase
 * testCase = HantoCellManagerTest
 * testClassType = hanto.kcbtsb.alpha.HantoCellManager
 */