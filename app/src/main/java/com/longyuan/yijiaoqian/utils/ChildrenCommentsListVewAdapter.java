package com.longyuan.yijiaoqian.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Comment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by loxu on 13/07/2017.
 */

public class ChildrenCommentsListVewAdapter extends ArrayAdapter<Comment> {


    private Comment[] mCommentList;



    private OnItemClickListener mOnItemClickListenerChildrenCommentsListView;

    public ChildrenCommentsListVewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Comment[] objects) {
        super(context, resource, objects);
        mCommentList =  objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.childrencomment_item, parent, false);
        }





        final Comment comment = mCommentList[position];

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenerChildrenCommentsListView.onItemClick(comment);
            }
        };

        ImageView image = (ImageView) rowView.findViewById(R.id.childrencomment_logo);

        TextView text = (TextView) rowView.findViewById(R.id.childrencomment_content);

        image.setImageResource(R.drawable.sport_1);

        text.setText(comment.getContent());

        return rowView;
    }

    public void setmOnItemClickListenerChildrenCommentsListView(OnItemClickListener mOnItemClickListenerChildrenCommentsListView) {
        this.mOnItemClickListenerChildrenCommentsListView = mOnItemClickListenerChildrenCommentsListView;
    }
}
