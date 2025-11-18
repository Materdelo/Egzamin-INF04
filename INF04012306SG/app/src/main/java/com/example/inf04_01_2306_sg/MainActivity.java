package com.example.inf04_01_2306_sg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioPocztowka, radioList, radioPaczka;
    private Button btnCheckPrice, btnSubmit;
    private ImageView imageResult;
    private TextView labelCena;
    private EditText editKod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioPocztowka = findViewById(R.id.radioPocztowka);
        radioList = findViewById(R.id.radioList);
        radioPaczka = findViewById(R.id.radioPaczka);

        btnCheckPrice = findViewById(R.id.btnCheckPrice);
        btnSubmit = findViewById(R.id.btnSubmit);

        imageResult = findViewById(R.id.imageResult);
        labelCena = findViewById(R.id.labelCena);
        editKod = findViewById(R.id.editKod);

        setupListeners();
    }

    private void setupListeners() {

        btnCheckPrice.setOnClickListener(v -> {

            if (radioPocztowka.isChecked()) {
                imageResult.setImageResource(R.drawable.pocztowka);
                labelCena.setText("Cena: 1 zł");
            } else if (radioList.isChecked()) {
                imageResult.setImageResource(R.drawable.list);
                labelCena.setText("Cena: 1,5 zł");
            } else if (radioPaczka.isChecked()) {
                imageResult.setImageResource(R.drawable.paczka);
                labelCena.setText("Cena: 10 zł");
            }
        });

        btnSubmit.setOnClickListener(v -> validatePostCode());
    }

    private void validatePostCode() {
        String kod = editKod.getText().toString().trim();

        if (kod.length() != 5) {
            Toast.makeText(this, "Nieprawidłowa liczba cyfr w kodzie pocztowym", Toast.LENGTH_SHORT).show();
            return;
        }

        for (char c : kod.toCharArray()) {
            if (!Character.isDigit(c)) {
                Toast.makeText(this, "Kod pocztowy powinien się składać z samych cyfr", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Toast.makeText(this, "Dane przesyłki zostały wprowadzone", Toast.LENGTH_LONG).show();
    }
}
