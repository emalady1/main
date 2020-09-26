
public class Movie {

	String name;
	String eidr;
	String genre;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * creates an object of type movie
	 * @param givenName user inputted name of movie
	 * @param givenEidr user inputted EIDR identification number
	 * @param givenGenre user inputted genre
	 */
	public Movie(String givenName, String givenEidr, String givenGenre) {
		name = givenName;
		eidr = givenEidr;
		genre = givenGenre;
	}

	/**
	 * @return string with name of the movie object
	 */
	public String toString() {
		return (name);
	}

}
