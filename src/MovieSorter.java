import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class MovieSorter {
	//This class is for gathering and initializing the data
	private final String TOP_RATED_FILE = "imdb_movies_toprated.txt";
	private final String HIGHEST_GROSSING_FILE = "imdb_movies_gross.txt";
	private final String CAST_LIST_FILE = "imdb_movies_cast.txt";
	private final String FILE_PREFIX = "movieData/";
	private HashMap <File, FileHandler> fileAndHandler = new HashMap<File, FileHandler>();
	HashMap<String, Movie> data = new HashMap<String, Movie>();
	MovieDatabase database;
	
	//gives reference for later when data is transferred
	public MovieSorter(MovieDatabase database) {
		this.database = database;
	}
	
	public void assembleData() {
		assembleFiles();
		for(File file : fileAndHandler.keySet()) {
			try {
			Scanner fileScanner = new Scanner(file);
			FileHandler fileHandler = fileAndHandler.get(file);
			readFile(fileScanner, fileHandler);
			fileScanner.close();
			}
			catch (FileNotFoundException e){
				System.out.println("File not found");
			}
			
			
		}
		database.inputData(data);
	}
	
	private void readFile(Scanner fileScanner, FileHandler fileHandler) {
		//This removes the first line of the files which have labels for the data
		Scanner topRowScanner = new Scanner(fileScanner.nextLine());
		topRowScanner.next();
		while (fileScanner.hasNextLine()) {
			Movie movie;
			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			lineScanner.useDelimiter("	");
			int rank = lineScanner.nextInt();
			String movieTitle = lineScanner.next();
			if (data.containsKey(movieTitle)) {
				movie = data.get(movieTitle);
				}
			else {
				movie = new Movie();
				movie.handleTitle(movieTitle);
				data.put(movieTitle, movie);
			}
			fileHandler.handle(lineScanner, movie, rank);
			lineScanner.close();	
		}
	}
	

	//To add any new files, add them to this function (with the literals up above,
	// create the new FileHandler type, and add it to fileAndHandler.
	private void assembleFiles() {
		File castFile = new File(FILE_PREFIX + CAST_LIST_FILE);
		FileHandler castHandler = new CastHandler();
		fileAndHandler.put(castFile, castHandler);
		File grossFile = new File(FILE_PREFIX + HIGHEST_GROSSING_FILE);
		FileHandler grossHandler = new GrossHandler();
		fileAndHandler.put(grossFile, grossHandler);
		File topRated = new File(FILE_PREFIX + TOP_RATED_FILE);
		FileHandler topHandler = new RatingHandler();
		fileAndHandler.put(topRated, topHandler);
			
	}
	

}
