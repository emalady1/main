import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NFLRoster {
  private ArrayList<Player> data;
  private String fileName = "";
  
  public NFLRoster() {
    this.data = new ArrayList<Player>();
    this.fileName = "NFLRoster.txt";
    this.loadData();
  }
  public NFLRoster(String fileName) {
    this.data = new ArrayList<Player>();
    this.fileName = fileName;
    this.loadData();
  }

  private void loadData() {
    try {
      Scanner fileScanner = new Scanner(new File(this.fileName));
      while (fileScanner.hasNextLine()) {
        String firstName = fileScanner.next();
        String lastName = "";
        while (!fileScanner.hasNextInt()) lastName += fileScanner.next() + " ";
        Integer jerseyNum = Integer.parseInt(fileScanner.next());
        Integer playerSal = Integer.parseInt(fileScanner.next());
        String team = fileScanner.nextLine();
        this.data.add(new Player(firstName.trim(), lastName.trim(), jerseyNum, playerSal, team.trim()));
      }
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  public ArrayList<Player> getPlayers() {
    return this.data;
  }
}
