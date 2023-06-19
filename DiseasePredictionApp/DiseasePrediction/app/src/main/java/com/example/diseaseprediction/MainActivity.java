package com.example.diseaseprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.diseaseprediction.helpers.NewsActivity;
import com.example.diseaseprediction.helpers.SubmitSymptomsActivity;
import com.example.diseaseprediction.helpers.TermsOfUse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void onSubmitSymptoms(View view) {
        Intent intent = new Intent(this, SubmitSymptomsActivity.class);
        startActivity(intent);
    }

    public void onNews(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void onTerms(View view) {
        Intent intent = new Intent(this, TermsOfUse.class);
        startActivity(intent);
    }
}