package com.attlib.adsmgr;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerAdsManager {
    private static BannerAdsManager instance;

    public enum BannerSize {
        BANNER,
        LARGE_BANNER,
        MEDIUM_RECTANGLE,
        FULL_BANNER,
        LEADERBOARD,
        SMART_BANNER
    }

    private BannerAdsManager() {

    }

    public static BannerAdsManager getInstance() {
        if (instance == null)
            instance = new BannerAdsManager();
        return instance;
    }

    public View createBanner(Context context, BannerSize size, String adUnitId) {
        AdView adView = new AdView(context);
        adView.setAdSize(getAdsize(size));
        adView.setAdUnitId(adUnitId);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        return adView;
    }

    private AdSize getAdsize(BannerSize size) {
        switch (size) {
            case BANNER:
                return AdSize.BANNER;
            case LARGE_BANNER:
                return AdSize.LARGE_BANNER;
            case MEDIUM_RECTANGLE:
                return AdSize.MEDIUM_RECTANGLE;
            case FULL_BANNER:
                return AdSize.FULL_BANNER;
            case LEADERBOARD:
                return AdSize.LEADERBOARD;
            case SMART_BANNER:
                return AdSize.SMART_BANNER;
            default:
                return AdSize.BANNER;
        }
    }
}
