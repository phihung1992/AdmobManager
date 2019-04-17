package com.attlib.adsmgr;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class InterstitialAdsManager {
    private static InterstitialAdsManager instance;
    private InterstitialAd mInterstitialAd;
    private Context mApplicationContext;

    private String mAdId;

    private InterstitialAdsManager() {

    }

    public static InterstitialAdsManager getInstance() {
        if (instance == null) {
            instance = new InterstitialAdsManager();
        }
        return instance;
    }

    public void init(Context applicationContext, String adId) {
        mApplicationContext = applicationContext;
        mAdId = adId;
    }

    public void load() {
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(mApplicationContext);
            mInterstitialAd.setAdUnitId(mAdId);
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
        }

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void show(int appearPercent) {
        if (new Random().nextInt(100) > appearPercent) {
            return;
        }

        if (mInterstitialAd == null || !mInterstitialAd.isLoaded()) {
            load();
        } else {
            mInterstitialAd.show();
        }
    }
}

