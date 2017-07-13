package com.longyuan.yijiaoqian.Comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;

import static com.longyuan.yijiaoqian.DiscoveryDetail.DiscoveryDetailFragment.EXTRA_MESSAGE_NAME;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String discoveryId = getIntent().getStringExtra(EXTRA_MESSAGE_NAME);

        CommentsFragment commentsFragment = (CommentsFragment)getSupportFragmentManager().findFragmentById(R.id.comments_fragcontent);

        if(commentsFragment ==null)
        {
            commentsFragment = CommentsFragment.getInstance();

            getSupportFragmentManager().beginTransaction().add(R.id.comments_fragcontent,commentsFragment).commit();
        }

        new CommentsPresenter(discoveryId, DiscoveryRepository.getInstance(),commentsFragment);
    }

}
