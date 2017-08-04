package com.longyuan.yijiaoqian.PromotionDetail;

import com.longyuan.yijiaoqian.data.Promotion;
import com.longyuan.yijiaoqian.data.PromotionsRepository;
import com.longyuan.yijiaoqian.utils.Category;

/**
 * Created by loxu on 05/07/2017.
 */

public class PromotionDetailPresent implements PromotionDetailContract.Presenter {

    private final PromotionsRepository mPromotionsRepository;

    private final PromotionDetailContract.View mPromotionDetailView;

    private String mPromotionId;

    private Promotion mPromotion;

    private Category mCategory;


    public PromotionDetailPresent(String promotionId, Category category, PromotionsRepository promotionsRepository, PromotionDetailContract.View promotionDetailFragment) {

        mPromotionId = promotionId;

        mPromotionsRepository = promotionsRepository;
        mPromotionDetailView = promotionDetailFragment;
        mCategory = category;

        promotionDetailFragment.setPresenter(this);

    }

    @Override
    public void start() {

        mPromotion = loadPromotion(mPromotionId,mCategory);

        mPromotionDetailView.displayPromotionDetail(mPromotion);
    }

    @Override
    public Promotion loadPromotion(String PromotionId, Category category) {

        return mPromotionsRepository.getPromotion(mPromotionId,category);
    }
}
