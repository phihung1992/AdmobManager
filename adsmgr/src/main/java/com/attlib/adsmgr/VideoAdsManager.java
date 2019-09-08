package com.attlib.adsmgr;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class VideoAdsManager {
    private static VideoAdsManager instance;
    private RewardedVideoAd mRvGetCoins;
    private String mAdId;

    protected VideoAdsManager() {

    }

    public static VideoAdsManager getInstance() {
        if (instance == null)
            instance = new VideoAdsManager();
        return instance;
    }

    public void init(Activity activity, String adId, final OnCallBack listener) {
        if (mRvGetCoins == null) {
            mAdId = adId;
            mRvGetCoins = MobileAds.getRewardedVideoAdInstance(activity);
            mRvGetCoins.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                @Override
                public void onRewardedVideoAdLoaded() {
                    listener.onLoaded();
                }

                @Override
                public void onRewardedVideoAdOpened() {

                }

                @Override
                public void onRewardedVideoStarted() {

                }

                @Override
                public void onRewardedVideoAdClosed() {
                }

                @Override
                public void onRewarded(RewardItem rewardItem) {
                    listener.onRewarded();
                }

                @Override
                public void onRewardedVideoAdLeftApplication() {

                }

                @Override
                public void onRewardedVideoAdFailedToLoad(int i) {

                }

                @Override
                public void onRewardedVideoCompleted() {

                }
            });
        }
    }


    public void load() {
        mRvGetCoins.loadAd(mAdId, new AdRequest.Builder().build());
    }

    public boolean isLoaded() {
        return mRvGetCoins.isLoaded();
    }

    public void show() {
        mRvGetCoins.show();
    }

    public void resume(Context context) {
        mRvGetCoins.resume(context);
    }

    public void pause(Context context) {
        mRvGetCoins.pause(context);
    }

    public void destroy(Context context) {
        mRvGetCoins.destroy(context);
    }

    public interface OnCallBack {
        void onLoaded();
        void onRewarded();
    }
}

