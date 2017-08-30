package com.example.amicoli.jsoupdemo.api;

import com.example.amicoli.jsoupdemo.modle.BlogBean;
import com.example.amicoli.jsoupdemo.modle.GrilsBean;


import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Amicoli on 2017/6/16.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public interface Api {
    //主页内容
    @GET("/")
    Observable<ResponseBody>getMainData();
}
