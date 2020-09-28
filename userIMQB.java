import java.util.Scanner;

public class userIMQB{

    public static void main(String[] args) {
        IMQB database = new IMQB();
        //LOAD MOVIES INTO DATABASE

        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO IMQB!");
        System.out.println("------------------");
        System.out.println("IMQB is an interactive movie database. You can search for your favorite movies, find information about them, or even add your own movies!");
        System.out.println();
        System.out.println("To begin, enter a command: ");
        commandMenu();
        String command = "";
        while(command != "Q"){
            command = scanner.nextLine();
            if(command.equals("S")){
                System.out.println("Enter movie name: ");
                String movie = scanner.nextLine();
                System.out.println("Retrieving movie...");
                System.out.println("Movie Title: " + movie);
                try {
                    System.out.println("Movie EIDR: " + database.get(movie));
                } catch (Exception e){
                    System.out.println("ERROR! Searching for a movie that is not in the database");
                }

            } else if(command.equals("A")){
                System.out.println("Enter movie name: ");
                String addMovie = scanner.nextLine();
                System.out.println("Now enter it's EIDR (ID number)");
                String eidr = scanner.nextLine();
                System.out.println("Inserting movie to IMQB!");
                database.put(addMovie, eidr);

            } else if(command.equals("H")){
                commandMenu();
            } else if(command.equals("Q")){
                break;
            }
        }
        System.out.println("Quitting IMQB...Have a nice day!");
    }

    public static void commandMenu(){
        System.out.println("S - Search Movie by Name");
        System.out.println("A - Add Movie to IMQB along with EIDR identifier");
        System.out.println("H - To bring up the command menu again");
        System.out.println("Q - Quit IMQB");
    }
}
