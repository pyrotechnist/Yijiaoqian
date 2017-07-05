package com.longyuan.yijiaoqian.PromotionDetail;

import com.longyuan.yijiaoqian.Promotion;
import com.longyuan.yijiaoqian.PromotionsContract;
import com.longyuan.yijiaoqian.data.PromotionsRepository;

/**
 * Created by loxu on 05/07/2017.
 */

public class PromotionDetailPresent implements PromotionDetailContract.Presenter {

    private final PromotionsRepository mPromotionsRepository;

    private final PromotionDetailContract.View mPromotionDetailView;

    private int mPromotionId;

    private Promotion mPromotion;



    public PromotionDetailPresent(int promotionId, PromotionsRepository promotionsRepository, PromotionDetailContract.View promotionDetailFragment) {

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
    public Promotion loadPromotion(int PromotionId) {

        return mPromotionsRepository.getPromotion(mPromotionId);
    }
}
