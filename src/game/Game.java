package game;
import java.text.DecimalFormat;
import java.util.Scanner;

import other.Coordinate;
import boards.EnglishBoard;
import boards.GameBoard;
import boards.TriangleBoard;

public class Game {
	
	// Stats storage
	static int totalPegsLeft = 0;
	static int gamesPlayed = 0;
	static int gamesWon = 0;
	
	// Command values
	final String HELP = "h";
	final String QUIT = "q";
	final String STATS = "s";
	
	GameBoard board;
	
	/**
	 * The game constructor sets which board is to be used in the new game.
	 * 
	 * Boards all extend the GameBoard class and use their own data structures
	 * and move validation.
	 * 
	 * @param board
	 * 
	 * @author Brandt
	 */
	public Game(GameBoard board) {
		this.board = board;
	}
	
	/**
	 * Play runs the main game loop.
	 * 
	 * @author Brandt
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
	
	/**
	 * Prompts, receives and processes input.
	 * 
	 * Gets user input and responds to it. If the user moves, calls a command,
	 * or enters some invalid input True will be returned. If the user enters 
	 * "q" False will be returned.
	 * 
	 * @return True unless the user quits.
	 * 
	 * @author My
	 * @author Brandt
	 */
	private boolean getUserInput() {
		Scanner keyboard = new Scanner(System.in); 
		
		System.out.print("\nPlease enter a move or '" + HELP + "' for help: ");
		
		String input = keyboard.nextLine();
		
		try {
			// Triggers invalid move message
			boolean validMove = false;
			
			// Convert input to integer and coordinate
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
					// Valid move! Execute.
					board.emptyPos(initialCoordinate);
					board.emptyPos(skipCoordinate);
					board.fillPos(finalCoordinate);
					
					// Prevent invalid move message
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
				case STATS:
					displayStats();
					break;
				case QUIT:
					// End game and to main menu
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
	 * 
	 * Help is specific to the current game type.
	 * 
	 * @author My
	 */
	private void printHelp(){
		board.showHelp();
	}
	
	/**
	 * Handles stat recording and end game display.
	 * 
	 * @author My
	 * @author Brandt
	 */
	public void endGame() {
		// Print final board
		board.drawBoard();
		
		// Game over
		System.out.println("\nGame over");
			    
		// Provide win/loss feedback
		if (board.getPegCount() == 1) {
		    System.out.println("You Win!");
		    
		    // Update games won count.
		    gamesWon++;
		} else {
			System.out.println("You lose.");
		}
		
		// Update other stats
		gamesPlayed++;
		totalPegsLeft += board.getPegCount();
		System.out.println("Remaining number of pegs: " + board.getPegCount());
		displayStats();
		
		// Insert break for esthetics 
		board.drawHR();
	}
	
	/**
	 * Displays stat information for the user.
	 * 
	 * This method is static so it can be called when the user quits.
	 * 
	 * @author My
	 * @author Brandt
	 */
	public static void displayStats() {
		// Test if user has played at all
		if (gamesPlayed > 0) {
			
			DecimalFormat formatter = new DecimalFormat("##0.00");
			System.out.println("\nTotal pegs left this session: " + totalPegsLeft);
			System.out.println("Win Rate: " + formatter.format((( (double) gamesWon / gamesPlayed ) * 100)) + "%");
			System.out.println("Average Pegs Left: " + totalPegsLeft / gamesPlayed);
			
		}
	}

	/**
	 * Runs the Main Menu
	 * 
	 * @param args	Does nothing
	 * 
	 * @author Brandt
	 * @author My
	 */
	public static void main(String[] args) {
		final String TRIANGLE_GAME_ID = "1";
		final String ENGLISH_GAME_ID = "2";
		boolean playing = true;
		Scanner keyboard = new Scanner(System.in);
		GameBoard board;
		Game game;
		
		// Main Menu Loop
		do {
			board = null;
			
			// Prompt user
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
				
				// Invalid choice
				System.out.println("Do you wish to quit? Y/N");
				input = keyboard.nextLine();
				
				// Check if user really wants to quit
				if ( input.equalsIgnoreCase("y") ) {
					
					// Display session stats and quit
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