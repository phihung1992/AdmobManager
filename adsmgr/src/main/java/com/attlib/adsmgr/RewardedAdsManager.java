package com.attlib.adsmgr;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedAdsManager {
    private Context mContext;
    private static RewardedAdsManager instance;
    private RewardedAd mRvAd;
    private String mAdId;

    private OnCallBack mListener;

    protected RewardedAdsManager() {

    }

    public static RewardedAdsManager getInstance() {
        if (instance == null)
            instance = new RewardedAdsManager();
        return instance;
    }

    public void init(Context context, String adId, final OnCallBack listener) {
        mContext = context;
        mAdId = adId;
        mListener = listener;
    }


    public void load() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(mContext, mAdId,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(Constants.ADMOB_MANAGER_LOG, loadAdError.toString());
                        mRvAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        mRvAd = ad;
                        Log.d(Constants.ADMOB_MANAGER_LOG, "Ad was loaded.");
                        if (mListener != null) mListener.onLoaded();
                    }
                });
    }

    public void show() {
        if (mRvAd != null) {
            mRvAd.show((Activity) mContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d(Constants.ADMOB_MANAGER_LOG, "The user earned the reward.");
//                    int rewardAmount = rewardItem.getAmount();
//                    String rewardType = rewardItem.getType();
                    if (mListener != null) mListener.onRewarded();
                }
            });
        } else {
            Log.d(Constants.ADMOB_MANAGER_LOG, "The rewarded ad wasn't ready yet.");
            load();
        }

    }

    public interface OnCallBack {
        void onLoaded();

        void onRewarded();
    }
}
