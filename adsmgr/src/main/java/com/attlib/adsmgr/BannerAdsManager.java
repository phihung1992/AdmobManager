package com.attlib.adsmgr;

import android.content.Context;
import android.view.View;

import com.attlib.adsmgr.Constants.BannerSize;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerAdsManager {
    private static BannerAdsManager instance;

    private BannerAdsManager() {

    }

    public static BannerAdsManager getInstance() {
        if (instance == null)
            instance = new BannerAdsManager();
        return instance;
    }

    public View createBanner(Context context, BannerSize size, String adUnitId) {
        AdView adView = new AdView(context);
        adView.setAdSize(getAdSize(size));
        adView.setAdUnitId(adUnitId);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        return adView;
    }

    private AdSize getAdSize(BannerSize size) {
        switch (size) {
            case LARGE_BANNER:
                return AdSize.LARGE_BANNER;
            case MEDIUM_RECTANGLE:
                return AdSize.MEDIUM_RECTANGLE;
            case FULL_BANNER:
                return AdSize.FULL_BANNER;
            case LEADERBOARD:
                return AdSize.LEADERBOARD;
            default:
                return AdSize.BANNER;
        }
    }
}
