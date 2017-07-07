package com.longyuan.yijiaoqian.Discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.longyuan.yijiaoqian.PromotionsActivity;
import com.longyuan.yijiaoqian.R;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;

public class DiscoveriesActivity extends AppCompatActivity {

    private  BottomNavigationView bottomNavigationView;

    private DiscoveriesPresenter mDiscoveriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discoveries_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_promotion:

                        Intent intent = new Intent(getApplicationContext(), PromotionsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_discovery:

                        return true;
                    case R.id.navigation_contribute:

                        return true;
                    case R.id.navigation_me:

                        return true;
                }
                return false;
            }
        });


        DiscoveriesFragment discoveriesFragment = (DiscoveriesFragment) getSupportFragmentManager().findFragmentById(R.id.discoveries_fragcontent);

        if(discoveriesFragment == null)
        {

            discoveriesFragment = DiscoveriesFragment.getInstance();
            FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.discoveries_fragcontent, discoveriesFragment);
            transaction.commit();
        }


        mDiscoveriesPresenter = new DiscoveriesPresenter(DiscoveryRepository.getInstance(),discoveriesFragment);


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = bottomNavigationView.findViewById(R.id.navigation_discovery);
        view.performClick();
    }
}
