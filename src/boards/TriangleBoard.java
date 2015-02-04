package boards;

import java.util.Scanner;

import other.Coordinate;

public class TriangleBoard extends GameBoard{

	/**
	 * Initializes the Traingle GameBoard data structure.
	 * 
	 * @author Tylor
	 */
	public TriangleBoard() {
		gameBoard = new int[][] {
				{2, 0, 0, 0, 0},
				{2, 2, 0, 0, 0},
				{2, 2, 2, 0, 0},
				{2, 2, 2, 2, 0},
				{2, 2, 2, 2, 2}
				};

		setStartingPosition();
	}

	/**
	 * Allows the user to chose which position is empty at the start of the game.
	 * 
	 * This is unique to the triangle game.
	 * 
	 * @author Brandt
	 */
	private void setStartingPosition() {
		do {
			Scanner keyboard = new Scanner(System.in); 
			
			drawBoard();
			
			System.out.print("\nPlease select a position to empty: ");
			
			String input = keyboard.nextLine();
			
			try {
				int position = Integer.parseInt(input);
				Coordinate coordinate = getCoordinate(position);

				if ( coordinate != null && isPeg(coordinate) ) {
					emptyPos(coordinate);
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid position.");
			}
		}while(true);
	}
	
	/**
	 * Tests if a move is valid.
	 * 
	 * If the move is valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid null is returned.
	 * 
	 * @param initialPos	Coordinate of peg to be moved.
	 * @param finalPos		Coordinate to move peg.
	 * @return				Coordinate of peg to be removed if valid.
	 * 
	 * @author Brandt
	 */
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
			skipCoordinate = testDiagonal( initialCoordinate, finalCoordinate );
		
		return skipCoordinate;
	}
	
	/**
	 * Tests if a move was horizontal.
	 * 
	 * If the move was horizontal and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not horizontal null is returned.
	 * 
	 * @param initialPos	Coordinate of peg to be moved.
	 * @param finalPos		Coordinate to move peg.
	 * @return				Coordinate of peg to be removed if valid.
	 * 
	 * @author Brandt
	 */
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
	
	/**
	 * Runs both diagonal tests.
	 * 
	 * If the move was diagonal and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not diagonal null is returned.
	 * 
	 * @param initialPos	Coordinate of peg to be moved.
	 * @param finalPos		Coordinate to move peg.
	 * @return				Coordinate of peg to be removed if valid.
	 * 
	 * @author Brandt
	 */
	private Coordinate testDiagonal(Coordinate initialPos, Coordinate finalPos) {
		Coordinate skipCoordinate;
		
		// Test /-diagonal
		skipCoordinate = testVertical(initialPos, finalPos);
		
		if (skipCoordinate == null) {
			// Test \-diagonal
			skipCoordinate = testAltDiagonal(initialPos, finalPos);
		}
				
		return skipCoordinate;
	}
	
	/**
	 * Tests if a move was diagonal (In the "\" direction).
	 * 
	 * If the move was diagonal and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not diagonal null is returned.
	 * 
	 * @param initialPos	Coordinate of peg to be moved.
	 * @param finalPos		Coordinate to move peg.
	 * @return				Coordinate of peg to be removed if valid.
	 * 
	 * @author Brandt
	 */
	private Coordinate testAltDiagonal(Coordinate initialPos, Coordinate finalPos) {
		Coordinate skipCoordinate = null;
		
		if ( finalPos.equals(new Coordinate(initialPos.getRow() + 2, initialPos.getCol() + 2)) ){
			skipCoordinate = new Coordinate(initialPos.getRow() + 1, initialPos.getCol() + 1);
		}else if(finalPos.equals(new Coordinate(initialPos.getRow() - 2, initialPos.getCol() - 2))) {
			skipCoordinate = new Coordinate(initialPos.getRow() - 1, initialPos.getCol() - 1);
		}
		
		if ( isPeg( skipCoordinate ))
			return skipCoordinate;
		
		return null;
	}
	
	/**
	 * Tests if a move was vertical.
	 * 
	 * If the move was vertical and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not vertical null is returned.
	 * 
	 * This serves as a diagonal test (in the "/" direction) with how the triangle data 
	 * structure is implemented.
	 * 
	 * @param initialPos	Coordinate of peg to be moved.
	 * @param finalPos		Coordinate to move peg.
	 * @return				Coordinate of peg to be removed if valid.
	 * 
	 * @author Brandt
	 */
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
	 * 
	 * @author Kai
	 * @author Brandt
	 */
	@Override
	public void drawBoard() {
		drawHR();
		
		int holeCounter = 1;
		
		for (int row = 0; row < gameBoard.length; row++) {
			drawOffset(row);
			for (int col = 0; col < gameBoard[row].length; col++) {
				int peg = gameBoard[row][col];
				holeCounter = drawPeg(peg, holeCounter);
			}
			
			System.out.print("\n");
		}
	}
	
	/**
	 * drawOffset prints white space to create the offset necessary to make the board
	 * resemble a triangle.
	 * 
	 * The white space printed is based on the length of INVALID_GFX.
	 * 
	 * @param row determines how much white space is printed.
	 */
	private void drawOffset(int row) {
		String offset = INVALID_GFX.substring(INVALID_GFX.length()/2);
		int offsetCount = gameBoard.length - row;
		
		for (int i = 0; i < offsetCount; i++) {
			System.out.print(offset);
		}
	}

	/**
	 * Show helpful information about commands and rules to the user.
	 * 
	 * @author Kai
	 */
	@Override
	public void showHelp() {
		System.out.println("SHOW HELP");
		System.out.println("GAME COMMANDS AND RULES:");
		System.out.println("press q to quit.");
		System.out.println("press h for help.");
		System.out.println("press s for stats.");
		System.out.println("Welcome to Peg solitare, the goal of the game is to remove all pegs but one");
		System.out.println("to remove a peg, you must take select a peg, and move that peg over another into");
		System.out.println("an empty space. Think jumping in checkers. Enter the coordinate of the peg you wish");
		System.out.println("to move, and then the coordinate of the empty space you wish to move that peg into.");
		System.out.println("The game is over when there are no longer any moves the player can make, with the");
		System.out.println("Player winning if there is only one peg remaining at this time.");
	}
}
