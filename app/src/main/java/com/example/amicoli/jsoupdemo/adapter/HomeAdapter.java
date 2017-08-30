package com.example.amicoli.jsoupdemo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.amicoli.jsoupdemo.R;
import com.example.amicoli.jsoupdemo.modle.JianshuBean;

import java.util.List;

/**
 * Created by Amicoli on 2017/6/17.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class HomeAdapter extends BaseQuickAdapter<JianshuBean,BaseViewHolder> {

    private Context context;

    public HomeAdapter(Context context) {
        super(R.layout.item_home);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, JianshuBean item) {
            helper.setText(R.id.tv_author,item.getAuthorName())
                    .setText(R.id.tv_time,item.getTime())
                    .setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_content,item.getContent())
                    .setText(R.id.tv_collectTag,item.getCollectionTag())
                    .setText(R.id.tv_read, item.getReadNum())
                    .setText(R.id.tv_talk, item.getTalkNum())
                    .setText(R.id.tv_like, item.getLikeNum());
        Glide.with(context).load(item.getAvatarImg()).crossFade().into((ImageView) helper.getView(R.id.iv_avatar));
        Glide.with(context).load(item.getPrimaryImg()).crossFade().into((ImageView) helper.getView(R.id.iv_primary));
    }
}
