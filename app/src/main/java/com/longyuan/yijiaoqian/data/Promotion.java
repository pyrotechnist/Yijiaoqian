package com.longyuan.yijiaoqian.data;

import com.longyuan.yijiaoqian.utils.Category;

/**
 * Created by loxu on 04/07/2017.
 */

public class Promotion extends DisplayData {

    public Promotion(String id ,String title, String image, Category category) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.category = category;

    }

    private Boolean isActive;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Category category;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
