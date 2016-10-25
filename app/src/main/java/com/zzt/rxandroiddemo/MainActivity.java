package com.zzt.rxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements TaskDetailContract.mainView {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;

    TextView[] tvs;

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.btn2)
    Button btn2;

    TaskDetailContract.MainPresenter mPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvs = new TextView[]{tv1,tv2,tv3,tv4};
        mPresenter = new MainPresenterImpl(this , this);

    }



    @OnClick(R.id.btn)
    public void setText() {
        mPresenter.setAllText();
    }

    @OnClick(R.id.btn2)
    public void caculate() {
        mPresenter.caculate();
    }


    @Override
    public void setAllText(String[] texts) {
        for(int i=0; i < texts.length ; i++) {
            tvs[i].setText(texts[i]);
        }
    }


    @Override
    public void setPresenter(TaskDetailContract.MainPresenter presenter) {
        mPresenter = presenter;
    }
}
