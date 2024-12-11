import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MovieDatabase {
//Movies are stored by their title, which will pull up the actual Movie object
HashMap<String, Movie> database;
	

	public MovieDatabase() {
	}

	//Once all data is read from files, it is given to this class here
	public void inputData(HashMap<String, Movie> data){
		database = data;
	}
	
	public int totalAMT(int year) {
		int totalAMT = 0;
		for (String title : database.keySet()) {
			Movie movie = database.get(title);
			if (movie.getYear() == year) {
				totalAMT = totalAMT + movie.getBoxOfficeAmt();
			}
		}
		return totalAMT;
	}
	
	//The textfile is set up so that director is always the first name input, so it will always be index 0.
	public ArrayList<String> allDirectors(){
		ArrayList<String> allDirectors = new ArrayList<String>();
		for (String title : database.keySet()) {
			Movie movie = database.get(title);
			if(movie.hasCast()) {
				String director = movie.getDirector();
				allDirectors.add(director);
			}
			
		} 
		allDirectors.sort(null);
		return allDirectors;
	}
	
	//Useful in testing or making sure files are read right
	public void printMovies() {
		for (String title : database.keySet()) {
			Movie movie = database.get(title);
			movie.print();
		}
	}
	
	
	public ArrayList<String> topDirectors(int numDirectors){	
		ArrayList<String> allDirectors = allDirectors();
		ArrayList<String> duplicateList = countDuplicates(allDirectors);
		Integer maxFrequency = 0;
		HashMap<String, Integer> duplicatesMap = new HashMap<String, Integer>();
		for (String director : duplicateList) {
			Integer frequency = Collections.frequency(duplicateList, director);
			if (!duplicatesMap.containsValue(director)) {
				duplicatesMap.put(director, frequency);
			}
			if (frequency > maxFrequency) {
				maxFrequency = frequency;
			}
		}
		ArrayList<String> outputSentences = new ArrayList<String>();
		int loopTracker = 0;
		ArrayList<String> directorsToRemove = new ArrayList<String>();
		while (loopTracker < numDirectors && maxFrequency > 0) {
			for (String director : duplicatesMap.keySet()) {
				Integer directorFrequency = duplicatesMap.get(director);
				if (directorFrequency == maxFrequency){
					String fullSentence = director + " appeared in the database " + (directorFrequency + 1) + " times.";
					outputSentences.add(fullSentence);
					loopTracker = loopTracker + 1;
					directorsToRemove.add(director);
				}
			}
			for (String director : directorsToRemove) {
				duplicatesMap.remove(director);
			}
			directorsToRemove.clear();
			if (maxFrequency == 0) {
				return outputSentences;
			}
			else {
				maxFrequency = maxFrequency - 1;
			}
			
		}
		
		if (outputSentences.size() > numDirectors) {
			int numToRemove = outputSentences.size() - numDirectors;
			for (int i = 0; i < numToRemove; i++) {
				outputSentences.removeLast();
			}
		}
		return outputSentences;
	}
		
	//Returns a list with every instance of a director that appears more than once
	public ArrayList<String> countDuplicates(ArrayList<String> allDirectors){
		HashMap<Integer, String> duplicateMap = new HashMap<Integer, String>();
		ArrayList<String>namedDirectors = new ArrayList<String>();
		ArrayList<String>duplicateList = new ArrayList<String>();
		for (String director : allDirectors) {
			if(!namedDirectors.contains(director)) {
				namedDirectors.add(director);
			}
			else {
				duplicateList.add(director);
			}
		}
		duplicateList.sort(null);
		return duplicateList;
		
	}
	
	//removes all duplicate directors from a list
	public ArrayList<String> cleanDuplicates(ArrayList<String>allDirectors){
		ArrayList<String>namedDirectors = new ArrayList<String>();
		for (String director : allDirectors) {
			if(!namedDirectors.contains(director)) {
				namedDirectors.add(director);
			}
		}
		return namedDirectors;
	}
	
	//for the Cast Querey, will return either starring cast or director for chosen movie
	public ArrayList<String> identifyCast(Movie movie, boolean searchDirector){
		ArrayList<String> castDetails = new ArrayList<String>();
		ArrayList<String> cast = movie.getCast();
		if(searchDirector) {
			castDetails.add(cast.get(0) + " directed " + movie.getTitle() );
		}
		else {
			
			castDetails.add("The starring cast on this film was: ");
			for (int i = 1; i <cast.size(); i++) {
				castDetails.add(cast.get(i));
			}
		}
		return castDetails;
	}
	
	//identifies movie to be used in identifyCast().
	public Movie identifyCastQuereyMovie(boolean ratingSearch, int rank) {
		Movie rightMovie = null;
		if (ratingSearch) {
			for (String title : database.keySet()) {
				Movie movie = database.get(title);
				if (movie.getRatingRank() == rank) {
					rightMovie = movie;
				}
			}
		}
		else {
			for (String title : database.keySet()) {
				Movie movie = database.get(title);
				if (movie.getRatingMoney() == rank) {
					rightMovie = movie;
				}
			}
		}
		
		return rightMovie;
	}
	
	
	public ArrayList<String> yearInMovies(int year){
		ArrayList<String> movies = new ArrayList<String>();
		for (String title : database.keySet()) {
			Movie movie = database.get(title);
			if (movie.getYear() == year) {
				movies.add(title);
			}
		}
		return movies;
	}
}
