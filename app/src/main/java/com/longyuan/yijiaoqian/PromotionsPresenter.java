package com.longyuan.yijiaoqian;

import com.longyuan.yijiaoqian.data.LoadDataCallback;
import com.longyuan.yijiaoqian.data.Promotion;
import com.longyuan.yijiaoqian.data.PromotionsRepository;
import com.longyuan.yijiaoqian.utils.Category;

import java.io.IOException;
import java.util.List;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsPresenter implements PromotionsContract.Presenter {


    private PromotionsContract.View mMainView;

    private PromotionsRepository mPromotionsRepository;

    private Category mCategory;

    public PromotionsPresenter(PromotionsRepository promotionsRepository, PromotionsContract.View view, Category category) {

        mPromotionsRepository = promotionsRepository;
        mMainView = view;
        mCategory = category;
        view.setPresenter(this);
    }


    @Override
    public void start() {

        loadPromotions();
    }

    @Override
    public void loadPromotions() {


        mPromotionsRepository.getPromotions(mCategory, new LoadDataCallback() {
            @Override
            public void onTasksLoaded(List<Promotion> promotions) {
                mMainView.displayPromnotions(promotions);
            }

        });


    }
}
