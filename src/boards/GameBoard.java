package boards;

public abstract class GameBoard {
	private int[][] gameBoard;


	public abstract int getPegCount();

	public abstract boolean isValidMove(int initialPosition, int finalPosition);

	public abstract boolean isPeg(int position);

	public abstract void drawBoard();
}
