package pl.drupalcampwroclaw.drupalcamp.json;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JSONParser {

    private JSONObject jarray = null;

    private String server_json = null;
    private String path_json = null;

    public JSONParser() {
    }

    /**
     * Get JSON content.
     * @param pathJson
     */
    public JSONObject getJSONFromUrl(String pathJson) {
        this.setPathJson(pathJson);
        String urlFileJson = this.GetUrlJson();

        if (urlFileJson == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(urlFileJson);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                result = builder.toString();
            } else {
                Log.e("Error....", "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("JSON Client", "Error parsing data " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON Error", "Error parsing data " + e.toString());
        }

        try {
            this.jarray = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return this.jarray;
    }

    /**
     * Set address server JSON.
     */
    public void setUrlServer(String url) {
        this.server_json = url;
    }

    /**
     * Get address server JSON.
     */
    public String getUrlServer() {
        return this.server_json.toString();
    }

    /**
     * Set path Json.
     */
    public void setPathJson(String path) {
        this.path_json = path;
    }

    /**
     * Get address to file JSON.
     */
    public String GetUrlJson() {
        return getUrlServer() + '/' +  this.path_json;
    }

}
