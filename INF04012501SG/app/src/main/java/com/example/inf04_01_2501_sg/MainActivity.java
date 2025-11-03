package com.example.inf04_01_2501_sg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
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

    List<ImageItem> gallery = new ArrayList<>(List.of(
            new ImageItem(0, "Mak", "obraz1", 1, 35),
            new ImageItem(1, "Bukiet", "obraz2", 1, 43),
            new ImageItem(2, "Dalmatyńczyk", "obraz3", 2, 2),
            new ImageItem(3, "Świnka morska", "obraz4", 2, 53),
            new ImageItem(4, "Rotwailer", "obraz5", 2, 43),
            new ImageItem(5, "Audi", "obraz6", 3, 11),
            new ImageItem(6, "Kotki", "obraz7", 2, 22),
            new ImageItem(7, "Róża", "obraz8", 1, 33),
            new ImageItem(8, "Świnka morska", "obraz9", 2, 123),
            new ImageItem(9, "Foksterier", "obraz10", 2, 22),
            new ImageItem(10, "Szczeniak", "obraz11", 2, 12),
            new ImageItem(11, "Garbus", "obraz12", 3, 321)
    ));

    private LinearLayout container;
    private Switch switch1, switch2, switch3;
    private LayoutInflater inflater;

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

        container = findViewById(R.id.container);
        inflater = LayoutInflater.from(this);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);

        renderGallery();

        View.OnClickListener filterListener = v -> renderGallery();
        switch1.setOnClickListener(filterListener);
        switch2.setOnClickListener(filterListener);
        switch3.setOnClickListener(filterListener);
    }

    private void renderGallery() {
        container.removeAllViews();

        for (ImageItem item : gallery) {
            boolean show = false;

            if (item.getCategory() == 1 && switch1.isChecked()) show = true;
            if (item.getCategory() == 2 && switch2.isChecked()) show = true;
            if (item.getCategory() == 3 && switch3.isChecked()) show = true;

            if (!show) continue;

            View view = inflater.inflate(R.layout.gallery_item, container, false);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textDownloads = view.findViewById(R.id.textDownloads);
            Button buttonDownload = view.findViewById(R.id.buttonDownload);

            textDownloads.setText("Pobrań: " + item.getDownloads());

            int imageResId = getResources().getIdentifier(item.getFilename(), "drawable", getPackageName());
            if (imageResId != 0) imageView.setImageResource(imageResId);

            buttonDownload.setOnClickListener(v -> {
                item.increaseDownload();
                textDownloads.setText("Pobrań: " + item.getDownloads());
            });

            container.addView(view);
        }
    }
}
