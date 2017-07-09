package com.longyuan.yijiaoqian.DiscoveryDetail;

import com.longyuan.yijiaoqian.data.Discovery;

/**
 * Created by LONGYUAN on 2017/7/7.
 */

public class DiscoveryDetailContract {

    interface View{

        void setPresenter(Presenter presenter);

        void displayDiscoveryDetail(Discovery discovery);

    }

    interface Presenter {

        void start();

        Discovery loadDiscoveryDetail(int discoveryId );

    }
}
