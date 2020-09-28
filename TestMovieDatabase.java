// --== CS400 File Header Information ==--
// Name: Tyler Richards
// Email: tjrichards@wisc.edu
// Team: KB
// Role: Test Engineer 2
// TA: Sid
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.NoSuchElementException;

public class TestMovieDatabase {
  // Verifies that get correctly throws the NoSuchElementException when searching for an element
  // that is not in the hash table.
  public static boolean testGet() {
    MovieDatabase mdb = new MovieDatabase(10);
    mdb.put("Avengers: Endgame", 4154796);
    mdb.put("Avengers: Infinity War", 4154756);
    mdb.put("Black Panther", 1825683);
    try {
      mdb.get("Tenet");
    } catch (NoSuchElementException nsee) {
      return true;
    }
    System.out.println("testGet failed");
    return false;
  }

  // Tests to make sure that multiple elements with the same key are thrown out instead of
  // attempting to store those in the hashTable.
  public static boolean testAdd() {
    MovieDatabase mdb = new MovieDatabase(10);
    mdb.put("Avengers: Endgame", 4154796);
    mdb.put("Avengers: Infinity War", 4154756);
    mdb.put("Black Panther", 1825683);
    if (!(mdb.get("Black Panther").equals("Black Panther"))) {
      System.out.println("testAdd() failed");
      return false;
    }
    return true;
  }

  // Verifies that get() doesn't try to feed back an element from a linkedList created from a
  // collision that doesn't match the given key.
  public static boolean testCollision() {
    MovieDatabase mdb = new MovieDatabase(10);
    mdb.put("Avengers: Endgame", 4154796);
    mdb.put("Avengers: Infinity War", 4154756);
    mdb.put("Black Panther", 1825683);
    mdb.put("Honey I Shrunk the Kids", 97523);
    mdb.put("Black Widow", 3480822);
    try {
      if (mdb.get("Black Widow").equals("Black Widow")) {
        return true;
      }
    } catch (NoSuchElementException nsee) {
      return false;
    }
    System.out.println("testCollision() failed");
    return false;
  }

  // Tests the expansion of the hashTables by storing to more than 80% of the hashTable's size,
  // causing a rehash. Verifies the growth was successful by checking that capacity = 2xoldCapacity.
  public static boolean testExpand() {
    MovieDatabase mdb = new MovieDatabase(10);
    mdb.put("Avengers: Endgame", 4154796);
    mdb.put("Avengers: Infinity War", 4154756);
    mdb.put("Black Panther", 1825683);
    mdb.put("Black Widow", 3480822);
    mdb.put("Iron Man", 0371746);
    mdb.put("Iron Man 2", 1228705);
    mdb.put("Iron Man 3", 1300854);
    mdb.put("Avengers: Age of Ultron", 2395427);
    if (mdb.capacity != 20) {
      System.out.println("testExpand() failed");
      return false;
    }
    return true;
  }

  // Tests the clear() method by verifying that the hashTable's size is set to 0.
  public static boolean testClear() {
    MovieDatabase mdb = new MovieDatabase(10);
    mdb.put("Avengers: Endgame", 4154796);
    mdb.put("Avengers: Infinity War", 4154756);
    mdb.put("Black Panther", 1825683);
    mdb.clear();
    if (mdb.size() != 0) {
      System.out.println("testClear() failed");
      return false;
    }
    return true;
  }

  public static void main(String args[]) {
    testGet();
    testAdd();
    testCollision();
    testExpand();
    testClear();
  }
}
