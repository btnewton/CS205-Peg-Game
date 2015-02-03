package boards;

import java.util.Scanner;

public class TriangleBoard extends GameBoard{

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
	 */
	private void setStartingPosition() {
		do {
			Scanner keyboard = new Scanner(System.in); 
			
			drawBoard();
			
			System.out.println("Please select a position to empty:");
			
			String input = keyboard.nextLine();
			
			try {
				int position = Integer.parseInt(input);
				if (0 < position && position <= countTotalPositions()) {
					emptyPos(position);
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid position.");
			}
		}while(true);
	}
	
	@Override
	public boolean isValidMove(int initialPosition, int finalPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * drawBoard prints the game board in the console.
	 * 
	 * Each hole on the board displays information regarding its position and
	 * whether or not it is occupied.
	 */
	@Override
	public void drawBoard() {
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
}
