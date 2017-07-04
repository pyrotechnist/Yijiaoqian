package com.longyuan.yijiaoqian.data;

import android.support.annotation.NonNull;

import com.longyuan.yijiaoqian.Promotion;
import com.longyuan.yijiaoqian.utils.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsRepository {


    private static PromotionsRepository INSTANCE = null;


    private PromotionsRepository() {
    }

    public static PromotionsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PromotionsRepository();
        }
        return INSTANCE;
    }

    public List<Promotion> getPromotions(Category category) {

        List<Promotion> promotions = new ArrayList<Promotion>();
        promotions.add(new Promotion("Better 1","", Category.Better));
        promotions.add(new Promotion("Good 1","",Category.Good));
        promotions.add(new Promotion("Good 2","",Category.Good));
        promotions.add(new Promotion("Good 3","",Category.Good));
        promotions.add(new Promotion("Good 4","",Category.Good));
        promotions.add(new Promotion("Better 2","",Category.Better));


        List<Promotion> promotionsAfterFilter = new ArrayList<Promotion>();

        for (Promotion element : promotions) {

            if(element.getCategory() == category)
            {
                promotionsAfterFilter.add(element);

            }
        }

        return  promotionsAfterFilter;

    }
}
