package tests;

import other.Coordinate;

public class HorizontalTest implements BoardTest{

	/**
	 * Tests if a move was horizontal.
	 * 
	 * If the move was horizontal and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not horizontal null is returned.
	 * 
	 * @param initialPos	Coordinate of peg to be moved.
	 * @param finalPos		Coordinate to move peg.
	 * @return				Coordinate of peg to be removed if valid.
	 * 
	 * @author Brandt
	 */
	@Override
	public Coordinate test(Coordinate initialCoordinate,
			Coordinate finalCoordinate) {
		if (initialCoordinate.getRow() == finalCoordinate.getRow()) {
			// Horizontal attempt
			if(initialCoordinate.getCol() - finalCoordinate.getCol() == 2){
				return new Coordinate( initialCoordinate.getRow(), initialCoordinate.getCol() - 1 );
			}else if(initialCoordinate.getCol() - finalCoordinate.getCol() == -2){
				return new Coordinate( initialCoordinate.getRow(), initialCoordinate.getCol() + 1 );
			}
		}
		return null;
	}

}
