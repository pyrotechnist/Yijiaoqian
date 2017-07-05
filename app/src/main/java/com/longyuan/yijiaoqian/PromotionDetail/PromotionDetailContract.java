package com.longyuan.yijiaoqian.PromotionDetail;

import com.longyuan.yijiaoqian.Promotion;

/**
 * Created by loxu on 05/07/2017.
 */

public class PromotionDetailContract {
    interface View {

        void setPresenter(Presenter presenter);

        void displayPromotionDetail(Promotion promotion);
    }

    interface Presenter {

        void start();

        Promotion loadPromotion(int PromotionId);

    }
}
