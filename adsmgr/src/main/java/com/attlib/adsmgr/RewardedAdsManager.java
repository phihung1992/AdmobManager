package com.attlib.adsmgr;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedAdsManager extends BaseAdsManager {
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

    public void setListener(OnCallBack mListener) {
        this.mListener = mListener;
    }

    public void load() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(mContext, mAdId,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        logError(loadAdError.toString());
                        mRvAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        mRvAd = ad;
                        logDebug("Ad was loaded.");
                        if (mListener != null) mListener.onLoaded();
                    }
                });
    }

    public boolean IsLoaded() {
        return mRvAd != null;
    }

    public boolean show() {
        if (mRvAd != null) {
            mRvAd.show((Activity) mContext, rewardItem -> {
                // Handle the reward.
                logDebug("The user earned the reward.");
//                    int rewardAmount = rewardItem.getAmount();
//                    String rewardType = rewardItem.getType();
                if (mListener != null) mListener.onRewarded();
                mRvAd = null;
            });

            return true;
        } else {
            logError("The rewarded ad wasn't ready yet.");
            return false;
        }
    }

    public interface OnCallBack {
        void onLoaded();

        void onRewarded();
    }
}


