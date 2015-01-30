package game;
import boards.EnglishBoard;
import boards.GameBoard;
import boards.TriangleBoard;

public class Game {
	final String HELP = "h";
	final String QUIT = "q";
	
	GameBoard board;
	
	public Game(GameBoard board) {
		this.board = board;
	}
	
	public void play() {
		boolean playing = true;
		
		do {
			// Print board
			board.drawBoard();
			
			// Prompt
			System.out.println("Please enter a move or '" + HELP + "' for help.");
			
			playing = getUserInput();
			
		} while(playing && board.getPegCount() > 1);
		
		// Game over
		// TODO store stats
		// TODO Provide win/loss feedback
	}
	
	// TODO Get user input and apply move or respond to command
	private boolean getUserInput() {
		
		String input = "";
		
		
		// If input is a command not a move
		switch (input){
			case HELP:
				printHelp();
				break;
			case QUIT:
				return false;
			default:
				// TODO Bad input
		}
		
		// Continue playing
		return true;
	}
	
	/**
	 * Prints helpful information to the console.
	 */
	private void printHelp(){
		System.out.println("Helpful information");
	}

	public static void main(String[] args) {
		final String TRIANGLE_GAME_ID = "1";
		final String ENGLISH_GAME_ID = "2";
		String userInput = "";
		Game game;
		GameBoard board;
		
		do {
			// TODO choose game type
			
			if (userInput.equals(TRIANGLE_GAME_ID))
				board = new TriangleBoard();
			else
				board = new EnglishBoard();
			
			game = new Game(board);
			game.play();
			
			
			// TODO get game stats
		} while(true);
	}
}