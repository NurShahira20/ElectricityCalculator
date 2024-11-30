package com.example.electricitycalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        ImageButton githubButton = findViewById(R.id.githubButton);
        githubButton.setOnClickListener(v -> {

            String githubUrl = "https://github.com/NurShahira20/ElectricityCalculator";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
            startActivity(browserIntent);
        });
    }
}
