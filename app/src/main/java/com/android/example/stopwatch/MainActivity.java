package com.android.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.example.stopwatch.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int ms = 0;
    private boolean running;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ms = savedInstanceState.getInt("ms");
            running = savedInstanceState.getBoolean("running");
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("ms", ms);
        savedInstanceState.putBoolean("running", running);
    }


    //Start the stopwatch running when the Start button is clicked.


    public void onClickStart(View view) {
        running = true;

    }
    public void onClickStop(View view){
        running = false;
    }
    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset (View view) {
        running = false;
        ms = 0;
    }
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int msc = ms%99;
                int secs = ms/60;
                int minutes = ms/3600 ;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", minutes, secs, msc);
                binding.tvTime.setText(time);
                if (running) {
                    ms ++;


                }
                handler.postDelayed(this, 1);
            }

        });



    }

}

