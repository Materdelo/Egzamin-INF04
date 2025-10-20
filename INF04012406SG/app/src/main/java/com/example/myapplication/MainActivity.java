package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView dice1, dice2, dice3, dice4, dice5;
    List <Integer> image;
    List<ImageView> dices;
    Button rollDiceButton, resetResult;
    TextView result, resultGeneralView;
    int resultGeneral = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        rollDiceButton = findViewById(R.id.rollDice);
        resetResult = findViewById(R.id.reset);

        result = findViewById(R.id.result);
        resultGeneralView = findViewById(R.id.resultGeneral);

        image = List.of(R.drawable.k1, R.drawable.k2, R.drawable.k3, R.drawable.k4, R.drawable.k5, R.drawable.k6);
        dices = List.of(dice1, dice2, dice3, dice4, dice5);

        rollDiceButton.setOnClickListener(e -> {
            int [] rolls = rollDice(dices);
            int resultSum = calculateSum(rolls);
            result.setText("Wynik tego losowania: " + resultSum);
            resultGeneral += resultSum;
            resultGeneralView.setText("wynik gry: " + resultGeneral);
        });

        resetResult.setOnClickListener(e -> {
            for(int i = 0; i <= 4; i++){
                dices.get(i).setImageResource(R.drawable.question);
                resultGeneral = 0;
                result.setText("Wynik tego losowania: 0");
                resultGeneralView.setText("wynik gry: 0");
            }
        });
    }

    public int[] rollDice(List<ImageView> dices) {
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            int roll = new Random().nextInt(6) + 1;
            dices.get(i).setImageResource(image.get(roll - 1));
            dice[i] = roll;
        }
        return dice;
    }

    public int calculateSum(int[] rolls) {
        int[] counts = new int[7];
        for (int roll: rolls){
            counts[roll]++;
        }

        int sum = 0;
        for (int i = 1; i <= 6; i++) {
            if(counts[i] >= 2){
                sum += i * counts[i];
            }
        }
        return sum;
    }
}