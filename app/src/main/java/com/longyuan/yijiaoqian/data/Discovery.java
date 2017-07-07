package com.longyuan.yijiaoqian.data;

import com.longyuan.yijiaoqian.utils.Category;

/**
 * Created by loxu on 06/07/2017.
 */

public class Discovery extends DisplayData{


    public Discovery(int id ,String title, String image,String subTitile) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subTitile = subTitile;
    }

    private String contributor;

    private String subTitile;

    private int favCount;

    private int commentCount;

    public String getSubTitile() {
        return subTitile;
    }

    public void setSubTitile(String subTitile) {
        this.subTitile = subTitile;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
