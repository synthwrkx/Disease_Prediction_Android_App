package com.example.diseaseprediction.helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.Collections;

public class SubmitSymptomsActivity extends AppCompatActivity {
    int count = 0;
    String treatmentDisease;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_submit_symptoms);

        Button treatmentButton = findViewById(R.id.showtreat);
        treatmentButton.setClickable(false);
        treatmentButton.setBackgroundColor(Color.parseColor("#999999"));

        RequestQueue requestQueue;
        ArrayList<String> dataList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/getdata.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("symptom");
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String symptomName = jsonObject.optString("symp_name");
                        dataList.add(symptomName);
                    }
                    Collections.sort(dataList);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

        Button addSymp = findViewById(R.id.addSymptom);
        LinearLayout linearLayout = findViewById(R.id.symptomListLayout);
        final ScrollView scrollView = findViewById(R.id.scrollView);

        addSymp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = findViewById(R.id.symptomListLayout);
                TextView textView = new TextView(getApplicationContext());
                linearLayout.addView(textView);
                textView.setHint("Add symptom");
                textView.setHintTextColor(Color.parseColor("#FFFFFF"));
                textView.setTextSize(20);

                Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow); // replace with your drawable resource ID
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null); // set the drawableEnd attribute

                GradientDrawable border = new GradientDrawable();
                border.setStroke(4, Color.WHITE); // set the stroke width and color
                border.setCornerRadius(20); // set the corner radius
                textView.setBackground(border); // set the TextView's background to the drawable object

                textView.setId(count);
                count++;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
                params.setMargins(30, 0, 30, 30);
                textView.setLayoutParams(params);
                textView.setPadding(20,36,0,0);
                textView.setTextColor(Color.parseColor("#FFFFFF"));

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Initialize dialog
                        dialog = new Dialog(SubmitSymptomsActivity.this);

                        // set custom dialog
                        dialog.setContentView(R.layout.dialog_searchable_spinner);

                        // set custom height and width
                        dialog.getWindow().setLayout(750,900);

                        // set transparent background
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // show dialog
                        dialog.show();

                        // Initialize and assign variable
                        EditText editText = dialog.findViewById(R.id.edit_text);
                        ListView listView = dialog.findViewById(R.id.list_view);

                        // Initialize array adapter
                        ArrayAdapter<String> adapter=new ArrayAdapter<>(SubmitSymptomsActivity.this, android.R.layout.simple_list_item_1,dataList);

                        // set adapter
                        listView.setAdapter(adapter);
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                adapter.getFilter().filter(s);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // when item selected from list
                                // set selected item on textView
                                textView.setText(adapter.getItem(position));

                                // Dismiss dialog
                                dialog.dismiss();
                            }
                        });
                    }
                });

               scrollView.post(new Runnable() {
                   @Override
                   public void run() {
                       scrollView.smoothScrollTo(0, scrollView.getBottom());
                   }
               });
            }
        });

        Button submitSymp = findViewById(R.id.submit);
        submitSymp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> symptomList = new ArrayList<>();
                for(int i=0; i<count; i++) {
                    TextView textView = findViewById(i);
                    String selectedValue = textView.getText().toString();
                    symptomList.add(selectedValue);
                }

                String listString = "";

                for (String s : symptomList)
                {
                    listString += s + " ";
                }

                Log.d("ArrayList", listString);

                //JSONObject jsonObjectSymp = new JSONObject();
                JSONArray jsonArraySymp = new JSONArray();
                for (String symp : symptomList) {
                    JSONObject jsonObjectSymp = new JSONObject();
                    try {
                        jsonObjectSymp.put("symp_name", symp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArraySymp.put(jsonObjectSymp);
                }

                JSONObject finalJson = new JSONObject();
                try {
                    finalJson.put("symptom", jsonArraySymp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = "http://10.0.2.2:5000/";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, finalJson, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Parse the JSON response here
                                try {
                                    String final_prediction = response.getString("final_prediction");
                                    TextView textView = (TextView) findViewById(R.id.predictOutput);

                                    textView.setText(final_prediction);
                                    adjustFontSizeToFit(textView);
                                    textView.setText(final_prediction);
                                    treatmentDisease = final_prediction;
                                    treatmentButton.setClickable(true);
                                    treatmentButton.setBackgroundColor(Color.parseColor("#4E0064"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error here
                            }
                        });
                // Add the request to the RequestQueue.
                requestQueue.add(jsonObjectRequest);
            }
        });

        Button removeSymp = findViewById(R.id.deleteSymptom);
        removeSymp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastIndex = linearLayout.getChildCount() - 1;
                if (lastIndex >= 0) {
                    linearLayout.removeViewAt(lastIndex);
                    count--;
                }
            }
        });
    }

    void adjustFontSizeToFit(TextView textView) {
        int targetWidth = textView.getWidth();
        int targetHeight = textView.getHeight();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        float textSize = paint.getTextSize();
        float textWidth = paint.measureText(text);

        while (textWidth > targetWidth || paint.getFontSpacing() > targetHeight) {
            textSize--;
            paint.setTextSize(textSize);
            textWidth = paint.measureText(text);
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void onTreatment(View view) {
        Intent intent = new Intent(this, TreatmentActivity.class);
        intent.putExtra("ExtraMessage", treatmentDisease);
        startActivity(intent);
    }
}