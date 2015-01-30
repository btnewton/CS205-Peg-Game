package boards;

public class EnglishBoard extends GameBoard{

	public EnglishBoard() {
		gameBoard = new int[][] {
				{0, 0, 1, 1, 1, 0, 0},
				{0, 0, 1, 1, 1, 0, 0},
				{1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 2, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1},
				{0, 0, 1, 1, 1, 0, 0},
				{0, 0, 1, 1, 1, 0, 0}
				};
	}
	
	@Override
	public int getPegCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isValidMove(int initialPosition, int finalPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPeg(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawBoard() {
		// TODO Auto-generated method stub
		
	}

}
