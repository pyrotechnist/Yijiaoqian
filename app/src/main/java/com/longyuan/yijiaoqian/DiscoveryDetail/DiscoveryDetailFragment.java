package com.longyuan.yijiaoqian.DiscoveryDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Discovery;

/**
 * Created by LONGYUAN on 2017/7/7.
 */

public class DiscoveryDetailFragment extends Fragment implements DiscoveryDetailContract.View {

    private DiscoveryDetailContract.Presenter mPresenter;

    private TextView textViewId;

    private TextView textViewSubTitile;

    private TextView textViewTitile;

    public DiscoveryDetailFragment(){};

    public static DiscoveryDetailFragment getInstance(){

        return  new DiscoveryDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.discovery_detail_frag,container,false);

        textViewId = (TextView) root.findViewById(R.id.discovery_detail_id);

        textViewTitile = (TextView) root.findViewById(R.id.discovery_detail_title);

        textViewSubTitile = (TextView) root.findViewById(R.id.discovery_detail_subtitle);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(DiscoveryDetailContract.Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public void displayDiscoveryDetail(Discovery discovery) {

        textViewId.setText(Integer.toString(discovery.getId()));

        textViewTitile.setText(discovery.getTitle());

        textViewSubTitile.setText(discovery.getSubTitile());

    }
}
