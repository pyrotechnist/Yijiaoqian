package com.longyuan.yijiaoqian.data;

/**
 * Created by loxu on 07/07/2017.
 */

public class DisplayData {

    String id;

    String image;

    String title;

    int watchedCount;

    String contributor;

    public int getWatchedCount() {
        return watchedCount;
    }

    public void setWatchedCount(int watchedCount) {
        this.watchedCount = watchedCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }
}
