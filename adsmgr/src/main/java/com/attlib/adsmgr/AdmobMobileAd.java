package com.attlib.adsmgr;

import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class AdmobMobileAd {
    public static ArrayList<String> mTestDeviceIds = new ArrayList<>();

    public static void AddTestDeviceId(String deviceId) {
        mTestDeviceIds.add(deviceId);
    }

    public static void init(Context context) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                RequestConfiguration configuration =
                        new RequestConfiguration.Builder().setTestDeviceIds(mTestDeviceIds).build();
                MobileAds.setRequestConfiguration(configuration);
            }
        });
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(mTestDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);
    }
}
