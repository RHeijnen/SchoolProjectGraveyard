package info.androidhive.materialnavbar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import info.androidhive.materialnavbar.fragment.InformationFragment;


/**
 * Created by mark on 21-5-15.
 */
public class JSON {
    public ArrayList<Fact> factAllList = null;
    private String jsonString = "";
    private JSONArray json = null;
    private StringBuilder builder = new StringBuilder();
    private Context c = null;
    private View v = null;

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public JSON(Context con, View vw, String type) throws JSONException, ExecutionException, InterruptedException {

            new FactsLoader(con, vw, this, type).execute().get();
            c = con;
            v = vw;
            json = getQuoteArray(jsonString);
            factAllList = getFactsList(type);

    }
    public JSON(Context con, View vw, String type, boolean internet) throws JSONException, ExecutionException, InterruptedException {

        new FactsLoader(con, vw, this, type).execute().get();
        c = con;
        v = vw;
        json = getQuoteArray(jsonString);
        factAllList = getFactsList(type);

    }

    public ArrayList<Fact> getGeneralFact() {

        ArrayList<Fact> x = new ArrayList<Fact>();
        x.add(factAllList.get(randInt(0, factAllList.size() - 1)));
        x.add(factAllList.get(randInt(0, factAllList.size() - 1)));


        return x;
    }

    public ArrayList<Fact> getFactAllList() {
        return factAllList;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public JSONArray getFacts() {

        return json;
    }



    public ArrayList<Fact> getFactsList(String type) throws JSONException {
        int arrayLength = json.length();
        ArrayList<Fact> allFacts = new ArrayList<Fact>(arrayLength);
        for (int i = 0; i < arrayLength; i++) {
            JSONObject y = json.getJSONObject(i);
            Fact JSONextractedFact = null;
            switch (type) {
                case "quote":
                    JSONextractedFact = new Fact(y.get("author").toString(), y.get("content").toString());
                    break;
                case "history":
                    String year = y.get("year").toString();
                    JSONextractedFact = new Fact(year, y.get("content").toString());
                    break;
                case "birthday":
                    JSONextractedFact = new Fact(y.get("fullname").toString(), ageModifier(y.get("age").toString()));
                    break;
                default:
                    JSONextractedFact = new Fact(y.get("content").toString());
                    break;

            }

            allFacts.add(JSONextractedFact);
        }
        Log.d("allFacts : ", allFacts.toString());
        return allFacts;
    }

    private String ageModifier(String a) {
        if (!a.contains("-")) {
            int age = Integer.parseInt(a);
            int year = Integer.parseInt(new AndroidDate().getYearNumber());
            int difference = age + ((year - 2015) + 1);
            Log.d("Age after convertion", String.valueOf(difference));
            return String.valueOf(difference);
        } else {
            return a;
        }
    }

    public String requestJSON(String type) {
        HttpResponse response = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://www.bartfokker.nl/factorial/");
        String respJSON = null;
        try {
            List<NameValuePair> nameValuePairs;
            AndroidDate y = new AndroidDate();
            String date = "0004-";
            date += y.getFullmonthNumber() + "-";
            date += y.getDayNumber();
            Log.i("Date: ", date);
            // Add your data
            switch (type) {
                case "facts":
                    nameValuePairs = new ArrayList<NameValuePair>(3);
                    nameValuePairs.add(new BasicNameValuePair("table", type));
                    nameValuePairs.add(new BasicNameValuePair("datum", date));
                    nameValuePairs.add(new BasicNameValuePair("categorie", "random"));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    break;
                default:
                    nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("table", type));
                    //nameValuePairs.add(new BasicNameValuePair("datum", date.toString()));
                    nameValuePairs.add(new BasicNameValuePair("datum", date));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    break;
            }
            // Execute HTTP Post Request


            response = httpclient.execute(httppost);

            respJSON = inputStreamToString(response.getEntity().getContent()).toString();

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

            if (respJSON == null) {

            }
        }
//        Log.e("JSON Response: ", respJSON);
        return respJSON;
    }

    private JSONArray convertStringToJSONarray(String JSON) throws JSONException {
        JSONObject JSONObject = new JSONObject(JSON);
        JSONArray JSONArray = JSONObject.getJSONArray("records");
        return JSONArray;

    }

    private JSONArray getQuoteArray(String JSON) throws JSONException {
        JSONArray x = new JSONArray(JSON);
        Log.d("JSONARRAY", x.toString());
        return x;
    }

    private StringBuilder inputStreamToString(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();
        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        // Return full string
        return total;
    }
}