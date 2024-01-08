package com.attlib.admobmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.attlib.adsmgr.BannerAdsManager;
import com.attlib.adsmgr.Constants;
import com.attlib.adsmgr.InterstitialAdsManager;
import com.attlib.adsmgr.RewardedAdsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use Interstitial Ads
        InterstitialAdsManager.getInstance().init(this, Constants.TEST_INTERSTITIAL_AD_ID);
        InterstitialAdsManager.getInstance().load();

        findViewById(R.id.full_screen_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterstitialAdsManager.getInstance().show(MainActivity.this);
            }
        });

        // Reward  Video Ads
        RewardedAdsManager.getInstance().init(this, Constants.TEST_REWARDED_VIDEO_AD_ID, new RewardedAdsManager.OnCallBack() {
            @Override
            public void onLoaded() {
                //Do something when ad loaded.
            }

            @Override
            public void onRewarded() {
                //Do something when ad is watched completely.
            }
        });
        RewardedAdsManager.getInstance().load();

        findViewById(R.id.reward_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewardedAdsManager.getInstance().show();
            }
        });

        // Banner Ads
        View bannerAd = BannerAdsManager.getInstance().createBanner(this, Constants.BannerSize.BANNER, Constants.TEST_BANNER_AD_ID);
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.addView(bannerAd);
    }
}
