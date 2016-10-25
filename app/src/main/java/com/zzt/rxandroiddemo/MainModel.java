package com.zzt.rxandroiddemo;

/**
 * Created by Android_ZzT on 16/10/25.
 */

public class MainModel {
    private String[] datas;

    public MainModel() {
        datas = new String[] {
                "一二三四五",
                "上山打老鼠",
                "老鼠不在家",
                "打到小老虎1"
        };
    }

    public String[] getDatas() {
        return datas;
    }

    public void setDatas(String[] datas) {
        this.datas = datas;
    }
}
