package com.example.amicoli.jsoupdemo.ui;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amicoli.jsoupdemo.R;
import com.example.amicoli.jsoupdemo.adapter.HomeAdapter;
import com.example.amicoli.jsoupdemo.iview.IMainPageDataView;
import com.example.amicoli.jsoupdemo.modle.JianshuBean;
import com.example.amicoli.jsoupdemo.presenter.MainPagePresenter;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.youth.banner.Banner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class MainActivity extends MvpActivity<IMainPageDataView, MainPagePresenter> implements IMainPageDataView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.mbanner)
    Banner mbanner;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout swRefresh;
    @BindView(R.id.tv_ceshi)
    TextView tvCeshi;
    private JianshuBean jianshuBean;
    private List<JianshuBean> jianshus = new ArrayList<>();
    private HomeAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isRefreshing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSwip();
        mSwipeRefreshLayout = new SwipeRefreshLayout(new Service() {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }
        });
        getPresenter().getDataList(isRefreshing);
    }

    @NonNull
    @Override
    public MainPagePresenter createPresenter() {
        return new MainPagePresenter();
    }


    @Override
    public void getData(ResponseBody mainPage) throws IOException {
        setJianShuBeanData(mainPage);
        mAdapter = new HomeAdapter(this);
        mAdapter.openLoadAnimation();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(mAdapter);
        mAdapter.setNewData(jianshus);
        tvCeshi.setText(jianshuBean.getAuthorName());
    }

    private void setJianShuBeanData(ResponseBody mainPage) throws IOException {
        Document document = Jsoup.parse(mainPage.string());
        Elements notelist = document.select("ul.note-list");
        Elements li = notelist.select("li");
        jianshus.clear();
        for (Element element : li) {
            jianshuBean = new JianshuBean();
            jianshuBean.setAuthorName(element.select("div.name").text()); // 作者姓名
            jianshuBean.setAuthorLink(element.select("a.blue-link").attr("abs:href")); // 作者首页链接
            jianshuBean.setTime(timeChange(element.select("span.time").attr("data-shared-at")));   // 发表时间
            jianshuBean.setPrimaryImg(element.select("img").attr("src"));  // 主图
            jianshuBean.setAvatarImg(element.select("a.avatar").select("img").attr("src")); // 头像

            jianshuBean.setTitle(element.select("a.title").text());    // 标题
            jianshuBean.setTitleLink(element.select("a.title").attr("abs:href")); // 标题链接

            jianshuBean.setContent(element.select("p.abstract").text());       // 内容
            jianshuBean.setCollectionTagLink(element.select("a.collection-tag").attr("abs:href")); // 专题链接

            String[] text = element.select("div.meta").text().split(" ");
//                        str.matches("[0-9]+");
            if (text[0].matches("[0-9]+")) {
                jianshuBean.setReadNum(text[0]);
                jianshuBean.setTalkNum(text[1]);
                jianshuBean.setLikeNum(text[2]);
            } else {
                jianshuBean.setCollectionTag(text[0]);
                jianshuBean.setReadNum(text[1]);
                jianshuBean.setTalkNum(text[2]);
                jianshuBean.setLikeNum(text[3]);
            }
            jianshus.add(jianshuBean);
        }

    }

    //改变时间
    private String timeChange(String time) {
        String[] ts = time.split("T");
        String[] split = ts[1].split("\\+");
        return ts[0] + "    " + split[0];
    }

    private void initSwip() {
        swRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swRefresh.setDistanceToTriggerSync(300);
        swRefresh.setOnRefreshListener(this);
        swRefresh.setProgressViewEndTarget(true, 300);
        swRefresh.post(new Runnable() {
            @Override
            public void run() {
                swRefresh.setRefreshing(true);
            }
        });
    }

    @Override
    public void showError() {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disRefresh() {
        swRefresh.setRefreshing(false);
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void onRefresh() {
        if (!isRefreshing) {
            isRefreshing = true;
            swRefresh.post(new Runnable() {
                @Override
                public void run() {
                    swRefresh.setRefreshing(true);
                    getPresenter().getDataList();
                }
            });

        }
    }
}
