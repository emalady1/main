import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class loads a list of movies with their corresponding EDIR number to an array.
 * @author jack
 *
 */
public class IMDB {
  static ArrayList<Movie> movieList;

  public IMDB() {

    movieList = new ArrayList<Movie>();
    try {
      loadMovies();

    } catch (IOException e) {
      System.out.println("error caught");

    }
    System.out.println(movieList.toString());
  }

  /**
   * loads the movie files and adds them to an array of movie objects
   * @throws IOException if file is not in correct format
   */
  public static void loadMovies() throws IOException {
    File movies = new File("/Users/jack/eclipse-workspace/Project One "
        + "Movie Database/src/Movies.txt"); 

    Scanner scanner = new Scanner(movies);

    while (scanner.hasNextLine()) {
      String[] split = scanner.nextLine().split(" ");

      Movie curr = new Movie(split[0], split[1]);

      movieList.add(curr);

    }
  }

}
