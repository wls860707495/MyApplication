package com.myapplication.View.bean;

public class ImageInfor {
    private String imageurl;
    private String title;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushtime() {
        return pushtime;
    }

    public void setPushtime(String pushtime) {
        this.pushtime = pushtime;
    }

    private String pushtime;
    public ImageInfor(String imageurl,String title,String name,String pushtime){
        this.imageurl=imageurl;
        this.title=title;
        this.name=name;
        this.pushtime=pushtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getimageurl() {
        return imageurl;
    }

    public void setimageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
