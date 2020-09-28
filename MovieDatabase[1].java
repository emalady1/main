
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Creates an this.database of linkedlist[] holding movieName-movieEIDR pair objects.
 * 
 * @author lorko
 *
 */
public class MovieDatabase {
  private LinkedList<MovieObject>[] database;
  private LinkedList<MovieObject> orderedList;
  private int size;
  private int capacity;

  /**
   * Constructor to create a MovieDatabase object. Instantiates the capacity to 10.
   */
  public MovieDatabase() {
    this.capacity = 10;
    this.size = 0;
    this.database = new LinkedList[this.capacity];
    this.orderedList = new LinkedList();
  }

  /**
   * Constructor to create a MovieDatabase object
   * 
   * @param capacity Instantiates this.capacity to capacity.
   */
  public MovieDatabase(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.database = new LinkedList[this.capacity];
    this.orderedList = new LinkedList();
  }

  /**
   * Creates a string of the movies in the database based on the order they were added
   * 
   * @return a string of the movie in the order they were added
   */
  public String filter() {
    String retString = "";
    for (int i = 0; i < this.orderedList.size(); i++) {
      retString += "[id: " + orderedList.get(i).getID() + "\n" + " name: "
          + orderedList.get(i).getName() + "\n" + " EIDR: " + orderedList.get(i).getEIDR() + "]\n";
    }
    return retString;
  }

  /**
   * Adds a movieName-movieEIDR pair object to the hash table.
   * 
   * @param movieName movieName of the object
   * @param movieEIDR movieEIDR of the object
   */
  public boolean add(String movieName, int movieEIDR) {
    if (movieName == null) {
      return false;
    }
    int index = hash(movieName);
    this.size++;
    MovieObject newMovie = new MovieObject(movieName, movieEIDR, this.size);
    if (this.database[index] == null) {
      this.database[index] = new LinkedList();
      this.database[index].addFirst(newMovie);
      if (this.getLoadCapacity() >= 0.80) {
        expand();
      }
      addToList(newMovie);
      return true;
    } else if ((this.database[index] != null) && (!movieExists(movieName, this.database[index]))) {
      this.database[index].add(newMovie);
      if (this.getLoadCapacity() >= 0.80) {
        expand();
      }
      addToList(newMovie);
      return true;
    }
    this.size--;
    return false;
  }

  /**
   * Retrieves a movieName-movieEIDR pair object from the hash table and return the name
   * 
   * @param movieName the object's movieName that will be return if it exists
   * @throws throws a NoSuchElementException if the is null or doesn't exist
   * @return returns the movieName of the object whose movieName matches
   */

  public String search(String movieName) throws NoSuchElementException {
    if (movieName == null) {
      throw new NoSuchElementException("movieName cannot be null");
    }
    int index = hash(movieName);
    if (this.database[index] != null) {
      return getHelper(movieName, this.database[index]);
    }
    throw new NoSuchElementException("No such element exists");
  }
  
  public int size() {
    return this.size;
  }
  
  public int getCapacity() {
    return this.capacity;
  }
  
  private void addToList(MovieObject newMovie) {
    if (this.orderedList == null) {
      this.orderedList.addFirst(newMovie);
    }
    else if (!movieExists(newMovie.getName(), this.orderedList)) {
      this.orderedList.add(newMovie);
    }
  }
 
  /**
   * Helper method to look for a movie name
   * 
   * @param movieName the movieName to look for
   * @param list      the linkedlist to look in
   * @return returns the movieEIDR from the movieName if found, otherwise throws a
   *         NoSuchElementException
   */
  private String getHelper(String movieName, LinkedList<MovieObject> list)
      throws NoSuchElementException {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(movieName)) {
        return list.get(i).getName();
      }
    }
    throw new NoSuchElementException("No such element exists");
  }

  /**
   * Helper method to check if a movieName exists.
   * 
   * @param movieName  movieName to check for
   * @param linkedList linkedlist to check in
   * @return returns true if the movieName is found, false otherwise
   */
  private boolean movieExists(String movieName, LinkedList<MovieObject> linkedList) {
    for (int i = 0; i < linkedList.size(); i++) {
      if (linkedList.get(i).getName().equals(movieName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the load capacity to determine if the table should expand or not.
   * 
   * @return returns the load capacity as a float.
   */
  public float getLoadCapacity() {
    return ((float) this.size) / this.capacity;
  }

  /**
   * Expands the hash table, doubling its current capacity and rehashes all objects within it.
   */
  private void expand() {
    LinkedList<MovieObject>[] oldDatabase = this.database;
    this.capacity *= 2;
    this.size = 0;
    this.database = new LinkedList[this.capacity];
    for (int i = 0; i < oldDatabase.length; i++) {
      if (oldDatabase[i] != null) {
        for (int j = 0; j < oldDatabase[i].size(); j++) {
          String movieName = (String) oldDatabase[i].get(j).getName();
          int movieEIDR = oldDatabase[i].get(j).getEIDR();
          add(movieName, movieEIDR);
        }
      }
    }
  }

  /**
   * Gets the hash index of a movieName
   * 
   * @param movieName movieName to obtain the hash movieEIDR from
   * @return returns the hash index
   */
  private int hash(String movieName) {
    return Math.abs(movieName.hashCode() % this.capacity);
  }
}
