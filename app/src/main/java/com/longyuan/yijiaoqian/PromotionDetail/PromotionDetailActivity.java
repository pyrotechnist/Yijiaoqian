package com.longyuan.yijiaoqian.PromotionDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.longyuan.yijiaoqian.PromotionsActivity;
import com.longyuan.yijiaoqian.PromotionsFragment;
import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.PromotionsRepository;
import com.longyuan.yijiaoqian.utils.Category;

public class PromotionDetailActivity extends AppCompatActivity {

    private PromotionDetailPresent mPromotionDetailPresent;

    private  TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator();
        ab.setDisplayHomeAsUpEnabled(true);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.watchedcounter, null);

        mTitleTextView = (TextView) mCustomView.findViewById(R.id.watchedcounter_count);
        mTitleTextView.setText("My Own Title");

        ImageView imageView = (ImageView) mCustomView.findViewById(R.id.watchedcounter_logo);

        imageView.setImageResource(android.R.drawable.btn_star_big_on);



        ab.setCustomView(mCustomView);
        ab.setDisplayShowCustomEnabled(true);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String promotionId = intent.getStringExtra(PromotionsFragment.EXTRA_MESSAGE_NAME);

        Category category = (Category) intent.getSerializableExtra(PromotionsFragment.CATEGORY_NAME);

       PromotionDetailFragment promotionDetailFragment =  (PromotionDetailFragment) getSupportFragmentManager().findFragmentById(R.id.promotion_detail_fragcontent);

        if (promotionDetailFragment == null) {
            // Create the fragment
            promotionDetailFragment = PromotionDetailFragment.newInstance();
            //promotionDetailFragment.setId(promotionId);
            FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.promotion_detail_fragcontent, promotionDetailFragment);
            transaction.commit();
        }


        mPromotionDetailPresent = new PromotionDetailPresent(promotionId,PromotionsRepository.getInstance(),promotionDetailFragment);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setWatchedCounter(int count){
        mTitleTextView.setText(Integer.toString(count));
    }



}
