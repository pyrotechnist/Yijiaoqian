package com.longyuan.yijiaoqian;

import com.longyuan.yijiaoqian.data.Promotion;

import java.util.List;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsContract {

    interface View {

        void setPresenter(Presenter presenter);

        void displayPromnotions(List<Promotion> promotionList);
    }

    interface Presenter {

        void start();

        void loadPromotions();

    }


}
