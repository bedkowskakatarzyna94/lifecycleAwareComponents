package com.example.android.lifecycles.step2;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Chronometer;

import com.example.android.codelabs.lifecycle.R;

public class ChronoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChronometerViewModel chronometerViewModel
                = new ViewModelProvider(this).get(ChronometerViewModel.class);

        Chronometer chronometer = findViewById(R.id.chronometer);

        if (chronometerViewModel.getStartTime() == null) {
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setStartTime(startTime);
            chronometer.setBase(startTime);
        } else {
            chronometer.setBase(chronometerViewModel.getStartTime());
        }
        chronometer.start();
    }
}
