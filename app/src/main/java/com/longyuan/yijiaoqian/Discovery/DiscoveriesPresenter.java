package com.longyuan.yijiaoqian.Discovery;

import com.longyuan.yijiaoqian.data.Discovery;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;

import java.util.List;

/**
 * Created by loxu on 07/07/2017.
 */

public class DiscoveriesPresenter implements DiscoveriesContract.Presenter {

    private  DiscoveryRepository mDiscoveryRepository;

    private DiscoveriesContract.View   mDiscoveriesView;

    private List<Discovery> mDiscoveryList;

    public DiscoveriesPresenter(DiscoveryRepository discoveryRepository, DiscoveriesContract.View discoveriesFragment) {

        mDiscoveryRepository = discoveryRepository;

        mDiscoveriesView = discoveriesFragment;

        discoveriesFragment.setPresenter(this);
    }

    @Override
    public void start() {

        mDiscoveryList = loadDiscoveries();

        mDiscoveriesView.displayDiscoveries(mDiscoveryList);
    }

    @Override
    public List<Discovery> loadDiscoveries() {
        return mDiscoveryRepository.getDiscoveries();
    }
}
