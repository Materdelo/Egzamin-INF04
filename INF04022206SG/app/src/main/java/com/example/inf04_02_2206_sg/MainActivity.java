package com.example.inf04_02_2206_sg;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView courseCount;
    ListView listView;
    EditText nameTextView, courseTextNumber, newCourseText;
    Button saveButton, addCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> courses = new ArrayList<>(List.of(
                "1. Progamowanie w C#",
                "2. Angular dla początkujących",
                "3. Kurs Django"
        ));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);

        courseCount = findViewById(R.id.courseCount);
        courseCount.setText("Liczba kursów: " + courses.size());

        listView = findViewById(R.id.listView);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            courses.remove(position);

            for (int i = 0; i < courses.size(); i++) {
                courses.set(i, (i + 1) + ". " + courses.get(i).substring(courses.get(i).indexOf(" ") + 1));
            }

            arrayAdapter.notifyDataSetChanged();
            courseCount.setText("Liczba kursów: " + courses.size());
        });

        newCourseText = findViewById(R.id.newCourseText);
        addCourseButton = findViewById(R.id.addCourseButton);
        addCourseButton.setOnClickListener(v -> {
            String newCourse = newCourseText.getText().toString().trim();

            if (newCourse.isEmpty()) {
                Toast.makeText(this, "Wpisz nazwę kursu!", Toast.LENGTH_SHORT).show();
                return;
            }

            courses.add((courses.size() + 1) + ". " + newCourse);
            arrayAdapter.notifyDataSetChanged();
            courseCount.setText("Liczba kursów: " + courses.size());
            newCourseText.setText("");
        });

        nameTextView = findViewById(R.id.nameTextText);
        courseTextNumber = findViewById(R.id.courseTextNumber);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(e -> {
            String name = nameTextView.getText().toString();
            String numberText = courseTextNumber.getText().toString();

            if (numberText.isEmpty()) {
                Toast.makeText(this, "Podaj numer kursu!", Toast.LENGTH_LONG).show();
                return;
            }

            int index = Integer.parseInt(numberText) - 1;

            if (index < 0 || index >= courses.size()) {
                Toast.makeText(this, "Nie ma takiego kursu!", Toast.LENGTH_LONG).show();
                return;
            }

            String selectedCourse = courses.get(index);

            Toast.makeText(this, name + " wybrał " + selectedCourse, Toast.LENGTH_LONG).show();
        });
    }
}
