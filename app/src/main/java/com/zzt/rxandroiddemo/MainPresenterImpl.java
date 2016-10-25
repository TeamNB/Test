package com.zzt.rxandroiddemo;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Android_ZzT on 16/10/25.
 */

public class MainPresenterImpl implements TaskDetailContract.MainPresenter {


    Observable<String> mObservable;

    Observer mObserver;

    Context context;

    TaskDetailContract.mainView mView;

    MainModel mModel;

    TextView[] tvs;

    int index = 0;

    int a = 0;

    public MainPresenterImpl(Context context, TaskDetailContract.mainView mView) {
        this.context = context;
        this.mView = mView;
        mModel = new MainModel();
        mView.setPresenter(this); //绑定View 互相持有对方对象

    }


    @Override
    public void start() {
        setAllText();
    }

    @Override
    public void setAllText() {
        String datas[] = mModel.getDatas();
        mView.setAllText(datas);
    }

    @Override
    public void caculate() {
        Observable.create(
                new Observable.OnSubscribe<String>(){
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("1");
                        subscriber.onNext("2");
                        subscriber.onNext("3");
                    }
                }).map(
                new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.valueOf(s);
                    }
                }).subscribe(
                new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        a += integer;
                        Toast.makeText(context, "sum = "+ a, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //被观察者
    private Observable createObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("一二三四五");
                subscriber.onNext("上山打老鼠");
                subscriber.onNext("老鼠不在家");
                subscriber.onNext("打到小老虎");
            }
        });
        return observable;
    }

    //观察者
    private Observer createObserver() {
        Subscriber<String> subcriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(context, "set completed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "set failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(final String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                        tvs[index].setText(s);
                        index ++;
            }
        };
        return subcriber;
    }



}
