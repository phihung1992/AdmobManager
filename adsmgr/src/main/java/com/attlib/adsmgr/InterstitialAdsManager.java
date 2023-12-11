package com.attlib.adsmgr;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class InterstitialAdsManager extends BaseAdsManager {
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
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(mApplicationContext, mAdId, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            logDebug("onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            logError(loadAdError.toString());
                            mInterstitialAd = null;
                        }
                    });
        }
    }

    public boolean IsLoaded() {
        return mInterstitialAd != null;
    }

    public boolean show(Activity activity) {
        if (mInterstitialAd == null) {
            load();
            return false;
        } else {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    logDebug("Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    logDebug("Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    logError("Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    logDebug("Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    logDebug("Ad showed fullscreen content.");
                }
            });
            mInterstitialAd.show(activity);
            return true;
        }
    }
}

