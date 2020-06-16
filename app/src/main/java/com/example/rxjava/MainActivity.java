package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String link = "https://finepointmobile.com/data/products.json";
    public static String content = "";

    Button btn_asynk, btn_just,btn_operator;
    Button btn_ha_1, btn_ha_2,btn_ha_3,btn_ha_4,btn_ha_5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_asynk = findViewById(R.id.btn_Async);
        btn_just = findViewById(R.id.btn_just);
        btn_operator = findViewById(R.id.btn_operator);
        btn_ha_1 = findViewById(R.id.btn_ha_1);
        btn_ha_2 = findViewById(R.id.btn_ha_2);
        btn_ha_3 = findViewById(R.id.btn_ha_3);
        btn_ha_4 = findViewById(R.id.btn_ha_4);
        btn_ha_5 = findViewById(R.id.btn_ha_5);


        btn_asynk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTaskGetData(link).execute();

                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (!content.equals("")) {
                                    Toast.makeText(MainActivity.this, content + "", Toast.LENGTH_SHORT).show();
                                    timer.cancel();
                                    // finish();
                                }
                            }
                        });
                    }
                }, 10, 50);
            }
        });


        btn_just.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            startActivity(new Intent(MainActivity.this,ActivityJust.class));


            }
        });

        btn_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,OperatorActivity.class));


            }
        });

        btn_ha_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,ActivityHomeAndroid1.class));

            }
        });

    }

    //just




    String getData(String link) {

        content = "";

        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content = builder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return content;
    }


}
