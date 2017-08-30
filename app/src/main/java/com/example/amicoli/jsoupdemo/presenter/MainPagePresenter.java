package com.example.amicoli.jsoupdemo.presenter;

import android.util.Log;


import com.example.amicoli.jsoupdemo.api.ApiQueryBuilder;
import com.example.amicoli.jsoupdemo.iview.IMainPageDataView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;



import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Amicoli on 2017/6/16.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class MainPagePresenter extends MvpBasePresenter<IMainPageDataView> {
    public void getDataList(final boolean isRefresh){
        final IMainPageDataView iMainPageDataView = getView();
        if (iMainPageDataView != null){
            Subscriber<ResponseBody> subscriber = new Subscriber<ResponseBody>() {
                @Override
                public void onCompleted() {
                    iMainPageDataView.showSuccess();
                }

                @Override
                public void onError(Throwable e) {
                    iMainPageDataView.showError();
                }

                @Override
                public void onNext(ResponseBody responseBody) {
                    Log.e("onNext:=====",responseBody.toString());
                    try {
                        if (isRefresh){
                            iMainPageDataView.getData(responseBody);
                            iMainPageDataView.disRefresh();
                        }else {
                            iMainPageDataView.refreshData();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            ApiQueryBuilder.getInstance().getMainPageData(subscriber);
        }
    }
}
