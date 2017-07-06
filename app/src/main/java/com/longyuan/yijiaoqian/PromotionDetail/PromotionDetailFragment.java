package com.longyuan.yijiaoqian.PromotionDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longyuan.yijiaoqian.Promotion;
import com.longyuan.yijiaoqian.R;

/**
 * Created by loxu on 05/07/2017.
 */

public class PromotionDetailFragment extends Fragment implements PromotionDetailContract.View {

    private PromotionDetailContract.Presenter mPromotionDetailPresent;

    private TextView mDetailId;

    private TextView mDetailTitle;

    private TextView mDetailCategory;

    public static PromotionDetailFragment newInstance() {
        return new PromotionDetailFragment();
    }


    public PromotionDetailFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPromotionDetailPresent.start();
    }

    @Override
    public void displayPromotionDetail(Promotion promotion) {


       mDetailId.setText(Integer.toString(promotion.getId()));

       mDetailTitle.setText(promotion.getTitle());

       mDetailCategory.setText(promotion.getCategory().toString());

        ((PromotionDetailActivity)getActivity()).setWatchedCounter(promotion.getWatchedCount());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.promotion_detail_frag, container, false);

        mDetailId = (TextView) root.findViewById(R.id.promotion_detail_id);

        mDetailTitle = (TextView) root.findViewById(R.id.promotion_detail_title);

        mDetailCategory =(TextView) root.findViewById(R.id.promotion_detail_category);

        return root;
    }


    @Override
    public void setPresenter(PromotionDetailContract.Presenter presenter) {

        mPromotionDetailPresent = presenter;
    }
}
