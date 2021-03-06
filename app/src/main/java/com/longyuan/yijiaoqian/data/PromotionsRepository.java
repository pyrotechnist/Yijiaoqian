package com.longyuan.yijiaoqian.data;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.longyuan.yijiaoqian.utils.ApiAction;
import com.longyuan.yijiaoqian.utils.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java8.util.stream.StreamSupport;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsRepository {

    private static PromotionsRepository INSTANCE = null;

    private static Map<String,Promotion> mCachedPromotionsGood = new LinkedHashMap<>();

    private static Map<String,Promotion> mCachedPromotionsBetter = new LinkedHashMap<>();

    private static LoadDataCallback mLoadDataCallbackGood;

    private static LoadDataCallback mLoadDataCallbackBetter;

    private static Promotion promotion;

    private static PromotionAPI mPromotionAPI;

    private ApiAction action;

    private static  List<Promotion> mPromotions;

    static {
        mPromotions = new ArrayList<Promotion>();
        mPromotions.add(new Promotion("1","Better 1","", Category.Better));
        mPromotions.add(new Promotion("2","Good 1","",Category.Good));
        mPromotions.add(new Promotion("3","Good 2","",Category.Good));
        mPromotions.add(new Promotion("4","Good 3","",Category.Good));
        mPromotions.add(new Promotion("5","Good 4","",Category.Good));
        mPromotions.add(new Promotion("6","Better 2","",Category.Better));
    }

    private PromotionsRepository() {
    }

    public static PromotionsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PromotionsRepository();
        }
        return INSTANCE;
    }

    public void getPromotions(Category category,LoadDataCallback callback) {
        getPromotions(category,callback,false);

    }

    public void getPromotions(Category category,LoadDataCallback callback, boolean forceUpdate) {

        if (mPromotionAPI == null) {

            createPromotionsAPI();
        }


        if(category == Category.Better)
        {
            mLoadDataCallbackBetter = callback;
        }else
        {
            mLoadDataCallbackGood= callback;
        }


        action = ApiAction.Find;

        if(!forceUpdate && !getPromotionsByCategory(category).isEmpty())
        {
            getCallbackByCategory(category).onTasksLoaded(new ArrayList<Promotion>(getPromotionsByCategory(category).values()));

        }else
        {
            //OkHttpHandler okHttpHandler = new OkHttpHandler();

            //okHttpHandler.execute("http://10.0.2.2:1337/promotion?category="+category.toString());

            mPromotionAPI.getPromotions()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myData -> {

                        StreamSupport.stream(myData).filter(s-> s.getCategory() ==category).forEach(item -> getPromotionsByCategory(category).put(item.getId(),item));
                        callback.onTasksLoaded(new ArrayList<Promotion>(getPromotionsByCategory(category).values()));
                    }, throwable -> {
                        // handle error event
                        Log.e("","test",throwable);
                    });
        }

        //List<Promotion> promotions = new ArrayList<Promotion>();

       /* List<Promotion> promotionsAfterFilter = new ArrayList<Promotion>();



        for (Promotion element : mPromotions) {

            if(element.getCategory() == category)
            {
                promotionsAfterFilter.add(element);

            }
        }*/

    }

    public Promotion getPromotion(String promotionId,Category category) {

        Promotion promotion = null;

      /*  for (Promotion element : mPromotions) {

            if (element.getId().equals(promotionId)) {
                element.setWatchedCount(element.getWatchedCount() + 1);
                return promotion = element;

            }
        }*/




        return  promotion;
    }

    public void addPromotion(Promotion promotion){

        mPromotions.add(promotion);
    }

    public int clickPromotion(String promotionId){

        Promotion promotion = null;

        for (Promotion element : mPromotions) {

            if (element.getId().equals(promotionId)) {

                element.setWatchedCount(element.getWatchedCount() + 1);
                promotion = element;

            }
        }
        return  promotion.getWatchedCount();

    }

    private Map<String,Promotion> getPromotionsByCategory(Category category){

        if(category == Category.Better)
        {
            return mCachedPromotionsBetter;
        }else if(category == Category.Good)
        {
            return mCachedPromotionsGood;
        }

        return null;

    }

    private LoadDataCallback getCallbackByCategory(Category category){

        if(category == Category.Better)
        {
            return mLoadDataCallbackBetter;
        }else if(category == Category.Good)
        {
            return mLoadDataCallbackGood;
        }

        return null;

    }


    private void createPromotionsAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PromotionAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mPromotionAPI = retrofit.create(PromotionAPI.class);*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PromotionAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        mPromotionAPI = retrofit.create(PromotionAPI.class);
    }



    /**
     *
     */
    public class OkHttpHandler extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mMainFragment.showProgressDialog();
        }

        @Override
        protected String doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();
            OkHttpClient client = new OkHttpClient();
            try {

                Response response = client.newCall(request).execute();
                ResponseBody body = response.body();

                String dd = response.body().string();


                return dd;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

       /* protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mMainFragment.updateDialog(Integer.parseInt(progress[0]));
        }
*/

        @Override
        protected void onPostExecute(String s) {
            String promotionJson = s;

            Gson gson = new GsonBuilder().create();

            if (action == ApiAction.Find) {

                List<Promotion> promotions = gson.fromJson(s, new TypeToken<List<Promotion>>() {
                }.getType());


                if(!promotions.isEmpty())
                {
                    Promotion promotion = promotions.get(0);

                    if(promotion.getCategory() == Category.Better)
                    {
                        mCachedPromotionsBetter.clear();

                        for (Promotion item : promotions) {
                            mCachedPromotionsBetter.put(item.getId(),item);
                        }
                        mLoadDataCallbackBetter.onTasksLoaded(new ArrayList<Promotion>(mCachedPromotionsBetter.values()));

                    }else if (promotion.getCategory() == Category.Good)
                    {
                        mCachedPromotionsGood.clear();

                        for (Promotion item : promotions) {
                            mCachedPromotionsGood.put(item.getId(),item);
                        }
                        mLoadDataCallbackGood.onTasksLoaded(new ArrayList<Promotion>(mCachedPromotionsGood.values()));
                    }
                }

            } else if (action == ApiAction.Destroy) {
                Promotion promotion = gson.fromJson(s, Promotion.class);

                if (promotion != null) {
                    //mCachedPromotions.remove(promotion.getId());
                    Category category = promotion.getCategory();

                    getPromotionsByCategory(category).remove(promotion.getId());
                    getCallbackByCategory(category).onTasksLoaded(new ArrayList<Promotion>(getPromotionsByCategory(category).values()));
                }
            }
        }
    }


}
