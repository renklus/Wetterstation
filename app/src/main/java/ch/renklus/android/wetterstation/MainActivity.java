package ch.renklus.android.wetterstation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListView
        Refresh();

        //Refresh Button
        final Button refresh = (Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Refresh();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Refresh() {
        ListView listView = (ListView) findViewById(R.id.listView);
        String[] values = GetValues();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }

    /*int i = 0;
    private String[] GetValues()
    {
        String[] values =  new String[]
                {
                        "",
                        "Android List View",
                        "Simple List View in Android",
                        "Create List View Android",
                        "Android Example",
                };
        values[0] = Integer.toString(++i);
        return values;
    }*/

    private String[] GetValues()
    {
        //sensorList = new ArrayList<>();
        //new LoadAllProducts().execute();
        //ListView lv = getListView();


        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url);

        // Check your log cat for JSON reponse
        Log.d("All Sensor Values: ", json.toString());


        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                double humidity = json.getDouble("humidity");
                double light = json.getDouble("light");
                double pressure = json.getDouble("pressure");
                double rain = json.getDouble("rain");
                double rainamount = json.getDouble("rainamount");
                double temperature = json.getDouble("temperature");
                double winddirection = json.getDouble("winddirection");
                double windspeed = json.getDouble("windspeed");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }





        return  null;
    }

    private ProgressDialog pDialog;

    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> sensorList;
    private static String url="http://ravoser.homeip.net:8282/db-view/app/read.php";

    private static final String TAG_SUCCESS = "success";
    //private static final String TAG_PRODUCTS = "products";
    //private static final String TAG_PID = "pid";
    //private static final String TAG_NAME = "name";

    JSONArray values = null;
}
