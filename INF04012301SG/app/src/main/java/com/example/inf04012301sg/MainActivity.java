package com.example.inf04012301sg;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CheckBox smallAndGreatLetters;
    CheckBox numbers;
    CheckBox specialChars;

    Button confirmButton;
    Button generatePasswordButton;

    EditText charsEditText;
    EditText nameEditText;
    EditText surnameEditText;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        smallAndGreatLetters = findViewById(R.id.smallAndGreatLetters);
        numbers = findViewById(R.id.numbers);
        specialChars = findViewById(R.id.specialChars);
        confirmButton = findViewById(R.id.confirm);
        generatePasswordButton = findViewById(R.id.generatePassword);
        charsEditText = findViewById(R.id.charsEditText);
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        spinner = findViewById(R.id.spinner);

        generatePasswordButton.setOnClickListener(v -> {
            Toast.makeText(this, "Wygenerowane hasło: " + generatePassword(), Toast.LENGTH_LONG).show();
        });

        confirmButton.setOnClickListener(v -> {
            Toast.makeText(this, "Dane pracownika " + nameEditText.getText() + " " + surnameEditText.getText() + " " +  spinner.getSelectedItem().toString() + ", Hasło: " + generatePassword(), Toast.LENGTH_SHORT).show();
        });
    }

    public String generatePassword(){
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specials = "!@#$%^&*()_+-=";

        int length;
        try {
            length = Integer.parseInt(charsEditText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Podaj liczbę znaków!", Toast.LENGTH_SHORT).show();
            return null;
        }

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        String allowedDigits = "";

        if (smallAndGreatLetters.isChecked())
            allowedDigits += letters;
        if (numbers.isChecked())
            allowedDigits += digits;
        if (specialChars.isChecked())
            allowedDigits += specials;

        for(int i = 0; i < length; i++){
            int index = random.nextInt(allowedDigits.length());
            password.append(allowedDigits.charAt(index));
        }

        return password.toString();
    }
}
