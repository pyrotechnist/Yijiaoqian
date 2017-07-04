package com.longyuan.yijiaoqian;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsContract {

    interface View<T> {

        void setPresenter(T presenter);
    }

    interface Presenter {

        void start();

        void loadPromotions();

    }


}
