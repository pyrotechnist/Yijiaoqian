package com.longyuan.yijiaoqian.Comment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Comment;
import com.longyuan.yijiaoqian.data.DisplayData;
import com.longyuan.yijiaoqian.utils.CommentsRecyclerViewAdapter;
import com.longyuan.yijiaoqian.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 10/07/2017.
 */

public class CommentsFragment extends Fragment implements CommentsContract.View{

    private CommentsContract.Presenter mPesenter;

    private RecyclerView mRecyclerViewComments;

    private CommentsRecyclerViewAdapter mCommentsRecyclerViewAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayoutComments;

    private EditText mEditTextComment;

    private ImageView mImageView;

    public static CommentsFragment getInstance (){
        return  new CommentsFragment();
    }

    @Override
    public void setPresent(CommentsContract.Presenter presenter) {
        mPesenter = presenter;
    }

    @Override
    public void displayComments(List<Comment> comments) {

        mCommentsRecyclerViewAdapter.replaceData(comments);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.comments_frag,container,false);

        mSwipeRefreshLayoutComments = (SwipeRefreshLayout) root.findViewById(R.id.swipeToRefresh_comments);

        mRecyclerViewComments = (RecyclerView) root.findViewById(R.id.recyclerview_comments);

        mEditTextComment = (EditText) root.findViewById(R.id.comment_add);

        mImageView = (ImageView)root.findViewById(R.id.comment_send);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEditTextComment.getText().toString();
                Comment comment = new Comment(text,"haoyang");
                mEditTextComment.setText("");
                mPesenter.addComment(comment);

            }
        });

        mCommentsRecyclerViewAdapter = new CommentsRecyclerViewAdapter(new ArrayList<Comment>(0));

        mCommentsRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DisplayData item) {
                String author = item.getContributor();
                mEditTextComment.setText("@"+author);
            }
        });

        mRecyclerViewComments.setLayoutManager(new LinearLayoutManager(mRecyclerViewComments.getContext()));
        mRecyclerViewComments.setAdapter(mCommentsRecyclerViewAdapter);

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        mPesenter.start();
    }
}
