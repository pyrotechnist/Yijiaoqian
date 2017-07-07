package com.longyuan.yijiaoqian.data;

import com.longyuan.yijiaoqian.utils.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 06/07/2017.
 */

public class DiscoveryRepository {
    private static DiscoveryRepository INSTANCE = null;

    private static List<Discovery> mDiscoveries;

    static {
        mDiscoveries = new ArrayList<Discovery>();
        mDiscoveries.add(new Discovery(1,"Discovery 1","","SubTitle 1"));
        mDiscoveries.add(new Discovery(2,"Discovery 2","","SubTitle 2"));
        mDiscoveries.add(new Discovery(3,"Discovery 3","","SubTitle 3"));
        mDiscoveries.add(new Discovery(4,"Discovery 4","","SubTitle 4"));
        mDiscoveries.add(new Discovery(5,"Discovery 5","","SubTitle 5"));
        mDiscoveries.add(new Discovery(6,"Discovery 6","","SubTitle 6"));
    }

    private DiscoveryRepository() {
    }

    public static DiscoveryRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscoveryRepository();
        }
        return INSTANCE;
    }

    public List<Discovery> getDiscoveries() {

        return  mDiscoveries;

    }

    public Discovery getDiscovery(int DiscoveryId) {

        Discovery Discovery = null;

        for (Discovery element : mDiscoveries) {

            if (element.getId() == DiscoveryId) {
                element.setWatchedCount(element.getWatchedCount() + 1);
                return Discovery = element;

            }
        }
        return  Discovery;
    }

    public void addDiscovery(Discovery Discovery){

        mDiscoveries.add(Discovery);
    }
}
