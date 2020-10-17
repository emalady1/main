
public class Player {
  private String firstName;
  private String lastName;
  private String teamName;
  private Integer jerseyNumber;
  private Integer salary;
  private Player parent;
  private Player leftChild;
  private Player rightChild;
  private boolean isBlack;
  
  public Player(String firstName, String lastName, Integer jerseyNumber, Integer playerSal, String teamName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.teamName = teamName;
    this.salary = playerSal;
    this.jerseyNumber = jerseyNumber;
    this.parent = null;
    this.isBlack = false;
    this.leftChild = null;
    this.rightChild = null;
  }
  
  public Player (String firstName, String lastName, Integer jerseyNumber,
      Integer playerSal, String teamName, Player parent) {
    this(firstName, lastName, jerseyNumber, playerSal, teamName);
    this.parent = parent;
  }
  
  public String getFirstName() {
    return this.firstName;
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public String getFullName() {
    return this.firstName + ", " + this.lastName;
  }
  
  public String getTeamName() {
    return this.teamName;
  }
  
  public Integer getJerseyNumber() {
    return this.jerseyNumber;
  }
  
  public Integer getSalary() {
    return this.salary;
  }
  
  public boolean isBlack() {
    return this.isBlack;
  }
  
  public void setBlack(boolean isBlack) {
    this.isBlack = isBlack;
  }
  
  public Player getParent() {
    return this.parent;
  }
  public void setParent(Player parent) {
    this.parent = parent;
  }
  
  public Player getLeftChild() {
    return this.leftChild;
  }
  public void setLeftChild(Player child) {
    this.leftChild = child;
  }
  
  public Player getRightChild() {
    return this.rightChild;
  }
  
  public void setRightChild(Player child) {
    this.rightChild = child;
  }
}
