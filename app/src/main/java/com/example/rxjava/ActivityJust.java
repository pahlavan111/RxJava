package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityJust extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String TAG = "Behrooz";
    TextView textView;
    String res="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just);
        textView= findViewById(R.id.textView_just);

        Observable<String> animalObservable = getAnimalObservable();
        animalObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getAnimalObserver());

    }

    private Observable<String> getAnimalObservable() {
        return Observable.just("Eagle", "Bee", "Lion", "Wolf", "Dog");
    }

    private Observer<String> getAnimalObserver() {

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                res=res+"onSubscribe\n";
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
                res=res+s+"\n";
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, "OnError: " + e.toString());
                res=res+"OnError: " + e.toString()+"\n";

            }

            @Override
            public void onComplete() {

                Log.d(TAG, "All item are emmited");
                res=res+"All item are emmited";
                textView.setText(res);
                res="";

                if (compositeDisposable.size() != 0) {
                    compositeDisposable.clear();
                }
            }
        };
    }
}
