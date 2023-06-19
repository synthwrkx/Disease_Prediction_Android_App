package com.example.diseaseprediction.helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseaseprediction.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TreatmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_treatment);

        // get the intent that started this activity
        Intent intent = getIntent();
        // retrieve the string value from the intent
        String treatmentDisease = intent.getStringExtra("ExtraMessage");

        String serverIP = getString(R.string.serverIP);
        String url = "http://" + serverIP + "/gettreatment.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("treatment");

                    JSONObject treatmentObj = jsonArray.getJSONObject(0);
                    String diseaseName = treatmentObj.getString("disease");
                    String treatDetails = treatmentObj.getString("treat");
                    // Process the disease name and treatment details

                    TextView diseaseNameView = findViewById(R.id.treatdiseasename);
                    TextView treatmentDetailsView = findViewById(R.id.treatmentdetails);

                    diseaseNameView.setText(diseaseName);
                    treatmentDetailsView.setText(treatDetails);
                    // Parse other properties of the JSON object as needed
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
                // handle error
            }})

        //Anonymous inner class to override te public getParams() method
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String valueToPass = treatmentDisease;
                params.put("disease", valueToPass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}