package com.longyuan.yijiaoqian.DiscoveryDetail;

import com.longyuan.yijiaoqian.data.Comment;
import com.longyuan.yijiaoqian.data.Discovery;

import java.util.List;

/**
 * Created by LONGYUAN on 2017/7/7.
 */

public class DiscoveryDetailContract {

    interface View{

        void setPresenter(Presenter presenter);

        void displayComments(int discoveryId);

        void displayDiscoveryDetail(Discovery discovery);

        void updateFAvCount(int favCount);

    }

    interface Presenter {

        void start();

        Discovery loadDiscoveryDetail(int discoveryId );

        void openComments();

        void addFavCount();

    }
}
