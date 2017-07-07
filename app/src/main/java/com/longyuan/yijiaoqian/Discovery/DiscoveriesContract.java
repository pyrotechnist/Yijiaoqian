package com.longyuan.yijiaoqian.Discovery;

import com.longyuan.yijiaoqian.PromotionDetail.PromotionDetailContract;
import com.longyuan.yijiaoqian.data.Discovery;

import java.util.List;

/**
 * Created by loxu on 07/07/2017.
 */

public class DiscoveriesContract {

    interface View {

        void setPresenter(DiscoveriesContract.Presenter presenter);

        void displayDiscoveries(List<Discovery> discoveryList);
    }

    interface Presenter{

        void start();

         List<Discovery> loadDiscoveries();
    }
}
