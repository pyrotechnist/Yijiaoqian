package com.longyuan.yijiaoqian.data;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.longyuan.yijiaoqian.utils.ApiAction;
import com.longyuan.yijiaoqian.utils.Category;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by loxu on 04/07/2017.
 */

public class PromotionsRepository {

    private static PromotionsRepository INSTANCE = null;

    private static Map<String,Promotion> mCachedPromotionsGood = new LinkedHashMap<>();

    private static Map<String,Promotion> mCachedPromotionsBetter = new LinkedHashMap<>();

    private static LoadDataCallback mLoadDataCallback;

    private ApiAction action;

    private Category mCategory;

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

        mLoadDataCallback = callback;

        mCategory = category;

        action = ApiAction.Find;

        if(!forceUpdate && !getPromotionsByCategory(category).isEmpty())
        {
            mLoadDataCallback.onTasksLoaded(new ArrayList<Promotion>(getPromotionsByCategory(category).values()));

        }else
        {
            OkHttpHandler okHttpHandler = new OkHttpHandler();

            okHttpHandler.execute("http://10.0.2.2:1337/promotion");
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

    public Promotion getPromotion(String promotionId) {

        Promotion promotion = null;

        for (Promotion element : mPromotions) {

            if (element.getId().equals(promotionId)) {
                element.setWatchedCount(element.getWatchedCount() + 1);
                return promotion = element;

            }
        }
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

                mCachedPromotionsGood.clear();

                mCachedPromotionsBetter.clear();

                for (Promotion promotion : promotions) {
                    if(promotion.getCategory() == Category.Better)
                    {
                        mCachedPromotionsBetter.put(promotion.getId(),promotion);
                    }else if (promotion.getCategory() == Category.Good)
                    {
                        mCachedPromotionsGood.put(promotion.getId(),promotion);

                    }
                }

                mLoadDataCallback.onTasksLoaded(new ArrayList<Promotion>(getPromotionsByCategory(mCategory).values()));

            } else if (action == ApiAction.Destroy) {
                Promotion promotion = gson.fromJson(s, Promotion.class);

                if (promotion != null) {
                    //mCachedPromotions.remove(promotion.getId());
                    getPromotionsByCategory(promotion.getCategory()).remove(promotion.getId());
                    mLoadDataCallback.onTasksLoaded(new ArrayList<Promotion>(getPromotionsByCategory(promotion.getCategory()).values()));
                }
            }
        }
    }


}
