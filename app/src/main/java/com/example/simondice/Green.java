package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Green extends AppCompatActivity {
    private String newTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);

        // Fetch buttons and text areas
        TextView title = findViewById(R.id.titleText);
        TextView scoreText = findViewById(R.id.scoreText);
        Button green = findViewById(R.id.greenBtn);
        Button yellow = findViewById(R.id.yellowBtn);
        Button blue = findViewById(R.id.blueBtn);
        Button red = findViewById(R.id.redBtn);
        Button restart = findViewById(R.id.restartBtn);
        Class<?>[] activitiesArray = {Green.class, Yellow.class, Blue.class, Red.class};

        // Get count, index, and color from intent
        int score = getIntent().getIntExtra("score", -2);
        int count = getIntent().getIntExtra("count", -3);
        ArrayList<String> colors = getIntent().getStringArrayListExtra("colors");

        // Update displayed score
        scoreText.setText(String.valueOf(score));

        // Update title text
        if (score != count) {
            String temp = "Color: " + (count + 1);
            title.setText(temp);
        } else {
            String temp = "Simon says " + colors.get(count);
            title.setText(temp);
        }


        // Play lost or won the game
        void gameOver() {
            colors.set(count, newTitle);
            title.setText(newTitle);
            restart.setVisibility(View.VISIBLE);
            red.setText(newTitle);
            yellow.setText(newTitle);
            green.setText(newTitle);
        }

        // Update game based on user's choice
        void onCorrect(String answer, int classNum) {
            if (colors.get(count).equals(answer)) {
                Intent intent = new Intent(Green.this, activitiesArray[classNum]);
                if ((count + 1) == colors.size()) {
                    gameOver("YOU WIN!");
                } else {
                    if (count == score) {
                        count = -1;
                        score++;
                    }
                    count++;
                    intent.putStringArrayListExtra("colors", colors);
                    intent.putExtra("count", count);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
            } else if (restart.getVisibility() != View.INVISIBLE) {
                gameOver("gameOver");
            }
        }

        // On click listeners for each button
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Green", 0);
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Yellow", 1);
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Blue", 2);
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrect("Red", 3);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Green.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
