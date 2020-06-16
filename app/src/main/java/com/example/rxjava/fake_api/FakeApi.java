package com.example.rxjava.fake_api;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FakeApi {

    private List<DataModel> dataModelList;
    Observable<List<DataModel>> listObservable;

    public FakeApi() {
        this.dataModelList = api_post();
        listObservable = Observable.just(dataModelList);
    }

    private List<DataModel> api_post(){
        dataModelList = new ArrayList<>();
        dataModelList.add(new DataModel("1","دوره پیشرفته آموزش Rxjava MVVM","https://homeandroid.ir/wp-content/uploads/2019/04/kotlin-android-cover_home_page.jpg"));
        dataModelList.add(new DataModel("2","دوره آموزش MVVM Coroutines Kotlin","https://homeandroid.ir/wp-content/uploads/2020/06/Rxjava-MVVM-Shopping-Small.jpg"));
        dataModelList.add(new DataModel("3","آموزش صفر تا صد برنامه نویسی اندروید کاتلین و جاوا ","https://homeandroid.ir/wp-content/uploads/2018/09/Full-Stack-Google-Maps-Android-Studio-cover.jpg%22%22"));
        dataModelList.add(new DataModel("4","اپلیکیشن دیجی کالا معماری MVVM ","https://homeandroid.ir/wp-content/uploads/2018/08/digikala-application-android-studio-cover.jpg"));
        dataModelList.add(new DataModel("5","سبد خرید RxJava Kotlin MVVM ","https://homeandroid.ir/wp-content/uploads/2020/05/Shopping-RxJava-Kotlin-small.jpg"));
        return dataModelList;
    }

}
