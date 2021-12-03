


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;



public class APIConnect {
  private static String subdomain;
  private static String username;
  private static String password;

  public APIConnect(String subdomain, String username, String password) {
    APIConnect.subdomain = subdomain;
    APIConnect.username = username;
    APIConnect.password = password;
  }



  public JSONArray getOverallTickets() throws IOException {
    String output = sendGET();
    JSONObject obj = new JSONObject(output);
    JSONArray arr = obj.getJSONArray("tickets");
    return arr;
  }

  private static String sendGET() throws IOException {
    try {
      URL obj = new URL("https://" + subdomain + ".zendesk.com/api/v2/tickets.json");
      String authorization = username + ":" + password;
      byte[] encoded = authorization.getBytes(Charset.forName("UTF-8"));

      String encoding = Base64.getEncoder().encodeToString(encoded);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("GET");
      con.setDoOutput(true);

      con.setRequestProperty("Authorization", "Basic " + encoding);
      int responseCode = con.getResponseCode();
      System.out.println("GET Response Code :: " + responseCode);
      
      //throwing an exception if there's an error with the GET order (>400)
      if (responseCode>=400) {
        throw new Exception("the GET function did not go through properly and the API did not connect. We apologize for the inconvenience!");
      }
      
      if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        return response.toString();
      } else {
        System.out.println("GET request not working");
      }
    } catch (Exception e) {
      e.getStackTrace();
      System.out.println(e.getMessage());
    }
    return "";

  }

}
