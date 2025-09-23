package com.example.inf04_01_2201_sg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(v -> {
            EditText emailEditText = findViewById(R.id.email);
            String email = emailEditText.getText().toString();
            EditText pass1EditText = findViewById(R.id.Password1);
            String pass1 = pass1EditText.getText().toString();
            EditText pass2EditText = findViewById(R.id.Password2);
            String pass2 = pass2EditText.getText().toString();
            TextView result = findViewById(R.id.result);

            if(!email.contains("@")){
                result.setText("Nieprawidłowy adres e-mail");
            } else if(!pass1.equals(pass2)){
                result.setText("Hasła się różnią");
            } else {
                result.setText("Witaj " + email);
            }
        });
    }
}