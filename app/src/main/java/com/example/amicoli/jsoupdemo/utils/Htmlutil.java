package com.example.amicoli.jsoupdemo.utils;

import com.example.amicoli.jsoupdemo.modle.BlogBean;
import com.example.amicoli.jsoupdemo.modle.JianshuBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amicoli on 2017/6/16.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class Htmlutil {
    public void parseHtml(String html) {
//        //将html转为Document对象
//        Document document = Jsoup.parse(html);
//        //获得li的元素集合
//        Elements elements = document.select("div#list-container ul li");
//        List<BlogBean> lists = new ArrayList<>();
//        BlogBean blogModel;
//        for (Element element : elements) {
//            //获得作者
//            String author = element.select("div.name a").first().text();
//            //获得标题
//            String title = element.select("a.title").first().text();
//            //获得图片url，因为文章有可能没有图片，所以这里需要特殊处理一下
//            String image = element.select("a.wrap-img").first() != null ?
//                    element.select("a.wrap-img").first().children().first().attr("src") : "";
//            //获得文章详情url
//            String targetUrl = element.select("a.title").first().attr("href");
//            blogModel = new BlogBean();
//            blogModel.setAuthor(author);
//            blogModel.setName(title);
//            blogModel.setImage(image);
//            blogModel.setTargetUrl(targetUrl);
//            lists.add(blogModel);
//        }
//    }
        Document document = Jsoup.parse(html);
        Elements notelist = document.select("ul.note-list");
        Elements li = notelist.select("li");
        for (Element element : li){
            JianshuBean jianshuBean = new JianshuBean();
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

        }

    }
    private String timeChange(String time){
        String [] ts = time.split("T");
        String [] split = ts[1].split("\\+");
        return ts[0] + "    " +  split[0];
    }
}
