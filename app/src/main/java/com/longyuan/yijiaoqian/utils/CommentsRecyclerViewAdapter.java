package com.longyuan.yijiaoqian.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by loxu on 10/07/2017.
 */

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.CommentsAdapter>{

    private List<Comment> mComments;

    private Context mContext;

    public CommentsRecyclerViewAdapter(List<Comment> mComments) {
        this.mComments = mComments;
    }

    @Override
    public CommentsAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);

        mContext = root.getContext();

        return new CommentsAdapter(root);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter holder, int position) {

        Comment comment = mComments.get(position);

        holder.textViewContent.setText(comment.getContent());

        String dateDiff  = DateHelper.dateDifference(comment.getDate(), Calendar.getInstance().getTime());

        holder.textViewDate.setText(dateDiff);

        holder.textViewAuthor.setText(comment.getContributor());

        ArrayList<String> list = new ArrayList<String>();

        list.add("AAA");

        list.add("BBB");

        ArrayAdapter adapter = new ArrayAdapter(mContext,
                android.R.layout.simple_list_item_1, list);
        holder.listView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    /**
     *
     * @param comments
     */
    public void replaceData(List<Comment> comments){

        mComments = comments;

        notifyDataSetChanged();
    }


    /**
     *
     */
    public static class CommentsAdapter extends RecyclerView.ViewHolder {


        ImageView imageView;

        TextView textViewAuthor;

        TextView textViewDate;

        TextView textViewContent;

        ListView listView;


        public CommentsAdapter(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.comment_image);

            textViewAuthor = (TextView) itemView.findViewById(R.id.comment_author);

            textViewDate = (TextView) itemView.findViewById(R.id.comment_date);

            textViewContent = (TextView) itemView.findViewById(R.id.comment_content);

            listView = (ListView) itemView.findViewById(R.id.testlist);

        }
    }
}
