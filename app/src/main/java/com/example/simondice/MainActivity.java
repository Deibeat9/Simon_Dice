 package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define needed variables
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        String[] fourColors = {"Green", "Yellow", "Blue", "Red"};
        ArrayList<String> allColors = new ArrayList<>();
        allColors.add(fourColors[randomIndex]);
        Button start = findViewById(R.id.startBtn);
        Class<?>[] activitiesArray = {Green.class, Yellow.class, Blue.class, Red.class};

        // Colors Simon says
        for (int i = 0; i < 3; i++) {
            randomIndex = random.nextInt(4);
            allColors.add(fourColors[randomIndex]);
        }
        final int finalRandomIndex = randomIndex;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activitiesArray[finalRandomIndex]);
                intent.putStringArrayListExtra("colors", allColors);
                intent.putExtra("count", 0);
                intent.putExtra("score", 0);
                startActivity(intent);
            }
        });
    }
}