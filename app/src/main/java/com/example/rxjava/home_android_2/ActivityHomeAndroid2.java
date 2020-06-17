package com.example.rxjava.home_android_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjava.R;
import com.example.rxjava.fake_api.DataModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//create - just - range  - repeat
// we used just before

public class ActivityHomeAndroid2 extends AppCompatActivity {

    private static final String TAG2 = "behrooz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_android2);

        final DataModel dataModel= new DataModel("","بهروز پهلوان ","");

        Observable<DataModel> singleObservable = null;

        singleObservable=Observable.create(new ObservableOnSubscribe<DataModel>() {
            @Override
            public void subscribe(ObservableEmitter<DataModel> emitter) throws Exception {

                if (!emitter.isDisposed()){
                    emitter.onNext(dataModel);
                    emitter.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());  //تا اینجا یعنی یک نمونه از از observable را ساختیم فقط کافیه با یک observer سابسکرایب بشه

        singleObservable.subscribe(new Observer<DataModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DataModel dataModel) {
                    Log.d(TAG2,dataModel.getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
