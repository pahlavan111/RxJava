package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {

    private static final String link = "https://finepointmobile.com/data/products.json";
    public static String content ="";

    Button btn_asynk,btn_rxjava;

    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_asynk=findViewById(R.id.btn_Async);
        btn_rxjava = findViewById(R.id.btn_rxjava);

        btn_asynk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });






    }





}
