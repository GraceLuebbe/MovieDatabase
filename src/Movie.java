import java.util.ArrayList;
import java.util.Scanner;

public class Movie {
	String title;
	int year = 0;
	double rating;
	int boxOfficeAmt;
	ArrayList<String> cast = new ArrayList<String>();
	//Each .txt file has its own ranking system
	int ratingRank;
	int ratingMoney;
	int ratingAlphabetical;
	boolean hasCast = false;
	
	public void processLine(Scanner lineScanner, String title) {
		
	}
	
	//The main file will feed the line containing all of the movie data
	public void processTopRatedLine(Scanner lineScanner, int rank) {
		ratingRank = rank;
		handleYear(lineScanner);
		rating = lineScanner.nextDouble();
	}
	
	public void processGrossLine(Scanner lineScanner, int rank) {
		ratingMoney = rank;
		handleYear(lineScanner);
		boxOfficeAmt = lineScanner.nextInt();
	}
		
	public void processCastLine(Scanner lineScanner, int rank) {
		hasCast = true;
		ratingAlphabetical = rank;
		handleYear(lineScanner);
		while (lineScanner.hasNext()) {
			String castMember = lineScanner.next();
			cast.add(castMember);
		}
		
	}
	
	//makes sure that title is being set for the first time, and if not that
	//there is not an error in the line given to this class
	public void handleTitle(String movieTitle) {
		if (title == null) {
			title = movieTitle;
		}
		else {
			if(movieTitle.equals(title) == false) {
				System.out.println("Titles " + movieTitle + " and " + title + " are not matching!!!");
				System.exit(0);
			}
		}
	}
	
	//same as handleTitle() but for year
	private void handleYear(Scanner scanner) {
		int possibleYear = scanner.nextInt();
		//No movie was made in the year zero, and ints cannot be checked for null
		if (year == 0) {
			year = possibleYear;
			}
		else {
			if(possibleYear != year) {
				System.out.println("Years are not matching!!!");
				System.exit(0);
			}
		}
	}
	
	//getters
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	public ArrayList<String> getCast() {
		return cast;
	}
	
	public double getRating() {
		return rating;
}
	
	public int getBoxOfficeAmt() {
		return boxOfficeAmt;
	}
	
	public int getRatingRank() {
		return ratingRank;
	}
	
	public int getRatingMoney() {
		return ratingMoney;
	}
	
	public int getRatingAlphabetical() {
		return ratingAlphabetical;
	}
	
	public void print() {
		System.out.println(title + " " + year);
	}
	
	public boolean hasCast() {
		return hasCast;
	}
	
	//assumes all files are formatted with director first, so it is at 0 in cast
	public String getDirector() {
		return cast.get(0);
	}
}

	