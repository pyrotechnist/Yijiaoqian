package com.longyuan.yijiaoqian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longyuan.yijiaoqian.data.PromotionsRepository;
import com.longyuan.yijiaoqian.utils.Category;
import com.longyuan.yijiaoqian.utils.PromotionsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsFragment extends Fragment{

    private PromotionsRepository mPromotionsRepository;

    private Category mCategory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.promotions_frag, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setupRecyclerView(RecyclerView recyclerView) {

        List<Promotion> promotions = mPromotionsRepository.getPromotions(mCategory);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new PromotionsRecyclerViewAdapter(promotions,getContext()));
    }

    private List<Promotion> loadPromotions(Category categoty) {

        return mPromotionsRepository.getPromotions(categoty);
    }

    public void setPromotionsRepository(PromotionsRepository mPromotionsRepository) {
        this.mPromotionsRepository = mPromotionsRepository;
    }

    public void setCategory(Category mCategory) {
        this.mCategory = mCategory;
    }
}
