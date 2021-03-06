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
import com.longyuan.yijiaoqian.utils.OnItemLongClickListener;

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

    private Comment mParentComment;

    private Comment mChildComment;

    private String mCommentto;

    private Boolean mIsCurentCommentIsChildComment = false;

    private Boolean mIsCurentCommentReplaytoChildComment = false;

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

                if(!mIsCurentCommentReplaytoChildComment)
                {
                    text = text.replace(mCommentto,"");
                    mCommentto = "";
                }


                Comment comment = null;

                if(!mIsCurentCommentIsChildComment)
                {
                    comment = new Comment(text,"haoyang");
                    mPesenter.addComment(comment);
                }
                else{
                    comment = new Comment(text,"haoyang",true);

                    mPesenter.addChildComment(mParentComment,comment);

                }

                mEditTextComment.setText("");

                mIsCurentCommentIsChildComment = false;

            }
        });

        mCommentsRecyclerViewAdapter = new CommentsRecyclerViewAdapter(new ArrayList<Comment>(0));

        mCommentsRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DisplayData item) {
                String author = item.getContributor();
                mParentComment = (Comment) item;
                mIsCurentCommentIsChildComment = true;
                mCommentto = "@"+author;
                mEditTextComment.setText(mCommentto+ " ");
            }
        });

        mCommentsRecyclerViewAdapter.setmOnItemLongClickListener(new OnItemLongClickListener(){
            @Override
            public void onItemLongClick(DisplayData item) {
                mPesenter.removeComment(item.getId());
                     }
                 });

        mCommentsRecyclerViewAdapter.setmOnItemClickListenerChildrenComments(new OnItemClickListener() {
            @Override
            public void onItemClick(DisplayData item) {
                String author = item.getContributor();
                mChildComment = (Comment) item;
                mIsCurentCommentIsChildComment = true;
                mIsCurentCommentReplaytoChildComment = true;
                mEditTextComment.setText("@"+author+ " ");
            }
        });

        mCommentsRecyclerViewAdapter.setmOnItemLongClickListenerChildrenComments(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(DisplayData item) {
                mPesenter.removeComment(item.getId());
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
