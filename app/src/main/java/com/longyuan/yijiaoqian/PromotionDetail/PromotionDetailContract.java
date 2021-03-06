package com.longyuan.yijiaoqian.PromotionDetail;

import com.longyuan.yijiaoqian.data.Promotion;
import com.longyuan.yijiaoqian.utils.Category;

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

        Promotion loadPromotion(String PromotionId, Category category);

    }
}
