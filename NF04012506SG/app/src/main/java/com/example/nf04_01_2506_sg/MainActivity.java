package com.example.nf04_01_2506_sg;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekR, seekG, seekB;
    private TextView textR, textG, textB, textRgb;
    private View previewLarge;

    private int savedR = 255, savedG = 255, savedB = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekR = findViewById(R.id.seekR);
        seekG = findViewById(R.id.seekG);
        seekB = findViewById(R.id.seekB);

        textR = findViewById(R.id.textR);
        textG = findViewById(R.id.textG);
        textB = findViewById(R.id.textB);

        previewLarge = findViewById(R.id.previewLarge);

        textRgb = findViewById(R.id.textRgb);
        Button buttonSave = findViewById(R.id.buttonSave);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                updateLargePreview();
                updateRGBText();
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        seekR.setOnSeekBarChangeListener(listener);
        seekG.setOnSeekBarChangeListener(listener);
        seekB.setOnSeekBarChangeListener(listener);

        updateLargePreview();
        updateRGBText();

        buttonSave.setOnClickListener(v -> {
            savedR = seekR.getProgress();
            savedG = seekG.getProgress();
            savedB = seekB.getProgress();

            int color = Color.rgb(savedR, savedG, savedB);
            textRgb.setBackgroundColor(color);
            textRgb.setText(savedR + ", " + savedG + ", " + savedB);
        });

        textRgb.setOnClickListener(e -> {

            String hex = String.format("#%02X%02X%02X",
                    seekR.getProgress(),
                    seekG.getProgress(),
                    seekB.getProgress());

            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("color", hex);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Skopiowano " + hex, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateLargePreview() {
        int r = seekR.getProgress();
        int g = seekG.getProgress();
        int b = seekB.getProgress();

        int color = Color.rgb(r, g, b);
        previewLarge.setBackgroundColor(color);
    }

    private void updateRGBText() {
        textR.setText(String.valueOf(seekR.getProgress()));
        textG.setText(String.valueOf(seekG.getProgress()));
        textB.setText(String.valueOf(seekB.getProgress()));
    }
}
