# AdsLoaderDemo

1. Locate the **.arr** file called **mediatorads.arr**

2. Integrate to **Android Studio**
- File -> New -> New Module
- Select from Wizard **Import .JAR/ .AAR Package** and then **Next**
- Search the file from **Folder Location**
- Put a **Subproject Name** like **mediatorads**

3. Go to **Gradle Scripts** -> **build.gradle(Module:app)**
- Add this dependencies and Update Gradle:

```
  implementation 'com.google.android.gms:play-services-ads:18.3.0'
  implementation project(':mediatorads')
```

4. Open **AndroidManifest.xml** from **App**
- Give internet permission
```
   <uses-permission android:name="android.permission.INTERNET"/>
```

- Inside **<application...** node, add the **AdMob** publisher Id (from AdMob console) this is an example one.
```
   <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-6998935679750572~5222324713" />
 ```
 
5. Using the **Library Classes**
- Initialize AdMob (AdColony not necessary) just one per App instance
```
  AdMediator.getInstance().setAdMobInitializeListener( object : AdMediator.AdMobInitializeListener{
            override fun onAdMobStatus(status: AdMobInitializeState) {
                when(status){
                    is AdMobInitializeState.OnAdMobLoaded -> {
                        loading_text.text = LOADED_MESSAGE
                    }
                    is AdMobInitializeState.OnAdMobFailedToLoad -> {
                        loading_text.text = NOT_LOADED_MESSAGE
                    }
                }
            }
        })
        AdMediator.getInstance().initAdMob(this)
```

- In Some **Activity** or **Fragment** you want to show **Ads**

 ```
        AdMediator.getInstance().setAdMobInterstitialUnitId("ca-app-pub-6998935679750572/8509519952")
        
        AdMediator.getInstance().setAdMobInterstitialListener(object : AdMediator.AdMobInterstitialListener{
            override fun onAdMobStatus(status: AdMobInterstitialState) {
                //Do something
            }
        })
        
        AdMediator.getInstance().showAdMobInterstitial(this)
 ```
**In the Example Above**

1. Set UnitId
2. AddListener
3. Show Ad
