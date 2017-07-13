package com.longyuan.yijiaoqian.PromotionDetail;

import com.longyuan.yijiaoqian.data.Promotion;
import com.longyuan.yijiaoqian.data.PromotionsRepository;

/**
 * Created by loxu on 05/07/2017.
 */

public class PromotionDetailPresent implements PromotionDetailContract.Presenter {

    private final PromotionsRepository mPromotionsRepository;

    private final PromotionDetailContract.View mPromotionDetailView;

    private String mPromotionId;

    private Promotion mPromotion;



    public PromotionDetailPresent(String promotionId, PromotionsRepository promotionsRepository, PromotionDetailContract.View promotionDetailFragment) {

        mPromotionId = promotionId;

        mPromotionsRepository = promotionsRepository;
        mPromotionDetailView = promotionDetailFragment;

        promotionDetailFragment.setPresenter(this);

    }

    @Override
    public void start() {

        mPromotion = loadPromotion(mPromotionId);

        mPromotionDetailView.displayPromotionDetail(mPromotion);
    }

    @Override
    public Promotion loadPromotion(String PromotionId) {

        return mPromotionsRepository.getPromotion(mPromotionId);
    }
}
