package whatsnear.hackathon.com.whatsnear;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {

    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;
    ProgressDialog progress;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    List<String> products = new ArrayList<String>();

    HashMap<String, JSONArray> individualData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        individualData = new HashMap<>();

        String lat = "12.9716";
        String longi = "77.5946";
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.show();
//        new DownloadFilesTask().execute(
        //              "https://us-central1-endless-datum-171706.cloudfunctions.net/TwitterFuncHttp?location="+lat+"%2C"+longi);
        new DownloadFilesTask().execute(
                "https://us-central1-endless-datum-171706.cloudfunctions.net/TwitterFuncHttp?location=18.559082%2C73.911363");

    }

    public void updateUI() {
        if (isDestroyed()) {
            return;
        }
        JSONObject object = null;
        JSONArray objectDetails = null;
        String key;
        int i = 0;
        try {
            object = new JSONObject(data);
            Iterator<String> keys = object.keys();
            while (keys.hasNext()) {
                key = keys.next();
                products.add(key);
                objectDetails = (JSONArray) object.get(key);
                individualData.put(key, objectDetails);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("DATA", individualData.get(products.get(position)).toString());
                startActivity(intent);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity2.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    static String data = "";

    private class DownloadFilesTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urls[0])
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return response.body().string();


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            data = result;
            updateUI();
            if (progress != null) {
                progress.dismiss();
            }
        }
    }
}