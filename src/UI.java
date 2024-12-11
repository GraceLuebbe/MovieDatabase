import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UI {
	private final String TOTAL_AMT = "$";
	private final String ALL_DIRECTORS = "D";
	private final String TOP_DIRECTORS = "TD";
	private final String CAST_DETAILS = "C";
	private final String YEAR_IN_MOVIES = "Y";
	private final String EXIT = "E";
	MovieDatabase database; 
	
	UI(MovieDatabase database){
		this.database = database;
		
	}
	
	//To add a new querey: add the input string with the other finals above,
	//Create a print line here that defines the function, and add a case corresponding to 
	//your new function.
	public void askForQuerey(){
		System.out.println(TOTAL_AMT + " - The total amount of box office earnings in a given year!");
		System.out.println(ALL_DIRECTORS + " - All directors, alphabetized!");
		System.out.println(TOP_DIRECTORS + " - The directors who have made the most big hits!");
		System.out.println(CAST_DETAILS + " - Find the names of the director or cast for the most successful films!");
		System.out.println(YEAR_IN_MOVIES + " - Get all movies for a given year!");
		System.out.println(EXIT + " - Exit");
		
		Scanner userScanner = new Scanner(System.in);
		String userChoice = userScanner.next();
		//Switch system is not infinitely scalable, but holds a good number of options.
		switch (userChoice) {
		case "$" -> totalAMT(userScanner);
		case "D" -> allDirectors();
		case "TD" -> topDirectors(userScanner);
		case "C" -> castDetails(userScanner);
		case "Y" -> yearInMovies(userScanner);
		case "E" -> System.exit(0);
		}
	}
	
	public void totalAMT(Scanner userScanner) {
		System.out.println("What year's profits would you like to see?");
		int year = userScanner.nextInt();
		System.out.println("The profits of " + year + " were $" + database.totalAMT(year) + ".");
		askForQuerey();
	}
	
	public void allDirectors() {
		System.out.println("Here is an alphabetized list of all directors:");
		ArrayList<String> allDirectors = database.allDirectors();
		allDirectors = database.cleanDuplicates(allDirectors);
		for (String director : allDirectors) {
			System.out.println(director);
		}
		askForQuerey();
	}
	
	public void topDirectors(Scanner userScanner) {
		System.out.println("How many directors?");
		int numDirectors = userScanner.nextInt();
		ArrayList<String> sentances = database.topDirectors(numDirectors);
		System.out.println(sentances.size());
		for (String sentance : sentances) {
			System.out.println(sentance);
		}
		askForQuerey();
	}
	
	//Hard-coded with booleans for finding Director vs Cast and priceRank vs ratingRank
	public void castDetails(Scanner userScanner) {
		System.out.println("Director or cast members? (D or C)");
		String personDecision = userScanner.next();
		System.out.println("Do you want to find it by rating or profit? (R or P)");
		String searchDecision = userScanner.next();
		boolean searchDirector = true;
		boolean searchByRating = true;
		switch(personDecision) {
		case "D" -> searchDirector = true;
		case "C" -> searchDirector = false;
		}
		switch(searchDecision) {
		case "R" -> searchByRating = true;
		case "P" -> searchByRating = false;
		}
		System.out.println("What ranking do you want to search for?");
		int rank = userScanner.nextInt();
		Movie rightMovie = database.identifyCastQuereyMovie(searchByRating, rank);
		ArrayList<String> castDetails = database.identifyCast(rightMovie, searchDirector);
		for (String string : castDetails) {
			System.out.println(string);
		}
		askForQuerey();
		
	}
	
	public void yearInMovies(Scanner userScanner) {
		System.out.println("Which year would you like?");
		int selectedYear = userScanner.nextInt();
		ArrayList<String> movies = database.yearInMovies(selectedYear);
		System.out.println("The movies that released in " + selectedYear + " are");
		for (String movieTitle : movies) {
			System.out.println(movieTitle);
		}
		askForQuerey();
	}
}
