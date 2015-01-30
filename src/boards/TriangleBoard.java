package boards;

public class TriangleBoard extends GameBoard{

	public TriangleBoard() {
		gameBoard = new int[][] {
				{1, 0, 0, 0, 0, 0, 0},
				{1, 1, 0, 0, 0, 0, 0},
				{1, 1, 1, 0, 0, 0, 0},
				{1, 1, 1, 1, 0, 0, 0},
				{1, 1, 1, 1, 1, 0, 0}
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
