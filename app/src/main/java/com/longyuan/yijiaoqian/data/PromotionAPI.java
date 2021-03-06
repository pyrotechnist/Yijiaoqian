package com.longyuan.yijiaoqian.data;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by LONGYUAN on 2017/8/3.
 */

public interface PromotionAPI {

    String BASE_URL = "http://10.0.2.2:1337";

    @GET("/promotion")
    Single<List<Promotion>> getPromotions();

    @GET("/promotion/destroy/id")
    Single<Promotion> deletePromotion(@Path("id") String id);
}
