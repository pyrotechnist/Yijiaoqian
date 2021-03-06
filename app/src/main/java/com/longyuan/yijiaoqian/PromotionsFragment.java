package com.longyuan.yijiaoqian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.longyuan.yijiaoqian.PromotionDetail.PromotionDetailActivity;
import com.longyuan.yijiaoqian.data.DisplayData;
import com.longyuan.yijiaoqian.data.LoadDataCallback;
import com.longyuan.yijiaoqian.data.Promotion;
import com.longyuan.yijiaoqian.data.PromotionsRepository;
import com.longyuan.yijiaoqian.utils.Category;
import com.longyuan.yijiaoqian.utils.OnItemClickListener;
import com.longyuan.yijiaoqian.utils.PromotionsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsFragment extends Fragment implements SearchView.OnQueryTextListener,PromotionsContract.View{

    public static final String EXTRA_MESSAGE_NAME = "com.longyuan.yijiaoqian.MESSAGE_NAME";

    public static final String CATEGORY_NAME = "com.longyuan.yijiaoqian.CATEGORY_NAME";

    private PromotionsRepository mPromotionsRepository;

    private PromotionsRecyclerViewAdapter mPromotionsRecyclerViewAdapter;

    private  List<Promotion> mPromotions = new ArrayList<>();

    private  SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private PromotionsContract.Presenter mPresenter;

    private Category mCategory;

    public static PromotionsFragment getInstance(Category category) {

        PromotionsFragment instance = new PromotionsFragment();
        instance.mCategory = category;
        return  instance;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Promotion> filteredModelList = filter(mPromotions, newText);
        mPromotionsRecyclerViewAdapter.setFilter(filteredModelList);
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSwipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(
                R.layout.promotions_frag, container, false);


        mRecyclerView = (RecyclerView) mSwipeRefreshLayout.findViewById(R.id.recyclerview);
        setupRecyclerView();
        return mSwipeRefreshLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);


    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

    }

    private void loadData(){

        mPromotionsRepository.getPromotions(mCategory, new LoadDataCallback() {
            @Override
            public void onTasksLoaded(List<Promotion> promotions) {
                mPromotions = promotions;
                mPromotionsRecyclerViewAdapter.replaceData(mPromotions);

            }
        });

    }

    @Override
    public void setPresenter(PromotionsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayPromnotions(List<Promotion> promotionList) {
        mPromotionsRecyclerViewAdapter.replaceData(promotionList);
    }

    private void setupRecyclerView( ) {

        mPromotionsRecyclerViewAdapter = new PromotionsRecyclerViewAdapter(mPromotions,getContext());

        mPromotionsRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DisplayData promotion) {
                Intent intent = new Intent(getContext(), PromotionDetailActivity.class);
                intent.putExtra(EXTRA_MESSAGE_NAME, promotion.getId());
                intent.putExtra(CATEGORY_NAME, mCategory);
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

               /* mPromotionsRepository.addPromotion(new Promotion("10","Better 10","", Category.Better));
                mPromotions = mPromotionsRepository.getPromotions(mCategory);
                //mPromotionsRecyclerViewAdapter = new PromotionsRecyclerViewAdapter(mPromotions,getContext());
                //mRecyclerView.setAdapter(mPromotionsRecyclerViewAdapter);
                mPromotionsRecyclerViewAdapter.setFilter(mPromotions);
                mSwipeRefreshLayout.setRefreshing(false);*/
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mPromotionsRecyclerViewAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        mPromotionsRecyclerViewAdapter.setFilter(mPromotions);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }


  /*  private List<Promotion> loadPromotions(Category categoty) {

        return mPromotionsRepository.getPromotions(categoty);
    }*/

    private List<Promotion> filter(List<Promotion> models, String query) {
        query = query.toLowerCase();

        final List<Promotion> filteredModelList = new ArrayList<>();
        for (Promotion model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
