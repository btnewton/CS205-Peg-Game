package tests;

import other.Coordinate;

public class VerticalTest implements BoardTest{

	/**
	 * Tests if a move was vertical.
	 * 
	 * If the move was vertical and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not vertical null is returned.
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
		
		if (initialCoordinate.getCol() == finalCoordinate.getCol()) {
			
			// Horizontal attempt
			if ( initialCoordinate.getRow() - finalCoordinate.getRow() == 2 ){
				return new Coordinate( initialCoordinate.getRow() - 1, initialCoordinate.getCol() );
			} else if ( initialCoordinate.getRow() - finalCoordinate.getRow() == -2 ){
				return new Coordinate( initialCoordinate.getRow() + 1, initialCoordinate.getCol() );
			}
		}
		
		return null;
	}

}
