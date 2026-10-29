package com.example.planertreningowy;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int liczbaCalkowitychSerii = 0;
    private void losujCwiczenie(Random rand, ImageView Cwiczenie, TextView Nazwa, int[] poprzedniaLiczba){
        int liczba;

        do{
            liczba = rand.nextInt(6)+1;
        }while (liczba == poprzedniaLiczba[0]);

        poprzedniaLiczba[0] = liczba;

        int[] obrazki = {
                R.drawable.cwiczenie1,
                R.drawable.cwiczenie2,
                R.drawable.cwiczenie3,
                R.drawable.cwiczenie4,
                R.drawable.cwiczenie5,
                R.drawable.cwiczenie6
        };

        String[] nazwy = {
                "Przysiad",
                "Pompki",
                "Plank",
                "Wykroki",
                "Burpees",
                "Podciąganie"
        };

        Cwiczenie.setImageResource(obrazki[liczba-1]);
        Nazwa.setText(nazwy[liczba-1]);
    }

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
        Button Losuj = findViewById(R.id.btnLosuj);
        Random rand = new Random();
        int[] poprzedniaLiczba = {0};
        ImageView Cwiczenie = findViewById(R.id.imgCwiczenie);
        TextView Nazwa = findViewById(R.id.txtNazwa);
        TextView Seria = findViewById(R.id.txtSeria);

        Button plus = findViewById(R.id.btnPlus);
        Button minus = findViewById(R.id.btnMinus);

        Button next = findViewById(R.id.btnNext);
        Button reset = findViewById(R.id.btnReset);

        plus.setEnabled(false);
        minus.setEnabled(false);
        next.setEnabled(false);

        TextView calkowite = findViewById(R.id.txtCalkowite);

        Losuj.setOnClickListener(v -> {
            plus.setEnabled(true);
            minus.setEnabled(true);
            next.setEnabled(true);
            losujCwiczenie(rand, Cwiczenie, Nazwa, poprzedniaLiczba);
            Seria.setText("0");
        });

        plus.setOnClickListener(v -> {
            int seria = Integer.parseInt(Seria.getText().toString());
            seria++;
            Seria.setText(String.valueOf(seria));
        });

        minus.setOnClickListener(v -> {
            int seria = Integer.parseInt(Seria.getText().toString());
            if(seria > 0){
                seria--;

            }
            Seria.setText(String.valueOf(seria));
        });

        next.setOnClickListener(v -> {
            int seria = Integer.parseInt(Seria.getText().toString());
            liczbaCalkowitychSerii+=seria;
            calkowite.setText("Łączna liczba serii: "+ String.valueOf(liczbaCalkowitychSerii));
            losujCwiczenie(rand, Cwiczenie, Nazwa, poprzedniaLiczba);
            Seria.setText("0");
        });

        reset.setOnClickListener(v -> {
            liczbaCalkowitychSerii = 0;
            poprzedniaLiczba[0] = 0;
            Seria.setText("0");
            calkowite.setText("Łączna liczba serii: 0");
            Cwiczenie.setImageResource(R.drawable.cwiczenie0);
            Nazwa.setText("Losuj ćwiczenie");

            plus.setEnabled(false);
            minus.setEnabled(false);
            next.setEnabled(false);
        });
    }
}