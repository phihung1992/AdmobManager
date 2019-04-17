# AdmobManager
An easy way to show Admob advertisement

Gradle
------------


###### Project Gradle
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

###### Module Gradle
```groovy
dependencies {
	implementation 'com.github.phihung1992:AdmobManager:1.0'
}
```

Usage
--------

###### Add App Id to Manifest

```groovy
<application
     ...
     <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-3940256099942544~3347511713" />
</application>
```

###### Permissions

```groovy
<uses-permission android:name="android.permission.INTERNET" />
```

###### Interstitial

```xml
InterstitialAdsManager.getInstance().init(getApplicationContext(), "ca-app-pub-3940256099942544/1033173712");
        InterstitialAdsManager.getInstance().load();

        findViewById(R.id.full_screen_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterstitialAdsManager.getInstance().show(100);
            }
        });
```


###### Reward Video

```xml
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
```
```java
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
```

###### Banner

```xml
View bannerAd = BannerAdsManager.getInstance()
                .createBanner(this, BannerAdsManager.BannerSize.BANNER, "ca-app-pub-3940256099942544/6300978111");
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.addView(bannerAd);
```

