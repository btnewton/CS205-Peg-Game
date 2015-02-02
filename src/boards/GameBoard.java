package boards;

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
	 */
	protected GameBoard(String invalid_pos, String vacant_pos, String occupied_pos) {
		INVALID_GFX = invalid_pos;
		VACANT_GFX = vacant_pos;
		OCCUPIED_GFX = occupied_pos;
	}
	
	/**
	 * This constructor uses the default board GFX.
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
	 */
	public int getPegCount() {
		int pegCounter = 0;
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[col][row] == OCCUPIED_POS)
					pegCounter++;
			}	
		}
		
		return pegCounter;
	}

	/**
	 * isPeg determines if the given hole is occupied.
	 * 
	 * Note that invalid positions will return False.
	 * 
	 * @param pos	position to test.
	 * @return 		True if position holds a peg.
	 */
	public boolean isPeg(int pos) {
		int rowLength = gameBoard[0].length;
		return gameBoard[pos/rowLength][pos%rowLength] == OCCUPIED_POS;
	}

	/**
	 * drawPeg prints the current position
	 * 
	 * holeCounter will increase by 1 if peg is not an invalid position.
	 * 
	 * @param peg			value of current position.
	 * @param holeCounter	number of next printed hole.
	 * @return number of next printed hole.
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
	
	// Abstract Methods
	public abstract boolean isValidMove(int initialPosition, int finalPosition);
	public abstract void drawBoard();
}
