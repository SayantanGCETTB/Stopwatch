package com.sayantan.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private  int seconds;
    private boolean isRunning;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("isRunning");
            savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    public  void onStart(View view){
        isRunning=true;
    }
    public  void onStop(View view){
        isRunning=false;
    }
    public  void onReset(View view){
        isRunning=false;
        seconds=0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning=isRunning;
        isRunning=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning)
            isRunning=true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("isRunning",isRunning);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void runTimer() {
        TextView timeview=findViewById(R.id.timer);
        Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,secs);
                timeview.setText(time);
                if(isRunning)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });
    }
}