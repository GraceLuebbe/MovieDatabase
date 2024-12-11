import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MovieSorterTest {
	
	private final String TEST_FILE_PREFIX = "movieData/";
	private final String CAST_TEST_FILE = "cast_test_file";
	private final String RATING_TEST_FILE = "rating_test_file";
	private final String GROSS_TEST_FILE = "gross_test_file";	
	private MovieDatabase database;
	private HashMap <File, FileHandler> fileAndHandler = new HashMap<File, FileHandler>();
	
	@Test
	void grossFile() {
	File grossFile = new File(TEST_FILE_PREFIX + GROSS_TEST_FILE);	
	Movie movie = new Movie();
	try {
	Scanner fileScanner = new Scanner(grossFile);
	FileHandler grossFileHandler = new GrossHandler();
	movie = testMovie(fileScanner, grossFileHandler);
		}
	catch (FileNotFoundException e){
		System.out.println("File not found");
		fail();
	}

	Assert.assertEquals(760505847, movie.getBoxOfficeAmt());
	}
	
	@Test
	void topRatedFile() {
		File ratedFile = new File(TEST_FILE_PREFIX + RATING_TEST_FILE);	
		Movie movie = new Movie();
		try {
		Scanner fileScanner = new Scanner(ratedFile);
		FileHandler ratingFileHandler = new RatingHandler();
		movie = testMovie(fileScanner, ratingFileHandler);
			}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			fail();
		}	
		
		Assert.assertEquals(9.2, movie.getRating(), 0.05);
	}
	
	@Test
	void castFile() {
		File castFile = new File(TEST_FILE_PREFIX + CAST_TEST_FILE);	
		Movie movie = new Movie();
		try {
		Scanner fileScanner = new Scanner(castFile);
		FileHandler castFileHandler = new CastHandler();
		movie = testMovie(fileScanner, castFileHandler);
			}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			fail();
		}	
			
		ArrayList<String> trueCast = new ArrayList<String>();
		trueCast.add("Clyde Geronimi");
		trueCast.add("Rod Taylor");
		trueCast.add("J. Pat O'Malley");
		trueCast.add("Betty Lou Gerson");
		trueCast.add("Martha Wentworth");
		trueCast.add("Ben Wright");
		ArrayList<String> movieCast = movie.getCast();
		for (int i = 0; i < trueCast.size();i++) {
			System.out.println(trueCast.get(i) + " " + movieCast.get(i));
			Assert.assertEquals(trueCast.get(i), movieCast.get(i));
		}
		
	}
	
	public Movie testMovie(Scanner fileScanner, FileHandler fileHandler) {
		Scanner tableScanner = new Scanner(fileScanner.nextLine());
		tableScanner.next();
		Scanner lineScanner = new Scanner(fileScanner.nextLine());
		lineScanner.useDelimiter("	");
		int rank = lineScanner.nextInt();
		String movieTitle = lineScanner.next();
		Movie movie = new Movie();
		movie.handleTitle(movieTitle);
		fileHandler.handle(lineScanner, movie, rank);
		return movie;
		

	}
 
	
	
	
}
