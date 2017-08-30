package com.example.amicoli.jsoupdemo.modle;

import java.util.List;

/**
 * Created by Amicoli on 2017/5/3.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class GrilsBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GrilsBean{" +
                "data=" + data +
                '}';
    }
}
