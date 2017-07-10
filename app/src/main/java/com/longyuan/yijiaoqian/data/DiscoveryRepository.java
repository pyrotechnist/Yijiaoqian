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
        Discovery discovery = new Discovery(1,"Discovery 1","","SubTitle 1");

        discovery.addComment(new Comment("TestContent","XU"));

        discovery.addComment(new Comment("TestContent","Yang"));

        discovery.addComment(new Comment("TestContent","Deng"));

        mDiscoveries.add(discovery);

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

    public Discovery getDiscovery(int DiscoveryId, boolean addWatched) {

        Discovery Discovery = null;

        for (Discovery element : mDiscoveries) {

            if (element.getId() == DiscoveryId) {
                if(addWatched) {
                    element.setWatchedCount(element.getWatchedCount() + 1);
                }
                return Discovery = element;

            }
        }
        return  Discovery;
    }

    public Discovery getDiscovery(int DiscoveryId) {

        return  getDiscovery(DiscoveryId,true);
    }

    public int addFav(int DiscoveryId){

        for (Discovery element : mDiscoveries) {

            if (element.getId() == DiscoveryId) {

                element.setFavCount(element.getFavCount() + 1);

                return element.getFavCount();
            }
        }
        return  0;
    }


    public List<Comment> getComments(int DiscoveryId) {

        List<Comment> commentList = new ArrayList<Comment>();

        for (Discovery element : mDiscoveries) {

            if (element.getId() == DiscoveryId) {
                return element.getComments();

            }
        }
        return  commentList;
    }

    public void addDiscovery(Discovery Discovery){

        mDiscoveries.add(Discovery);
    }
}
