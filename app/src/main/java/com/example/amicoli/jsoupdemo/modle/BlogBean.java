package com.example.amicoli.jsoupdemo.modle;

/**
 * Created by Amicoli on 2017/6/16.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class BlogBean {

    private String author;
    private String name;
    private String image;
    private String targetUrl;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public String toString() {
        return "BlogBean{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                '}';
    }
}
