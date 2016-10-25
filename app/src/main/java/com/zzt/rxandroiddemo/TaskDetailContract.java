package com.zzt.rxandroiddemo;

import com.zzt.rxandroiddemo.base.IBasePresenter;
import com.zzt.rxandroiddemo.base.IBaseView;

/**
 * Created by Android_ZzT on 16/10/25.
 *  契约类
    官方的实现中加入了契约类来统一管理view与presenter的所有的接口，
    这种方式使得view与presenter中有哪些功能，一目了然，维护起来也方便
 */

public class TaskDetailContract {
    interface mainView extends IBaseView<MainPresenter> {
        void setAllText(String[] text);
    }

    interface MainPresenter extends IBasePresenter {
        void setAllText();
        void caculate();
    }
}
