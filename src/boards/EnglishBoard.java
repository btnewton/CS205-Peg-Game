package boards;

import other.Coordinate;

public class EnglishBoard extends GameBoard{

	public EnglishBoard() {
		gameBoard = new int[][] {
				{0, 0, 2, 2, 2, 0, 0},
				{0, 0, 2, 2, 2, 0, 0},
				{2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 1, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2},
				{0, 0, 2, 2, 2, 0, 0},
				{0, 0, 2, 2, 2, 0, 0}
				};
	}
	
	@Override
	public Coordinate isValidMove(Coordinate initialCoordinate, Coordinate finalCoordinate) {
		
		// Initial tests
		if ( isEmpty(initialCoordinate) )
			return null;
		
		if ( isPeg(finalCoordinate) )
			return null;
		
		// Primary Tests
		Coordinate skipCoordinate;
		
		skipCoordinate = testHorizontal( initialCoordinate, finalCoordinate );
		
		if (skipCoordinate == null)
			skipCoordinate = testVertical( initialCoordinate, finalCoordinate );
		
		return skipCoordinate;
	}
	
	private Coordinate testHorizontal(Coordinate initialPos, Coordinate finalPos) {
		if (initialPos.getRow() == finalPos.getRow()) {
			Coordinate skipCoordinate = null;
			
			// Horizontal attempt
			if(initialPos.getCol() - finalPos.getCol() == 2){
				skipCoordinate = new Coordinate( initialPos.getRow(), initialPos.getCol() - 1 );
			}else if(initialPos.getCol() - finalPos.getCol() == -2){
				skipCoordinate = new Coordinate( initialPos.getRow(), initialPos.getCol() + 1 );
			}
			
			if ( isPeg( skipCoordinate ) )
				return skipCoordinate;
			
		}
		return null;
	}
	
	private Coordinate testVertical(Coordinate initialPos, Coordinate finalPos) {
		if (initialPos.getCol() == finalPos.getCol()) {
			Coordinate skipCoordinate = null;
			
			// Horizontal attempt
			if ( initialPos.getRow() - finalPos.getRow() == 2 ){
				skipCoordinate = new Coordinate( initialPos.getRow() - 1, initialPos.getCol() );
			} else if ( initialPos.getRow() - finalPos.getRow() == -2 ){
				skipCoordinate = new Coordinate( initialPos.getRow() + 1, initialPos.getCol() );
			}
			
			if ( isPeg( skipCoordinate ) )
				return skipCoordinate;
			
		}
		
		return null;
	}

	/**
	 * drawBoard prints the game board in the console.
	 * 
	 * Each hole on the board displays information regarding its position and
	 * whether or not it is occupied.
	 */
	@Override
	public void drawBoard() {
		drawHR();
		
		int holeCounter = 1;
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				int peg = gameBoard[row][col];
				holeCounter = drawPeg(peg, holeCounter);
			}
			
			System.out.print("\n");
		}
	}
	
	@Override
	public void showHelp() {
		System.out.println("SHOW HELP");
	}
}
