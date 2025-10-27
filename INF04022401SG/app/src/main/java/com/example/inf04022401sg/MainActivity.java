package com.example.inf04022401sg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText numberEditText, nameEditText, surnameEditText;
    RadioButton blue, green, beer;
    Button ok;
    ImageView zdjecie, odcisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        numberEditText = findViewById(R.id.numberEditText);
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);

        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        beer = findViewById(R.id.beer);

        ok = findViewById(R.id.okButton);
        zdjecie = findViewById(R.id.zdjecie);
        odcisk = findViewById(R.id.odcisk);

        numberEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String numer = numberEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(numer)) {
                    zdjecie.setImageResource(getResources().getIdentifier("z"+numer+"zdjecie", "drawable", getPackageName()));
                    odcisk.setImageResource(getResources().getIdentifier("z"+numer+"odcisk", "drawable", getPackageName()));
                }
            }
        });

        ok.setOnClickListener(e -> {
            String name = nameEditText.getText().toString();
            String surname = surnameEditText.getText().toString();

            RadioGroup colorGroup = findViewById(R.id.colorGroup);

            int selectedId = colorGroup.getCheckedRadioButtonId();

            if(!name.isBlank() && !surname.isBlank()){
                RadioButton selectedRadioButton = findViewById(selectedId);
                String selectedText = selectedRadioButton.getText().toString();
                Toast.makeText(this, name+", "+surname+" kolor oczu "+selectedText, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
