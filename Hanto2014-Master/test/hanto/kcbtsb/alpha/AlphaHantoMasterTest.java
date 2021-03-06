package hanto.kcbtsb.alpha;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.kcbtsb.HantoGameFactory;

public class AlphaHantoMasterTest{
	/**         * Internal class for these test cases.         * @version Sep 13, 2014    */   
	class TestHantoCoordinate implements HantoCoordinate{              
		private final int x, y;                               
		public TestHantoCoordinate(int x, int y){                 
			this.x = x;                   
			this.y = y;             
			}             
		/*             * @see hanto.common.HantoCoordinate#getX()                 */                
		@Override                
		public int getX()            
		{                    
			return x;              
		}              
		/*              * @see hanto.common.HantoCoordinate#getY()          */         
		@Override         
		public int getY()             
		{                     
			return y;               
		}       
	}               
	private HantoGame game;      
	
	@BeforeClass public static void initializeClass()    {           
		HantoGameFactory.getInstance();        
	}
	
	@Before  public void setup()    {            
		game = HantoGameFactory.makeHantoGame(HantoGameID.ALPHA_HANTO);   
	}      
	
	@Test       
	public void getAnAlphaHantoGameFromTheFactory()     {             
		assertTrue(game instanceof HantoGameAlpha);       
	}   
	
	@Test   public void blueMakesValidFirstMove() throws HantoException {         
		final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));              
		assertEquals(MoveResult.OK, mr);    
	}    
	
	@Test        
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException   {           
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));              
		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));               
		assertEquals(HantoPieceType.BUTTERFLY, p.getType());             
		assertEquals(HantoPlayerColor.BLUE, p.getColor());      
	}      
	
	@Test(expected=HantoException.class)   
	public void bluePlacesNonButterfly() throws HantoException  {          
		game.makeMove(HantoPieceType.SPARROW, null, new TestHantoCoordinate(0, 0));      
	}
	
	@Test   
	public void redPlacesButterflyNextToBlueButterfly() throws HantoException   {           
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));              
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 1));         
		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 1));          
		assertEquals(HantoPieceType.BUTTERFLY, p.getType());                
		assertEquals(HantoPlayerColor.RED, p.getColor());  
	}  
	
	@Test(expected=HantoException.class)       
	public void blueAttemptsToPlaceButterflyAtWrongLocation() throws HantoException {         
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(-1, 1));   
	} 
	
	@Test       
	public void redMakesValidSecondMoveAndGameIsDrawn() throws HantoException       {               
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));          
		final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(-1, 1));              
		assertEquals(MoveResult.DRAW, mr);       
	}      
	
	@Test(expected=HantoException.class)    
	public void redPlacesButterflyNonAdjacentToBlueButterfly() throws HantoException     {             
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));                
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 2));   
	}  
	
	@Test(expected=HantoException.class)        
	public void attemptToMoveRatherThanPlace() throws HantoException {         
		game.makeMove(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0, 1), new TestHantoCoordinate(0, 0));       
	}
}