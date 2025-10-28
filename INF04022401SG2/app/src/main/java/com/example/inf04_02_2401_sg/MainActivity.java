package com.example.inf04_02_2401_sg;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, goalEditText, timeEditText;
    TextView ageTitle, result;
    ListView listView;
    SeekBar seekBar;
    Button okButton, clearButton;
    String selectedSpace = "";
    int age = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        goalEditText = findViewById(R.id.goal);
        timeEditText = findViewById(R.id.time);
        result = findViewById(R.id.result);
        listView = findViewById(R.id.listView);
        ageTitle = findViewById(R.id.ageTitle);

        seekBar = findViewById(R.id.seekBar);
        okButton = findViewById(R.id.okButton);
        clearButton = findViewById(R.id.clearButton);

        ArrayList<String> spacies = new ArrayList<>(List.of(
                "Pies",
                "Kot",
                "Świnka morska"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spacies);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedSpace = spacies.get(position);
            switch (selectedSpace){
                case "Pies": seekBar.setMax(18); break;
                case "Kot": seekBar.setMax(20); break;
                case "Świnka morska": seekBar.setMax(9); break;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age = progress;
                ageTitle.setText("Ile ma lat? " + age);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        okButton.setOnClickListener(e -> {
            String name = nameEditText.getText().toString();
            String goal = goalEditText.getText().toString();
            String time = timeEditText.getText().toString();

            if (name.isBlank() || goal.isBlank() || selectedSpace.isBlank() || time.isBlank()) {
                Toast.makeText(this, "Wypełnij wszystkie dane", Toast.LENGTH_SHORT).show();
            } else {
                result.setText(name + ", " + selectedSpace + ", " + age + ", " + goal + ", " + time);
            }
        });

        clearButton.setOnClickListener(e -> {
            nameEditText.setText("");
            goalEditText.setText("");
            timeEditText.setText("16:00");
            result.setText("");
            selectedSpace = "";
            seekBar.setProgress(0);
            seekBar.setMax(20);
            ageTitle.setText("Ile ma lat? 0");
            listView.clearChoices();
            ((ArrayAdapter<?>) listView.getAdapter()).notifyDataSetChanged();
            Toast.makeText(this, "Formularz wyczyszczony", Toast.LENGTH_SHORT).show();
        });
    }
}