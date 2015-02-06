package tests;

import other.Coordinate;

public class AltDiagonalTest implements BoardTest {

	/**
	 * Tests if a move was diagonal (In the "\" direction).
	 * 
	 * If the move was diagonal and valid, the coordinate of the peg that was "skipped over"
	 * is returned. If the move is invalid or not diagonal null is returned.
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
		
		if ( finalCoordinate.equals(new Coordinate(initialCoordinate.getRow() + 2, initialCoordinate.getCol() + 2)) ){
			return new Coordinate(initialCoordinate.getRow() + 1, initialCoordinate.getCol() + 1);
		}else if(finalCoordinate.equals(new Coordinate(initialCoordinate.getRow() - 2, initialCoordinate.getCol() - 2))) {
			return new Coordinate(initialCoordinate.getRow() - 1, initialCoordinate.getCol() - 1);
		}
		
		return null;
	}

}
