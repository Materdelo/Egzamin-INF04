package com.example.inf04_01_2206_sg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.delete), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button likeButton = findViewById(R.id.like);
        Button deleteButton = findViewById(R.id.delete);

        TextView liked = findViewById(R.id.liked);
        liked.setText(counter + " polubień");

        likeButton.setOnClickListener(v -> {
            counter++;
            liked.setText(counter + " polubień");
        });

        deleteButton.setOnClickListener(v -> {
            if (counter > 0) {
                counter--;
                liked.setText(counter + " polubień");
            }
        });
    }
}