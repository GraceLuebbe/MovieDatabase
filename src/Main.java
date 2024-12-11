//@Author Grace Luebbe
public class Main {
	public static void main(String[] args) {
		MovieDatabase database = new MovieDatabase();
		MovieSorter movieSorter = new MovieSorter(database);
		movieSorter.assembleData();
		UI ui = new UI(database);
		System.out.println("Welcome to the all-knowing movie database! Would you like to ask about:");
		ui.askForQuerey();
	}
		
}
