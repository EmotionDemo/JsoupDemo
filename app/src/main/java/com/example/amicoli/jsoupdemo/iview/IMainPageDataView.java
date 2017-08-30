package com.example.amicoli.jsoupdemo.iview;

import com.example.amicoli.jsoupdemo.modle.BlogBean;
import com.example.amicoli.jsoupdemo.modle.GrilsBean;
import com.hannesdorfmann.mosby.mvp.MvpView;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Amicoli on 2017/6/16.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public interface IMainPageDataView extends MvpView{
    void getData(ResponseBody mainPage) throws IOException;

    void showError();
    void disRefresh();
    void showSuccess();

    void refreshData();
}
