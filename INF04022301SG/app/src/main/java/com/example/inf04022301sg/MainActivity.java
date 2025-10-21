package com.example.inf04022301sg;

import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText newElement;
    Button addElement;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        newElement = findViewById(R.id.newElement);
        addElement = findViewById(R.id.Add);
        listView = findViewById(R.id.listView);

        ArrayList<String> notes = new ArrayList<>(List.of(
                "Zakupy: chleb, masło, ser",
                "Do zrobienia: obiad, umyć podłogi",
                "weekend: kino, spacer z psem"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        addElement.setOnClickListener(e -> {
            String element = newElement.getText().toString();
            Objects.requireNonNull(element);

            if(element.isBlank() || element.isEmpty()){
                Toast.makeText(this, "Brak elemneu" ,Toast.LENGTH_SHORT).show();
            } else {
                notes.add(element);
                arrayAdapter.notifyDataSetChanged();
                newElement.setText("");
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            EditText input = new EditText(this);
            input.setText(notes.get(position));
            new AlertDialog.Builder(this)
                    .setTitle("Edytuj notatkę")
                    .setView(input)
                    .setPositiveButton("Zapisz", (dialog, which) -> {
                        notes.set(position, input.getText().toString());
                        arrayAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("Anuluj", null)
                    .show();
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            String clickedItem = notes.get(position);
            Toast.makeText(this, "Usunięto: " + clickedItem, Toast.LENGTH_SHORT).show();
            notes.remove(position);
            arrayAdapter.notifyDataSetChanged();
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}