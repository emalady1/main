import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Creates an array of linkedlist[] holding movieName-movieEIDR pair objects.
 * Uses a hash function to add and remove
 * @author lorko
 *
 */
public class MovieDatabase<KeyType, ValueType> implements MapADT<KeyType, ValueType>{
  private LinkedList<Movie>[] database;
  private LinkedList<Movie> orderedList;
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
      retString += "[id: " + orderedList.get(i).getId() + "\n" + " name: "
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
  public boolean add(KeyType movieName, ValueType movieEIDR) {
    if (movieName == null) {
      return false;
    }
    movieName = (KeyType) movieName.toString().toLowerCase();
    int index = hash(movieName);
    this.size++;
    Movie newMovie = new Movie(movieName, movieEIDR);
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
  public KeyType search(KeyType movieName) throws NoSuchElementException {
    if (movieName == null) {
      throw new NoSuchElementException("movieName cannot be null");
    }
    int index = hash(movieName);
    if (this.database[index] != null) {
      return getNameHelper(movieName, this.database[index]);
    }
    throw new NoSuchElementException("No such element exists");
  }
  
  private void addToList(Movie newMovie) {
    if (this.orderedList == null) {
      this.orderedList.addFirst(newMovie);
      this.orderedList.getFirst().setId(this.size);
    }
    else if (!movieExists((KeyType) newMovie.getName(), this.orderedList)) {
      this.orderedList.add(newMovie);
      this.orderedList.getLast().setId(this.size);
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
  private KeyType getNameHelper(KeyType movieName, LinkedList<Movie> list)
      throws NoSuchElementException {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(movieName)) {
        return (KeyType) list.get(i).getName();
      }
    }
    throw new NoSuchElementException("No such element exists");
  }
  
  private ValueType getEidrHelper(KeyType movieName, LinkedList<Movie> list)
      throws NoSuchElementException {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(movieName)) {
        return (ValueType) list.get(i).getEIDR();
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
  private boolean movieExists(KeyType movieName, LinkedList<Movie> linkedList) {
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
  private float getLoadCapacity() {
    return ((float) this.size) / this.capacity;
  }

  /**
   * Expands the hash table, doubling its current capacity and rehashes all objects within it.
   */
  private void expand() {
    LinkedList<Movie>[] oldDatabase = this.database;
    this.capacity *= 2;
    this.size = 0;
    this.database = new LinkedList[this.capacity];
    for (int i = 0; i < oldDatabase.length; i++) {
      if (oldDatabase[i] != null) {
        for (int j = 0; j < oldDatabase[i].size(); j++) {
          KeyType movieName = (KeyType) oldDatabase[i].get(j).getName();
          ValueType movieEIDR = (ValueType) oldDatabase[i].get(j).getEIDR();
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
  private int hash(KeyType movieName) {
    return Math.abs(movieName.hashCode() % this.capacity);
  }

  /**
   * Creates and insert a movie into the database with the specified
   * movie name and eidr
   * @param movieName name of the movie
   * @param movieEidr eidr of the movie
   * @return returns true if successfully added, false otherwise
   */
  @Override
  public boolean put(KeyType movieName, ValueType movieEidr) {
    return add(movieName, movieEidr);
  }

  /**
   * Gets and returns the Eidr of a move. To search for a movie,
   * use search("movieName")
   */
  @Override
  public ValueType get(KeyType movieName) throws NoSuchElementException {
    if (movieName == null) {
      throw new NoSuchElementException("movieName cannot be null");
    }
    int index = hash(movieName);
    if (this.database[index] != null) {
      return getEidrHelper(movieName, this.database[index]);
    }
    throw new NoSuchElementException("No such element exists");
  }

  /**
   * Gets the size of the database
   * @return returns the size
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Checks whether a movie is in the database or not
   * @param movieName movie to look for
   * @return returns true if movie exists, false otherwise
   */
  @Override
  public boolean containsKey(KeyType movieName) {
    if (movieName == null) {
      return false;
    }
    int index = hash(movieName);
    if (this.database[index] != null) {
      return movieExists(movieName, this.database[index]);
    }
    return false;
  }

  /**
   * Removes the movie from the hashtable and in the ordered list
   * @param movieName name of the movie
   * @return returns the eidr of the movie removed
   */
  @Override
  public ValueType remove(KeyType movieName) {
    if (!containsKey(movieName)) {
      return null;
    }
    int index = hash(movieName);
    for (int i = 0; i < this.database[index].size(); i++) {
      if (this.database[index].get(i).getName().equals(movieName)) {
        ValueType returnVal = (ValueType) this.database[index].get(i).getEIDR();
        removeFromList(this.database[index].get(i));
        this.database[index].remove(i);
        this.size--;
        
        return returnVal;
      }
    }
    return null;
  }

  /**
   * Removes a movie from the ordered list
   * @param Movie movie to remove
   */
  private void removeFromList(Movie Movie) {
    if (this.orderedList.removeFirstOccurrence(Movie)) {
      for(int i = 0; i < this.orderedList.size(); i++) {
        Movie current = this.orderedList.get(i);
        if (current.getId() > Movie.getId()) {
          this.orderedList.get(i).setId(current.getId() - 1);
        }
      }
    }
  }

  /**
   * Clears everything in the database and ordered list
   */
  @Override
  public void clear() {
    this.database = new LinkedList[this.capacity];
    this.orderedList = new LinkedList();
    this.size = 0;
    
  }
}
