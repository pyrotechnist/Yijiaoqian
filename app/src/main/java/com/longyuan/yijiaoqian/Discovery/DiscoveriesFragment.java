package com.longyuan.yijiaoqian.Discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longyuan.yijiaoqian.PromotionDetail.PromotionDetailActivity;
import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Discovery;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;
import com.longyuan.yijiaoqian.data.DisplayData;
import com.longyuan.yijiaoqian.data.Promotion;
import com.longyuan.yijiaoqian.utils.Category;
import com.longyuan.yijiaoqian.utils.DiscoveriesRecyclerViewAdapter;
import com.longyuan.yijiaoqian.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.longyuan.yijiaoqian.PromotionsFragment.EXTRA_MESSAGE_NAME;

/**
 * Created by loxu on 07/07/2017.
 */

public class DiscoveriesFragment extends Fragment implements DiscoveriesContract.View {

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Discovery> mDiscoveriesList;

    private DiscoveriesContract.Presenter mPresenter;

    private DiscoveriesRecyclerViewAdapter mDiscoveriesRecyclerViewAdapter;

    public static DiscoveriesFragment getInstance(){

        return  new DiscoveriesFragment();
    }

    public DiscoveriesFragment(){
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSwipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.discoveries_frag,container,false);

        mRecyclerView = (RecyclerView) mSwipeRefreshLayout.findViewById(R.id.recyclerview_discoveries);

        setupRecyclerView();

        return mSwipeRefreshLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void setupRecyclerView(){

       mDiscoveriesRecyclerViewAdapter = new DiscoveriesRecyclerViewAdapter(new ArrayList<Discovery>());
        mDiscoveriesRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DisplayData discovery) {
                Intent intent = new Intent(getContext(), PromotionDetailActivity.class);
                intent.putExtra(EXTRA_MESSAGE_NAME, discovery.getId());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                
                mDiscoveriesRecyclerViewAdapter.replaceData(mPresenter.loadDiscoveries());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mDiscoveriesRecyclerViewAdapter);
    }

    @Override
    public void setPresenter(DiscoveriesContract.Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public void displayDiscoveries(List<Discovery> discoveryList) {
        mDiscoveriesRecyclerViewAdapter.replaceData(discoveryList);
    }
}
