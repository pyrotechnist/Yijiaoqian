package com.longyuan.yijiaoqian.data;

import com.longyuan.yijiaoqian.utils.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsRepository {


    private static PromotionsRepository INSTANCE = null;

    private static  List<Promotion> mPromotions;

    static {
        mPromotions = new ArrayList<Promotion>();
        mPromotions.add(new Promotion(1,"Better 1","", Category.Better));
        mPromotions.add(new Promotion(2,"Good 1","",Category.Good));
        mPromotions.add(new Promotion(3,"Good 2","",Category.Good));
        mPromotions.add(new Promotion(4,"Good 3","",Category.Good));
        mPromotions.add(new Promotion(5,"Good 4","",Category.Good));
        mPromotions.add(new Promotion(6,"Better 2","",Category.Better));
    }

    private PromotionsRepository() {
    }

    public static PromotionsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PromotionsRepository();
        }
        return INSTANCE;
    }

    public List<Promotion> getPromotions(Category category) {

        //List<Promotion> promotions = new ArrayList<Promotion>();

        List<Promotion> promotionsAfterFilter = new ArrayList<Promotion>();



        for (Promotion element : mPromotions) {

            if(element.getCategory() == category)
            {
                promotionsAfterFilter.add(element);

            }
        }
        return  promotionsAfterFilter;

    }

    public Promotion getPromotion(int promotionId) {

        Promotion promotion = null;

        for (Promotion element : mPromotions) {

            if (element.getId() == promotionId) {
                element.setWatchedCount(element.getWatchedCount() + 1);
                return promotion = element;

            }
        }
        return  promotion;
    }

    public void addPromotion(Promotion promotion){

        mPromotions.add(promotion);
    }

    public int clickPromotion(int promotionId){

        Promotion promotion = null;

        for (Promotion element : mPromotions) {

            if (element.getId() == promotionId) {

                element.setWatchedCount(element.getWatchedCount() + 1);
                promotion = element;

            }
        }
        return  promotion.getWatchedCount();

    }
}
