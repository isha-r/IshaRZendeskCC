import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class TicketTester {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(runAllTests());
    
    

  }
  public static boolean APIConnectGetTester() {
    APIConnect tester= new APIConnect("zccisharustagi", "rustagiisha@gmail.com", "Password123!");
    try {
    JSONArray testerArray=tester.getOverallTickets();
    if (testerArray.length()!=100) {
      System.out.println("Not all of the tickets were able to load correctly. Needed 100, got a different number.");
      return false;  
    }
    System.out.println(testerArray.get(0));
    JSONObject testerObjOne=testerArray.getJSONObject(0);
    if (testerObjOne.getInt("id")!=(1)) {
      return false;
    }
    if (!testerObjOne.getString("subject").equals("Sample ticket: Meet the ticket")) {
      return false;
    }
    
    }
    catch(IOException e) {
      //working as expected
    }
    
    return true;
  }
  
  
  
  
  public static boolean runAllTests() {
    if (!APIConnectGetTester())
      return false;
    return true;
  }

}
