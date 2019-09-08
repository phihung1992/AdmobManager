package com.attlib.admobmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.attlib.adsmgr.BannerAdsManager;
import com.attlib.adsmgr.InterstitialAdsManager;
import com.attlib.adsmgr.VideoAdsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use Intertitial Ads
        InterstitialAdsManager.getInstance().init(getApplicationContext(), "ca-app-pub-3940256099942544/1033173712");
        InterstitialAdsManager.getInstance().load();

        findViewById(R.id.full_screen_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterstitialAdsManager.getInstance().show(100);
            }
        });

        // Reward  Video Ads
        VideoAdsManager.getInstance().init(this, "ca-app-pub-3940256099942544/5224354917", new VideoAdsManager.OnCallBack() {
            @Override
            public void onLoaded() {
                Toast.makeText(MainActivity.this, "Reward video is loaded!", Toast.LENGTH_SHORT).show();

                // Show when load done
                VideoAdsManager.getInstance().show();
            }

            @Override
            public void onRewarded() {
                Toast.makeText(MainActivity.this, "Reward video is onRewarded!", Toast.LENGTH_SHORT).show();
                // TODO: Somethings when user watch video done
            }
        });

        findViewById(R.id.reward_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VideoAdsManager.getInstance().isLoaded()) {
                    VideoAdsManager.getInstance().show();
                } else {
                    VideoAdsManager.getInstance().load();
//                    showLoading();
                }
            }
        });

        // Banner Ads
        View bannerAd = BannerAdsManager.getInstance()
                .createBanner(this, BannerAdsManager.BannerSize.BANNER, "ca-app-pub-3940256099942544/6300978111");
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.addView(bannerAd);
    }
}
