package com.longyuan.yijiaoqian.DiscoveryDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longyuan.yijiaoqian.Comment.CommentsActivity;
import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Comment;
import com.longyuan.yijiaoqian.data.Discovery;

import java.util.List;

/**
 * Created by LONGYUAN on 2017/7/7.
 */

public class DiscoveryDetailFragment extends Fragment implements DiscoveryDetailContract.View {

    public static final String EXTRA_MESSAGE_NAME = "com.longyuan.yijiaoqian.MESSAGE_NAME.discoveryId";

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

        View view  = root.findViewById(R.id.discovery_detail_bottom_comment);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.openComments();
            }
        });

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

    @Override
    public void displayComments(int discoveryId) {

        Intent intent = new Intent(getContext(), CommentsActivity.class);

        intent.putExtra(EXTRA_MESSAGE_NAME,discoveryId);

        startActivity(intent);
    }
}