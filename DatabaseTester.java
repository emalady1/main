import java.util.NoSuchElementException;

/*
 * Tests the functionality of the MovieDatabase.java implementation
 * 
 * @author trichards
 * 
 */

public class DatabaseTester {
  /*
   * Verifies that get correctly throws the NoSuchElementException when searching for an element
   * that is not in the hash table.
   * 
   * @return true if we receive NSEE, false otherwise
   */
  public static boolean testGet() {
    MovieDatabase<String, Integer> mdb = new MovieDatabase(10);
    mdb.add("Avengers: Endgame", 4154796);
    mdb.add("Avengers: Infinity War", 4154756);
    mdb.add("Black Panther", 1825683);
    try {
      mdb.search("Tenet");
    } catch (NoSuchElementException nsee) {
      return true;
    }
    System.out.println("testGet() failed");
    return false;
  }

  /*
   * Tests to make sure that multiple elements with the same key are thrown out instead of
   * attempting to store those in the hashTable.
   * 
   * @return true
   */
  public static boolean testAdd() {
    MovieDatabase<String, Integer> mdb = new MovieDatabase(10);
    mdb.add("Avengers: Endgame", 4154796);
    mdb.add("Avengers: Infinity War", 4154756);
    mdb.add("Black Panther", 1825683);
    if (!(mdb.search("Black Panther").equals("Black Panther"))) {
      System.out.println("testAdd() failed");
      return false;
    }
    return true;
  }

  // Verifies that get() doesn't try to feed back an element from a linkedList created from a
  // collision that doesn't match the given key.
  public static boolean testGetWithCollision() {
    MovieDatabase<String, Integer> mdb = new MovieDatabase(10);
    mdb.add("Avengers: Endgame", 4154796);
    mdb.add("Avengers: Infinity War", 4154756);
    mdb.add("Black Panther", 1825683);
    mdb.add("Honey I Shrunk the Kids", 97523);
    mdb.add("Black Widow", 3480822);
    try {
      if (mdb.search("Black Widow").equals("Black Widow")) {
        return true;
      }
    } catch (NoSuchElementException nsee) {
      return false;
    }
    System.out.println("testGetWithCollision() failed");
    return false;
  }

  // Tests the expansion of the hashTables by storing to more than 80% of the hashTable's size,
  // causing a rehash. Verifies the growth was successful by checking that capacity = 2xoldCapacity.
  public static boolean testExpand() {
    MovieDatabase<String, Integer> mdb = new MovieDatabase(10);
    mdb.add("Avengers: Endgame", 4154796);
    mdb.add("Avengers: Infinity War", 4154756);
    mdb.add("Black Panther", 1825683);
    mdb.add("Black Widow", 3480822);
    mdb.add("Iron Man", 0371746);
    mdb.add("Iron Man 2", 1228705);
    mdb.add("Iron Man 3", 1300854);
    mdb.add("Avengers: Age of Ultron", 2395427);
    if (mdb.getCapacity() != 40) {
      System.out.println("testExpand() failed");
      return false;
    }
    return true;
  }

  public static void main(String args[]) {
    testGet();
    testAdd();
    testGetWithCollision();
    testExpand();
  }
}
