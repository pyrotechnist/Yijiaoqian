package com.longyuan.yijiaoqian.DiscoveryDetail;

import com.longyuan.yijiaoqian.data.Discovery;
import com.longyuan.yijiaoqian.data.DiscoveryRepository;

import java.util.List;

/**
 * Created by LONGYUAN on 2017/7/7.
 */

public class DiscoveryDetailPresenter implements DiscoveryDetailContract.Presenter{

    private DiscoveryDetailContract.View mDiscoveryDetailView;

    private DiscoveryRepository mDiscoveryRepository;

    private Discovery  mDiscovery ;

    private String  mDiscoveryId ;

    public DiscoveryDetailPresenter(String DiscoveryId,DiscoveryRepository discoveryRepository, DiscoveryDetailContract.View view){

        mDiscoveryId = DiscoveryId;

        mDiscoveryRepository = discoveryRepository;

        mDiscoveryDetailView = view;

        mDiscoveryDetailView.setPresenter(this);
    }

    @Override
    public void start() {

        mDiscoveryDetailView.displayDiscoveryDetail(loadDiscoveryDetail(mDiscoveryId));

    }

    @Override
    public Discovery loadDiscoveryDetail(String discoveryId)  {

        return  mDiscoveryRepository.getDiscovery(discoveryId);

    }

    @Override
    public void openComments() {

        mDiscoveryDetailView.displayComments(mDiscoveryId);
    }

    @Override
    public void addFavCount() {
        int count = mDiscoveryRepository.addFav(mDiscoveryId);

        mDiscoveryDetailView.updateFAvCount(count);
    }
}
