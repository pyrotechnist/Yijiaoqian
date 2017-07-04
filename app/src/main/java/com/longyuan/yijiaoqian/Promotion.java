package com.longyuan.yijiaoqian;

import com.longyuan.yijiaoqian.utils.Category;

/**
 * Created by loxu on 04/07/2017.
 */

public class Promotion {

    public Promotion(String title, String image, Category category) {
        this.image = image;
        this.title = title;
        this.category = category;

    }

    private String image;

    private String title;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Category category;

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
}
