package com.example.diseaseprediction.helpers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseaseprediction.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_news);

        RequestQueue requestQueue;
        ArrayList<String> newsList1 = new ArrayList<>();
        ArrayList<String> newsList2 = new ArrayList<>();
        final RecyclerView[] recyclerView = new RecyclerView[1];

        requestQueue = Volley.newRequestQueue(this);
        String serverIP = getString(R.string.serverIP);
        String url = "http://" + serverIP + "/getnews.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("disease");
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String diseaseName = jsonObject.optString("dise_name");
                        String diseaseDesc = jsonObject.optString("dise_desc");
                        newsList1.add(diseaseName);
                        newsList2.add(diseaseDesc);
                    }
                    Log.d("Testing", String.valueOf(newsList2.size()));
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                recyclerView[0] = findViewById(R.id.recycler_view);
                recyclerView[0].setLayoutManager(new LinearLayoutManager(NewsActivity.this));

                ArrayList<String> dataList;
                dataList = new ArrayList<>();
                for(int i=0; i<newsList1.size(); i++) {
                    Data data = new Data(newsList1.get(i), newsList2.get(i));
                    dataList.add(data.getText());
                }

                CustomAdapter adapter = new CustomAdapter(dataList, NewsActivity.this);
                recyclerView[0].setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}