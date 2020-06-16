package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityHomeAndroid1 extends AppCompatActivity {

    private List<String> personList;
    private static final String TAG1 = "home1";
    Observer<List<String>> personObserver;
    Observable<List<String>> personObservable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_android1);


        personList = new ArrayList<>();
        personList.add("Behrooz");
        personList.add("Emad");
        personList.add("Sajad");
        personList.add("Mehdi");
        personList.add("Samad");
        personList.add("Rasol");
        personList.add("Elahe");

        personObservable = getPersonObservable();
        personObserver = getPersonObserver();


        personObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(personObserver);

    }

    private Observable<List<String>> getPersonObservable() {
        return Observable.just(personList);
      //  return Observable.fromArray(personList);
    }


    private Observer<List<String>> getPersonObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG1, "on subscribe");
            }

            @Override
            public void onNext(List<String> strings) {

                for (String name :
                        strings) {
                    Log.d(TAG1, "name is: " +name);

                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG1, "on error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG1, "all person name are emitted");
            }
        };

    }
}
