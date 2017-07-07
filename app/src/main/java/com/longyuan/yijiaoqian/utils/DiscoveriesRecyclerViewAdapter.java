package com.longyuan.yijiaoqian.utils;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.Discovery;

import java.util.List;

/**
 * Created by loxu on 07/07/2017.
 */

public class DiscoveriesRecyclerViewAdapter extends RecyclerView.Adapter<DiscoveriesRecyclerViewAdapter.DiscoveriesViewHolder> {

    private List<Discovery> mDiscoveryList;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;


    public DiscoveriesRecyclerViewAdapter(List<Discovery> mDiscoveryList) {
        this.mDiscoveryList = mDiscoveryList;
    }

    // private


    public static class DiscoveriesViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        TextView  textViewTitle;

        TextView textViewSubtitle;


        public DiscoveriesViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.discovery_image);

            textViewTitle = (TextView) itemView.findViewById(R.id.discovery_title);

            textViewSubtitle = (TextView) itemView.findViewById(R.id.discovery_subtitle);

        }


    }


    @Override
    public DiscoveriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discovery_item, parent, false);

        DiscoveriesViewHolder vh = new DiscoveriesViewHolder(root);

        return vh;
    }

    @Override
    public void onBindViewHolder(DiscoveriesViewHolder holder, int position) {


        final Discovery discovery = mDiscoveryList.get(position);

        holder.imageView.setImageResource(R.drawable.sport_1);

        holder.textViewTitle.setText(discovery.getTitle());

        holder.textViewSubtitle.setText(discovery.getSubTitile());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(discovery);
            }
        };

        holder.itemView.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return mDiscoveryList.size();
    }


    public void replaceData(List<Discovery> discoveryList){
        mDiscoveryList = discoveryList;

        notifyDataSetChanged();
    }
}
