package com.zzt.rxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;

    TextView[] tvs;

    int index = 0;

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.btn2)
    Button btn2;

    Observable<String> mObservable;

    Observer mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvs = new TextView[]{tv1,tv2,tv3,tv4};
        mObservable = createObservable();
        mObserver = createObserver();
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
                Toast.makeText(MainActivity.this, "set completed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "set failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(final String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                tvs[index].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvs[index].setText(s);
                        index ++;
                    }
                },1000);
            }
        };
        return subcriber;
    }

    @OnClick(R.id.btn)
    public void setText() {
        mObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    int a = 0;
    @OnClick(R.id.btn2)
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
                        Toast.makeText(MainActivity.this, "sum = "+ a, Toast.LENGTH_SHORT).show();
                    }
                });




    }
}
