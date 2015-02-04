package other;

/**
 * Coordinate class is used to store a coordinate.
 * 
 * Coordinates include a row position and a column position. 
 * Note: for use in a 2D array: array[row][column]
 * 
 * @author Brandt
 *
 */
public class Coordinate {
	private final int ROW;
	private final int COL;
	
	/**
	 * Initializes a coordinate with a row and column.
	 * 
	 * @param ROW index of row
	 * @param COL index of column
	 */
	public Coordinate(int row, int col) {
		this.ROW = row;
		this.COL = col;
	}
	
	public int getRow(){
		return ROW;
	}
	
	public int getCol(){
		return COL;
	}
	
	/**
	 * Compares 2 Coordinate objects for equality.
	 * 
	 * @param coordinate	compared to this.
	 * @return				True if row and col match this, else False.
	 */
	public boolean equals(Coordinate coordinate) {
		return ( ROW == coordinate.getRow() ) && ( COL == coordinate.getCol() );
	}
}
