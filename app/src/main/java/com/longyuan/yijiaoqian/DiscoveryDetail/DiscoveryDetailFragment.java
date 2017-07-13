package com.longyuan.yijiaoqian.DiscoveryDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
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

    private TextView textViewFavCount;

    private TextView textViewCommentCount;

    private ShareActionProvider mShareActionProvider;

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

        View viewFav  = root.findViewById(R.id.discovery_detail_bottom_fav);

        textViewFavCount = (TextView) viewFav.findViewById(R.id.discovery_detail_bottom_fav_count);

        viewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.addFavCount();
            }
        });

        textViewCommentCount = (TextView) root.findViewById(R.id.discovery_detail_bottom_comment_count);


        View viewShare  = root.findViewById(R.id.discovery_detail_bottom_share1);

        viewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
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

        textViewId.setText(discovery.getId());

        textViewTitile.setText(discovery.getTitle());

        textViewSubTitile.setText(discovery.getSubTitile());

        textViewFavCount.setText(Integer.toString(discovery.getFavCount()));

        textViewCommentCount.setText(Integer.toString(discovery.getCommentCount()));

    }

    @Override
    public void updateFAvCount(int favCount) {

        textViewFavCount.setText(Integer.toString(favCount));
    }

    @Override
    public void displayComments(String discoveryId) {

        Intent intent = new Intent(getContext(), CommentsActivity.class);

        intent.putExtra(EXTRA_MESSAGE_NAME,discoveryId);

        startActivity(intent);
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "http://stackandroid.com");
        return shareIntent;
    }
}
