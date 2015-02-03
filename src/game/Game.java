package game;
import java.util.Scanner;

import boards.EnglishBoard;
import boards.GameBoard;
import boards.TriangleBoard;

public class Game {
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
		
		// Game over
		System.out.println("Game over");
			    
		// Provide win/loss feedback
		if(board.getPegCount() == 1){
		    System.out.println("You Win");
		}else{
			System.out.println("You LOSE");
		}
	}
	
	// TODO Get user input and apply move or respond to command
	private boolean getUserInput() {
		Scanner keyboard = new Scanner(System.in); 
		
		System.out.println("Please enter a move or '" + HELP + "' for help.");
		
		String input = keyboard.nextLine();
		
		try {
			int move = Integer.parseInt(input);
			if (board.isPeg(move)) {
				// TODO valid position. Prompt for peg destination
			}else {
				// TODO alert invalid position.
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
		System.out.println("Helpful information");
	}

	public static void main(String[] args) {
		final String TRIANGLE_GAME_ID = "1";
		final String ENGLISH_GAME_ID = "2";
		int numberOfPinsLeft;
		boolean playing = true;
		Scanner keyboard = new Scanner(System.in);
		
		do {
			// TODO choose game type
			System.out.println("Select Game Board"); 
	        System.out.print("Triangle Game (Press 1), English Game (Press 2): "); 
	        
	        String input = keyboard.nextLine();
	        
	        try {
				int selection = Integer.parseInt(input);
				
				GameBoard board;
				
				if (input.equals(TRIANGLE_GAME_ID))
					board = new TriangleBoard();
				else
					board = new EnglishBoard();
				
				Game game = new Game(board);
				game.play();
				
				// TODO get game stats
				numberOfPinsLeft = board.getPegCount();
				System.out.println("Remaining number of pegs: " + numberOfPinsLeft);
				
			} catch (NumberFormatException e) {
				// TODO respond to non integer input. Could be mistake or intent to quit.
			}
	        
		} while(playing);
	}
}