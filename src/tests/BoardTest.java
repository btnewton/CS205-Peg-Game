package tests;

import other.Coordinate;

public interface BoardTest {
	
	// Test move
	public abstract Coordinate test(Coordinate initialCoordinate, Coordinate finalCoordinate);
}
