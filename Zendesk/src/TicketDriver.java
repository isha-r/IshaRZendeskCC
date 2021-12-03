import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

/**
 * This class implements a text-based driver for the TicketViewer
 *
 */
public class TicketDriver {
  private static final String WELCOME_MSG =
      "=============   Welcome to the Ticket Viewer  " + "=============";
  private static final String QUIT_MSG =
      "==============    Thank you for using our Ticket Viewer!!!!!    ==============";
 

 

  

  /**
   * Main method for the text-based Tile Matching Driver
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // Display Welcome message
    System.out.println(WELCOME_MSG);

    System.out.println();
    System.out.println();
    // start playing
    // driver();

    // System.out.print("Enter whether you want to quit entirely. If so, type \"quit\". Otherwise,
    // type 'y' to use the ticket viewer");
    Scanner sc = new Scanner(System.in);
    // input = sc.nextLine()+"lon";
    String input = "";
    int page=0;
    int pageEnd;

    while (!input.equals("quit")) {
      System.out.println(
          "Enter \"quit\" if you would like to exit the program. Otherwise, type \"start!\" to use the ticket viewer!");
      // Scanner sc = new Scanner (System.in);
      input = sc.next();
      // String s1 = sc.next();
      // System.out.println(input);
      if (input.equals("start!")) {
        System.out.println("Enter your subdomain: (Please be careful with the details)");
        String subD = sc.next();
        System.out.println("Enter your username:");
        String userN = sc.next();
        System.out.println("Enter your password:");
        String passW = sc.next();

        System.out.println();
        System.out.println();

        APIConnect user = new APIConnect(subD, userN, passW);
        try {
          // get an array of all the valid tile colors
          JSONArray arr = user.getOverallTickets();
          // Scanner to read user input line commands
          String subject;
          String updatedDate;
          int id;
          String created;
          String description;

          while (!input.equals("quit")) {
            System.out.println("Enter \"quit\" to quit. Otherwise, type 'y' to list tickets, "
                + "\n"
                + "'n' for next page, 'p' for returning to the first page, and 't' to look up a ticket.");
            input = sc.next();
            if (input.equals("y")) {

              page = 0;
              pageEnd = page + 25;
              if (pageEnd > arr.length() - 1) {
                pageEnd = arr.length();
              }


              for (int i = page; i < pageEnd; i++) {
                JSONObject nu = arr.getJSONObject(i); //

                created = nu.getString("created_at");
                created = created.substring(0, 10);
                id = nu.getInt("id");
                subject = nu.getString("subject");
                updatedDate = nu.getString("updated_at");
                updatedDate = updatedDate.substring(0, 10);
                System.out.println(
                    "Ticket #" + id + " made on " + created + " and last updated: " + updatedDate);

                System.out.println("Subject of the message: " + subject);
                System.out.println();
              }
            }
            if (input.equals("n")) {


              page += 25;
              pageEnd = page + 25;
              if (pageEnd > arr.length() - 1) {
                pageEnd = arr.length();
              }


              for (int i = page; i < pageEnd; i++) {
                JSONObject nu = arr.getJSONObject(i); //

                created = nu.getString("created_at");
                created = created.substring(0, 10);
                id = nu.getInt("id");
                subject = nu.getString("subject");
                updatedDate = nu.getString("updated_at");
                updatedDate = updatedDate.substring(0, 10);
                System.out.println(
                    "Ticket #" + id + " made on " + created + " and last updated: " + updatedDate);

                System.out.println("Subject of the message: " + subject);
                System.out.println();
              }
            }
            if (input.equals("p")) {

              page = 0;
              pageEnd = page+25;
              
              if (pageEnd > arr.length() - 1) {
                pageEnd = arr.length();
              }


              for (int i = page; i < pageEnd; i++) {
                JSONObject nu = arr.getJSONObject(i); //

                created = nu.getString("created_at");
                created = created.substring(0, 10);
                id = nu.getInt("id");
                subject = nu.getString("subject");
                updatedDate = nu.getString("updated_at");
                updatedDate = updatedDate.substring(0, 10);
                System.out.println(
                    "Ticket #" + id + " made on " + created + " and last updated: " + updatedDate);

                System.out.println("Subject of the message: " + subject);
                System.out.println();
              }

            }
            if (input.equals("t")) {

              System.out.println("What ticket would you like to view? From 1 to " + arr.length());
              input = sc.next();
              int x = Integer.parseInt(input);
              JSONObject nu = arr.getJSONObject(x-1); //

              created = nu.getString("created_at");
              created = created.substring(0, 10);
              id = nu.getInt("id");
              subject = nu.getString("subject");
              updatedDate = nu.getString("updated_at");
              updatedDate = updatedDate.substring(0, 10);
              description = nu.getString("description");
              System.out.println();
              System.out.println(
                  "Ticket #" + id + " made on " + created + " and last updated: " + updatedDate);
              System.out.println();
              System.out.println(
                  "Subject of the message: \n" + subject + "\n"+"\n"+ "Message Description: \n" + description);
              System.out.println();
            }
          }
        }
        

        catch (IOException e) {
          e.getStackTrace();
          System.out.println("The stream from the online connection to the API is interrupted \n "
              + "or there is some other error reading the data. We apologize for the inconvenience, please try again later.");
        }
        catch(Exception e) {
          System.out.println(e.getMessage());
        }


      }

    }
    sc.close();



    System.out.println("The loop has been ended"); // Prints if the loop is ended by "quit" input
    System.out.println();

    // Display Good bye message
    System.out.println(QUIT_MSG);

  }
}
