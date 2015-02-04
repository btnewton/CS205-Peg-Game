package game;
import java.text.DecimalFormat;
import java.util.Scanner;

import other.Coordinate;
import boards.EnglishBoard;
import boards.GameBoard;
import boards.TriangleBoard;

public class Game {
	
	static int totalPegsLeft = 0;
	static int gamesPlayed = 0;
	static int gamesWon = 0;
	
	final String HELP = "h";
	final String QUIT = "q";
	
	GameBoard board;
	
	/**
	 * The game constructor sets which board is to be used in the new game.
	 * 
	 * Boards all extend the GameBoard class and use their own data structures
	 * and move validation.
	 * 
	 * @param board
	 */
	public Game(GameBoard board) {
		this.board = board;
	}
	
	/**
	 * Play runs the main game loop.
	 */
	public void play() {
		boolean playing = true;
		
		do {
			// Print board
			board.drawBoard();
			
			// Prompt
			playing = getUserInput();
			
		} while(playing && board.getPegCount() > 1);
		
		
	}
	
	// TODO Get user input and apply move or respond to command
	private boolean getUserInput() {
		Scanner keyboard = new Scanner(System.in); 
		
		System.out.print("\nPlease enter a move or '" + HELP + "' for help: ");
		
		String input = keyboard.nextLine();
		
		try {
			boolean validMove = false;
			int initialPosition = Integer.parseInt(input);
			Coordinate initialCoordinate = board.getCoordinate(initialPosition);
			
			if ( initialCoordinate != null && board.isPeg(initialCoordinate) ) {
		
				System.out.print("Enter a final position: ");
				int finalPosition = keyboard.nextInt();
				Coordinate finalCoordinate = board.getCoordinate(finalPosition);
				
				Coordinate skipCoordinate = null;
				
				if (finalCoordinate != null) 
					skipCoordinate = board.isValidMove(initialCoordinate, finalCoordinate);
				
				if ( skipCoordinate != null ) {
					board.emptyPos(initialCoordinate);
					board.emptyPos(skipCoordinate);
					board.fillPos(finalCoordinate);
					validMove = true;
				}
			}
			
			if ( ! validMove ){
				System.out.println("Invalid move.");
			}
		} catch (NumberFormatException e) {
			// Input is a command not a move
			switch (input){
				case HELP:
					printHelp();
					break;
				case QUIT:
					return false;
				default:
					System.out.println("Invalid input.");
			}
		}
		
		// Continue playing
		return true;
	}
	
	/**
	 * Prints helpful information to the console.
	 */
	private void printHelp(){
		board.showHelp();
	}
	
	public void endGame() {
		// Print final board
		board.drawBoard();
		
		// Game over
		System.out.println("\nGame over");
			    
		// Provide win/loss feedback
		if(board.getPegCount() == 1){
		    System.out.println("You Win!");
		    
		    // Update games won count.
		    gamesWon++;
		}else{
			System.out.println("You lose.");
		}
		
		// Update other stats
		gamesPlayed++;
		totalPegsLeft += board.getPegCount();
		System.out.println("Remaining number of pegs: " + board.getPegCount());
		displayStats();
		
		board.drawHR();
	}
	
	public static void displayStats() {
		// Test if user has played at all
		if (gamesPlayed > 0) {
			DecimalFormat formatter = new DecimalFormat("##0.00");
			System.out.println("\nTotal pegs left this session: " + totalPegsLeft);
			System.out.println("Win Rate: " + formatter.format((( (double) gamesWon / gamesPlayed ) * 100)) + "%");
			System.out.println("Average Pegs Left: " + totalPegsLeft / gamesPlayed);
		}
	}

	public static void main(String[] args) {
		final String TRIANGLE_GAME_ID = "1";
		final String ENGLISH_GAME_ID = "2";
		boolean playing = true;
		Scanner keyboard = new Scanner(System.in);
		GameBoard board;
		Game game;
		
		do {
			board = null;
			
			System.out.println("1) Triangle Peg");
			System.out.println("2) English Peg Solitare");
			System.out.println("Select a game or press 'q' to quit.");
	        String input = keyboard.nextLine();
	        
	        // Check user input
			if (input.equals(TRIANGLE_GAME_ID))
				board = new TriangleBoard();
			else if (input.equals(ENGLISH_GAME_ID))
				board = new EnglishBoard();
			else {
				System.out.println("Do you wish to quit? Y/N");
				input = keyboard.nextLine();
				
				if ( input.equalsIgnoreCase("y") ) {
					Game.displayStats();
					System.out.println("Goodbye!");
					return;
				}
			}
	        
			// Start game if board selected.
	        if (board != null) {
		        game = new Game(board);
				game.play();
				game.endGame();
	        }
	        
		} while(playing);
	}
}