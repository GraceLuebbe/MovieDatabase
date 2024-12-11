import java.io.File;
import java.util.Scanner;

public interface FileHandler {
	void handle(Scanner lineScanner, Movie movie, int rank);
}

//All of these are coded for the txt file specifics, and assuming that 
// the common factors (rank, title, year) are not within linescanner.

class GrossHandler implements FileHandler{

	@Override
	public void handle(Scanner lineScanner, Movie movie, int rank) {
		movie.processGrossLine(lineScanner, rank);
		
	}
	
}
 
class CastHandler implements FileHandler{

	@Override
	public void handle(Scanner lineScanner, Movie movie, int rank) {
		movie.processCastLine(lineScanner, rank);
		
	}
	
}

class RatingHandler implements FileHandler{

	@Override
	public void handle(Scanner lineScanner, Movie movie, int rank) {
		movie.processTopRatedLine(lineScanner, rank);
		
	}
	
}