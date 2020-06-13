package com.example.rxjava;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskGetData extends AsyncTask {

    String link = "";

    public AsyncTaskGetData(String link) {

        this.link = link;

    }

    @Override
    protected Object doInBackground(Object[] params) {


        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            MainActivity.content = builder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}

