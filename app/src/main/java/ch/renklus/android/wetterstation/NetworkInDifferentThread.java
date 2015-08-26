package ch.renklus.android.wetterstation;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by lukas_000 on 26.08.2015.
 */
public class NetworkInDifferentThread extends AsyncTask<String, Void, JSONObject> {
    JSONParser jParser = new JSONParser();

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jsonObject = jParser.makeHttpRequest(params[0]);
        return jsonObject;
    }
}
