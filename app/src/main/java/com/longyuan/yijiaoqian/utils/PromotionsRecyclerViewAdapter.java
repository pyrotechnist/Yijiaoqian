package com.longyuan.yijiaoqian.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longyuan.yijiaoqian.Promotion;
import com.longyuan.yijiaoqian.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsRecyclerViewAdapter extends RecyclerView.Adapter<PromotionsRecyclerViewAdapter.PromotionViewHolder> {

    private CardView cardView;
    //private ImageView imageView;
    //private TextView textView;

    private List<Promotion> promotionsList ;

    private Context context;


    public PromotionsRecyclerViewAdapter(List<Promotion> promotionsList,Context context) {
        this.promotionsList = promotionsList;
        this.context = context;
    }

    @Override
    public PromotionsRecyclerViewAdapter.PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_item, parent, false);



        PromotionViewHolder vh = new PromotionViewHolder(root);

        return vh;

    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {

        Promotion promotion  = promotionsList.get(position);

        holder.textView.setText(promotion.getTitle());

        final Random rand = new Random();
        int diceRoll = rand.nextInt(6) + 1;

        String url = "http://lorempixel.com/400/200/sports/" + diceRoll;

       Glide.with(holder.imageView.getContext())
                .load(url)
                .fitCenter()
                .into(holder.imageView);

        //holder.imageView.setImageResource(R.drawable.sport_1);

        //holder.imageView.setImageResource(android.R.drawable.arrow_up_float);
    }


    @Override
    public int getItemCount() {
        return promotionsList.size();
    }

    public static class PromotionViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public PromotionViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.promotion_image);

            textView = (TextView) itemView.findViewById(R.id.promotion_title);
            ;
        }
    }

    public void setFilter(List<Promotion> countryModels){
        promotionsList = new ArrayList<>();
        promotionsList.addAll(countryModels);
        notifyDataSetChanged();
    }
}
