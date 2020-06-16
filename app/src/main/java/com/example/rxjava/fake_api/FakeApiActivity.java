package com.example.rxjava.fake_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.rxjava.R;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FakeApiActivity extends AppCompatActivity {

    FakeApi fakeApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_api);
        final RecyclerView rv_fake_api=findViewById(R.id.rv_fake_api);

        fakeApi=new FakeApi();
        fakeApi.listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DataModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataModel> dataModels) {
                        rv_fake_api.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_fake_api.setAdapter(new DataModelAdapter(dataModels,FakeApiActivity.this));
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
