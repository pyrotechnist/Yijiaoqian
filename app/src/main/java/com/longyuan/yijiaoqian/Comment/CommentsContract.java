package com.longyuan.yijiaoqian.Comment;

import com.longyuan.yijiaoqian.data.Comment;

import java.util.List;

/**
 * Created by loxu on 10/07/2017.
 */

public class CommentsContract {

    interface View{

        void setPresent(Presenter present);

        void displayComments(List<Comment> comments);

    }

    interface Presenter{
        void start();

        void addComment(Comment comment);

        void addChildComment(Comment parentComment, Comment childrenComment);

        List<Comment> loadComments(String discoveryId);

    }
}
