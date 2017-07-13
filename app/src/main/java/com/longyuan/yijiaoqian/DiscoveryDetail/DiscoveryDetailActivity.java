package com.longyuan.yijiaoqian.DiscoveryDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.longyuan.yijiaoqian.Discovery.DiscoveriesActivity;
import com.longyuan.yijiaoqian.Discovery.DiscoveriesFragment;
import com.longyuan.yijiaoqian.PromotionsActivity;
import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;

public class DiscoveryDetailActivity extends AppCompatActivity {

    private  BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discovery_detail_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_discovery_detail);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_discovery_detail_comment:

                        return true;
                    case R.id.navigation_discovery_detail_fav:

                        return true;
                    case R.id.navigation_discovery_detail_share:

                        return true;
                    case R.id.navigation_discovery_detail_share2:

                        return true;
                }
                return false;
            }
        });*/

        Intent intent  = getIntent();

        String discoveryId = intent.getStringExtra(DiscoveriesFragment.EXTRA_MESSAGE_NAME);

        DiscoveryDetailFragment discoveryDetailFragment = (DiscoveryDetailFragment)getSupportFragmentManager().findFragmentById(R.id.discovery_detail_fragcontent);

        if(discoveryDetailFragment == null)
        {
            discoveryDetailFragment = DiscoveryDetailFragment.getInstance();

            getSupportFragmentManager().beginTransaction().add(R.id.discovery_detail_fragcontent,discoveryDetailFragment).commit();
        }


        DiscoveryDetailPresenter discoveryDetailPresenter = new DiscoveryDetailPresenter(discoveryId, DiscoveryRepository.getInstance(),discoveryDetailFragment);



    }

}
