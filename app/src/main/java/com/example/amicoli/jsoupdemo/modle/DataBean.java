package com.example.amicoli.jsoupdemo.modle;

/**
 * Created by Amicoli on 2017/5/3.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */

public class DataBean {
    /**
     * id : 1
     * name : gril1
     * image : /data/img/gril1.jpg
     */

    private String id;
    private String name;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
