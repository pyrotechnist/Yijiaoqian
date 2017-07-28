package com.longyuan.yijiaoqian.Comment;

import com.longyuan.yijiaoqian.data.Comment;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;

import java.util.List;

/**
 * Created by loxu on 10/07/2017.
 */

public class CommentsPresenter implements CommentsContract.Presenter {

    private DiscoveryRepository mDiscoveryRepository;

    private CommentsContract.View mCommentsView;

    private String mDiscoveryId;

    public CommentsPresenter(String discoveryId, DiscoveryRepository discoveryRepository, CommentsContract.View view) {

        mDiscoveryId = discoveryId;

        mDiscoveryRepository = discoveryRepository;

        mCommentsView = view;

        view.setPresent(this);
    }

    @Override
    public void start() {
        List<Comment> commentList = loadComments(mDiscoveryId);

        mCommentsView.displayComments(commentList);
    }



    @Override
    public void addComment(Comment comment) {

        List<Comment> commentList = mDiscoveryRepository.addComment(mDiscoveryId,comment);

        mCommentsView.displayComments(commentList);

    }

    @Override
    public void removeComment(String commentId) {

        List<Comment> commentList = mDiscoveryRepository.removeComment(mDiscoveryId,commentId);

        mCommentsView.displayComments(commentList);

    }

    @Override
    public void addChildComment(Comment parentComment, Comment childrenComment) {
        parentComment.addChildrenComments(childrenComment);

        mCommentsView.displayComments(loadComments(mDiscoveryId));
    }

    @Override
    public List<Comment> loadComments(String discoveryId) {
        return mDiscoveryRepository.getDiscovery(discoveryId).getComments();
    }
}
