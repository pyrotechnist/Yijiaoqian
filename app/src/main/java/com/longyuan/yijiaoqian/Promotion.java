package com.longyuan.yijiaoqian;

import com.longyuan.yijiaoqian.utils.Category;

/**
 * Created by loxu on 04/07/2017.
 */

public class Promotion {

    public Promotion(int id ,String title, String image, Category category) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.category = category;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    private int id;

    private Boolean isActive;

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
