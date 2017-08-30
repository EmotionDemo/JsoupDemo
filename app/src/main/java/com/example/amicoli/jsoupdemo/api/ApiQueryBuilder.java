package com.example.amicoli.jsoupdemo.api;

import com.example.amicoli.jsoupdemo.modle.BlogBean;
import com.example.amicoli.jsoupdemo.modle.GrilsBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Amicoli on 2017/6/16.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class ApiQueryBuilder {
    private static final String BASE_URL = "http://www.jianshu.com/";
    private static final int DEFAULT_TIME_OUT = 10;
    private static Api api;

    private ApiQueryBuilder() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//设置默认超时

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(Api.class);

    }
    //在访问QueryBuilder时创建单例
    private static class SingletonHolder {
        private static final ApiQueryBuilder INSTANCE = new ApiQueryBuilder();
    }


    public static ApiQueryBuilder getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /*
    * 获取首页数据
    * */

    public void getMainPageData(Subscriber<ResponseBody> subscriber){
        api.getMainData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
