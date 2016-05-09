package pl.drupalcampwroclaw.drupalcamp.others;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;


public class SharedPreference {

    private String SHARED_NAME = null;

    public SharedPreference(String shared_name) {
        super();

        this.SHARED_NAME = shared_name;
    }

    /**
     * Save JSON object.
     */
    public void putJson(Context context, JSONObject jsonObject) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString("JSONString", jsonObject.toString());

        editor.commit();
    }

    /**
     * Get JSON object.
     */
    public JSONObject getJson(Context context) {
        SharedPreferences settings = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        String json = settings.getString("JSONString", null);

        if (json == null) {
            return null;
        }

        try {
            JSONObject result = new JSONObject(json);
            return result;
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
