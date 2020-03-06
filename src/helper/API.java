package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class API {

    public String getResponse(String path){
        //String path = "http://shibe.online/api/shibes?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]";
        try {
            URL apiURL = new URL(path);
            HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + apiURL);
//            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
//            System.out.println(response.toString());
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decodeJ(String jsonString){
        String path = "http://cdn.shibe.online/shibes/"+jsonString.substring(2,jsonString.length()-2)+".jpg";
        URL imageU = null;
        try {
            imageU = new URL(path);
//            System.out.println(imageU.toString());
            return path;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
