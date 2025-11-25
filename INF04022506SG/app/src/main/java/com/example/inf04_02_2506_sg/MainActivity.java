package com.example.inf04_02_2506_sg;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText editKey, editInput;
    private TextView textEncrypted;
    private Button buttonHash, buttonSave;

    private ActivityResultLauncher<String> saveFileLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editKey = findViewById(R.id.editKey);
        editInput = findViewById(R.id.editInput);
        textEncrypted = findViewById(R.id.textEncrypted);
        buttonHash = findViewById(R.id.buttonHash);
        buttonSave = findViewById(R.id.buttonSave);

        saveFileLauncher = registerForActivityResult(
                new ActivityResultContracts.CreateDocument("text/plain"),
                this::saveTextToFile
        );

        buttonHash.setOnClickListener(v -> {
            String keyText = editKey.getText().toString().trim();
            String input = editInput.getText().toString();

            int key;
            try {
                key = Integer.parseInt(keyText);
            } catch (NumberFormatException e) {
                key = 0;
            }

            String encrypted = cesarHash(input, key);
            textEncrypted.setText(encrypted);
        });

        buttonSave.setOnClickListener(v -> {
            if (textEncrypted.getText().toString().isEmpty()) {
                Toast.makeText(this, "Brak tekstu do zapisania", Toast.LENGTH_SHORT).show();
                return;
            }

            saveFileLauncher.launch("szyfr.txt");
        });
    }

    private void saveTextToFile(Uri uri) {
        if (uri == null) return;

        try {
            ContentResolver resolver = getContentResolver();
            OutputStream output = resolver.openOutputStream(uri);

            if (output != null) {
                output.write(textEncrypted.getText().toString().getBytes());
                output.close();
                Toast.makeText(this, "Zapisano pomyślnie", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Błąd zapisu", Toast.LENGTH_SHORT).show();
        }
    }

    private String cesarHash(String input, int key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                c = Character.toLowerCase(c);
                int index = (alphabet.indexOf(c) + key) % alphabet.length();
                if (index < 0) index += alphabet.length();
                char shifted = alphabet.charAt(index);
                output.append(isUpper ? Character.toUpperCase(shifted) : shifted);
            } else {
                output.append(c);
            }
        }
        return output.toString();
    }

}
