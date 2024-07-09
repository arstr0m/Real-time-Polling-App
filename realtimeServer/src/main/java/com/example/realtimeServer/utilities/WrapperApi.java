package com.example.realtimeServer.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class WrapperApi {
   public static String getData(String search){
       try {
           if(search.isEmpty()){
               return "search something";
           }
           String url = "https://pokeapi.co/api/v2/pokemon/" + search;

           URL obj = new URL(url);

           HttpURLConnection con = (HttpURLConnection) obj.openConnection();
           con.setRequestMethod("GET");

           con.setRequestProperty("User-Agent", USER_AGENT);

           int responseCode = con.getResponseCode();
           System.out.println("Response Code : " + responseCode);

           BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
           String inputLine;
           StringBuffer response = new StringBuffer();

           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
           in.close();

          return response.toString();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "we couldn't retrieve data from server";
   }

}
