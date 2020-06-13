package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private static final String link = "https://finepointmobile.com/data/products.json";
    public static String content ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // final CompositeDisposable compositeDisposable=new CompositeDisposable();

        final AsyncTask execute = new AsyncTaskGetData(link).execute();

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!content.equals("")){
                            Toast.makeText(MainActivity.this, content+"", Toast.LENGTH_SHORT).show();
                            timer.cancel();
                           // finish();
                        }
                    }
                });
            }
        },10,50);

    }





}
