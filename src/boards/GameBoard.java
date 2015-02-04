package boards;

import other.Coordinate;

public abstract class GameBoard {
	protected int[][] gameBoard;

	// Text to print
	protected final String INVALID_GFX;
	protected final String VACANT_GFX;
	protected final String OCCUPIED_GFX;
	
	// Board position keys
	protected final int INVALID_POS = 0;
	protected final int VACANT_POS = 1;
	protected final int OCCUPIED_POS = 2;

	/**
	 * This constructor allows custom board GFX.
	 * @param invalid_pos	GFX to represent null space.
	 * @param vacant_pos	GFX to represent an empty hole.
	 * @param occupied_pos	GFX to represent an occupied hole.
	 * 
	 * @author Brandt
	 */
	protected GameBoard(String invalid_pos, String vacant_pos, String occupied_pos) {
		INVALID_GFX = invalid_pos;
		VACANT_GFX = vacant_pos;
		OCCUPIED_GFX = occupied_pos;
	}
	
	/**
	 * This constructor uses the default board GFX.
	 * 
	 * @author Brandt
	 */
	protected GameBoard() {
		INVALID_GFX = "     ";
		VACANT_GFX = "_";
		OCCUPIED_GFX = "X";
	}

	/**
	 * getPegCount returns the number of pegs that exist in the gameBoard data structure.
	 * 
	 * @return count of remaining pegs.
	 * 
	 * @author Brandt
	 */
	public int getPegCount() {
		int pegCounter = 0;
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] == OCCUPIED_POS)
					pegCounter++;
			}	
		}
		
		return pegCounter;
	}

	/**
	 * isPeg determines if the given hole is occupied.
	 * 
	 * Note that invalid positions will also return False.
	 * 
	 * @param pos	position to test.
	 * @return 		True if position holds a peg.
	 * 
	 * @author Brandt
	 */
	public boolean isPeg(Coordinate coordinate) {
		return gameBoard[coordinate.getRow()][coordinate.getCol()] == OCCUPIED_POS;
	}
	
	/**
	 * isEmpty determines if the given hole is vacant.
	 * 
	 * Note that invalid positions will also return False.
	 * 
	 * @param pos	position to test.
	 * @return 		True if position holds a peg.
	 * 
	 * @author Brandt
	 */
	public boolean isEmpty(Coordinate coordinate) {
		return gameBoard[coordinate.getRow()][coordinate.getCol()] == VACANT_POS;
	}

	/**
	 * drawPeg prints the current position
	 * 
	 * holeCounter will increase by 1 if peg is not an invalid position.
	 * 
	 * @param peg			value of current position.
	 * @param holeCounter	number of next printed hole.
	 * @return number of next printed hole.
	 * 
	 * @author Brandt
	 */
	public int drawPeg(int peg, int holeCounter) {		
		String draw;
		
		if (peg == INVALID_POS) {
			draw = INVALID_GFX;
		}else {
			String symbol;
			
			if (peg == VACANT_POS)
				symbol = VACANT_GFX;
			else
				symbol = OCCUPIED_GFX;
			
			draw = String.format("[%s%2d]", symbol, holeCounter);
			
			holeCounter++;
		}
		
		System.out.print(draw);
		
		return holeCounter;
	}
	
	/**
	 * Set the position to vacant.
	 * 
	 * @param pos	location to empty.
	 * 
	 * @author Brandt
	 */
	public void emptyPos(Coordinate coordinate) {
		setPos(coordinate, VACANT_POS);
	}
	
	/**
	 * Set the position to occupied.
	 * 
	 * @param pos	location to fill.
	 * 
	 * @author Brandt
	 */
	public void fillPos(Coordinate coordinate) {
		setPos(coordinate, OCCUPIED_POS);
	}
	
	/**
	 * Private position setter method that will set an element of the board data structure
	 * to the specified new value. 
	 *
	 * @param pos		relates to number of valid positions in gameBoard.
	 * @param newType	new value for pos.
	 * 
	 * @author Brandt
	 */
	private void setPos(Coordinate coordinate, int newType) {
		int row = coordinate.getRow();
		int col = coordinate.getCol();
		
		gameBoard[row][col] = newType;
	}
	
	/**
	 * Converts a position to a Coordinate
	 * 
	 * @param pos	to be converted
	 * @return		Coordinate equivalent to pos
	 * 
	 * @author Brandt
	 */
	public Coordinate getCoordinate(int pos) {
		int posCounter = 1;
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] != INVALID_POS) {
					if (posCounter == pos) {
						return new Coordinate(row, col);
					}
					posCounter++;
				}
			}	
		}
		
		// Position not found.
		return null;
	}
	
	/**
	 * Converts a coordinate to a position.
	 * 
	 * Positions equal the peg number for the board.
	 * 
	 * @param coordinate	coordinate to convert.
	 * @return return 		equivalent position of coordinate.
	 * 
	 * @author Brandt
	 */
	protected int getPos(Coordinate coordinate) {
		int posCounter = 0;
		int targetRow = coordinate.getRow();
		int targetCol = coordinate.getCol();
		
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] != INVALID_POS) {
					posCounter++;
					
					if (row == targetRow && col == targetCol) {
						return posCounter;
					}
				}
			}	
		}
		
		return -1;
	}
	
	/**
	 * Checks if coordinate corresponds to a valid position on the board.
	 * 
	 * @param coordinate	Coordinate to test.
	 * @return				True if coordinate is not invalid.
	 */
	public boolean isValid(Coordinate coordinate) {
		return gameBoard[coordinate.getRow()][coordinate.getCol()] != INVALID_POS;
	}
	
	/**
	 * Counts total elements in gameBoard that are not equal to
	 * INVALID_POS.
	 * 
	 * @return count of valid positions.
	 * 
	 * @author Brandt
	 */
	public int countTotalPositions() {
		int posCounter = 0;
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] != INVALID_POS) {
					posCounter++;
				}
			}	
		}
		
		return posCounter;
	}
	
	/**
	 * Prints a break for esthetics. 
	 * 
	 * @author Brandt
	 */
	public static void drawHR() {
		System.out.println("\n\n---\n");
	}
	
	// Abstract Methods
	public abstract Coordinate isValidMove(Coordinate initialCoordinate, Coordinate finalCoordinate);
	public abstract void drawBoard();
	public abstract void showHelp();
}
