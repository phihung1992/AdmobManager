package com.attlib.admobmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.attlib.adsmgr.AdmobMobileAd;
import com.attlib.adsmgr.BannerAdsManager;
import com.attlib.adsmgr.Constants;
import com.attlib.adsmgr.InterstitialAdsManager;
import com.attlib.adsmgr.RewardedAdsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdmobMobileAd.AddTestDeviceId("BC0BE802F09BB8D72B45C6984F40239B");
        AdmobMobileAd.AddTestDeviceId("6B9FB54823BD16F4580CA328FF072BF1");
        AdmobMobileAd.init(this);

        // Use Interstitial Ads
        InterstitialAdsManager.getInstance().init(this, Constants.TEST_INTERSTITIAL_AD_ID);
        InterstitialAdsManager.getInstance().load();

        findViewById(R.id.full_screen_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterstitialAdsManager.getInstance().show(MainActivity.this, 100);
            }
        });

        // Reward  Video Ads
//        RewardedAdsManager.getInstance().init(this, "ca-app-pub-3940256099942544/5224354917", new RewardedAdsManager.OnCallBack() {
//            @Override
//            public void onLoaded() {
//                Toast.makeText(MainActivity.this, "Reward video is loaded!", Toast.LENGTH_SHORT).show();
//
//                // Show when load done
//                RewardedAdsManager.getInstance().show();
//            }
//
//            @Override
//            public void onRewarded() {
//                Toast.makeText(MainActivity.this, "Reward video is onRewarded!", Toast.LENGTH_SHORT).show();
//                // TODO: Somethings when user watch video done
//            }
//        });

//        findViewById(R.id.reward_ad).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (RewardedAdsManager.getInstance().isLoaded()) {
//                    RewardedAdsManager.getInstance().show();
//                } else {
//                    RewardedAdsManager.getInstance().load();
////                    showLoading();
//                }
//            }
//        });

        // Banner Ads
        View bannerAd = BannerAdsManager.getInstance()
                .createBanner(this, Constants.BannerSize.BANNER, Constants.TEST_BANNER_AD_ID);
        LinearLayoutCompat lnMain = findViewById(R.id.ln_main);
        lnMain.addView(bannerAd);
    }
}
