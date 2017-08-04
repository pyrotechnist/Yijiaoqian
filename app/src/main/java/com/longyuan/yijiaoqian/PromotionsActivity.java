package com.longyuan.yijiaoqian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.longyuan.yijiaoqian.Discovery.DiscoveriesActivity;
import com.longyuan.yijiaoqian.data.PromotionsRepository;
import com.longyuan.yijiaoqian.utils.Category;

import java.util.ArrayList;
import java.util.List;

public class PromotionsActivity extends AppCompatActivity {

    private PromotionsRepository mPromotionsRepository;

    private  BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotions_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
       // ab.setHomeAsUpIndicator();
        ab.setDisplayHomeAsUpEnabled(true);


        mPromotionsRepository = PromotionsRepository.getInstance();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_promotion:

                       // onResume();
                        return true;
                    case R.id.navigation_discovery:


                        Intent intent = new Intent(getApplicationContext(), DiscoveriesActivity.class);
                        startActivity(intent);

                        return true;
                    case R.id.navigation_contribute:

                        return true;
                    case R.id.navigation_me:

                        return true;
                }
                return false;
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        PromotionsFragment promotionsFragment1 = PromotionsFragment.getInstance(Category.Better);

        new PromotionsPresenter(mPromotionsRepository,promotionsFragment1,Category.Better);

        //promotionsFragment1.setPromotionsRepository(mPromotionsRepository);
        //promotionsFragment1.setCategory(Category.Better);
        adapter.addFragment(promotionsFragment1, "Better");




        PromotionsFragment promotionsFragment2 = PromotionsFragment.getInstance(Category.Good);

        new PromotionsPresenter(mPromotionsRepository,promotionsFragment2,Category.Good);

        //promotionsFragment1.setPromotionsRepository(mPromotionsRepository);
        //promotionsFragment1.setCategory(Category.Better);
      /*  promotionsFragment2.setPromotionsRepository(mPromotionsRepository);
        promotionsFragment2.setCategory(Category.Good);*/
        adapter.addFragment(promotionsFragment2, "Good");

        viewPager.setAdapter(adapter);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = bottomNavigationView.findViewById(R.id.navigation_promotion);
        view.performClick();
    }

}
