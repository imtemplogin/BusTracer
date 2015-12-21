package ir.rowin.bustracer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Hamid-Lab on 12/21/2015.
 */
public class Webservice  extends Thread{
    public String site;
    public MainActivity act;
    @Override
    public void run() {
        super.run();
        Log.v("internetset", "statio start ");
        URL url = null;
        try {
            Log.v("internetset", "station 1 ");
            url = new URL(site);
            Log.v("internetset", "station 2 ");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.v("internetset", "station 3 ");
            con.setRequestMethod("GET");
            Log.v("internetset", "station 4 ");
            con.setDoOutput(true);
            Log.v("internetset", "station 5 ");
            con.connect();
            Log.v("internetset", "station 6 ");
            InputStream input = con.getInputStream();
            Log.v("internetset", "station 7 ");
            String s1 = convertInputStreamToString(input);
            Log.v("internetset", "station 8 ");
            Log.v("internetset", "statio read " + s1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("internetset", "station 9 ");
    }

    private static String convertInputStreamToString(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
