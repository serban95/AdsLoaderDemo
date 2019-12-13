package com.radudev.adsloaderdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appforever.mediatorads.AdMediator
import com.appforever.mediatorads.utils.*
import kotlinx.android.synthetic.main.activity_main.*

const val LOADED_MESSAGE        = "Loaded"
const val NOT_LOADED_MESSAGE    = "Not loaded"
const val REWARD_TITLE          = "Reward Amount: "

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init AdMob
        AdMediator.getInstance().setAdMobInitializeListener( object : AdMediator.AdMobInitializeListener{
            override fun onAdMobStatus(status: AdMobInitializeState) {
                when(status){
                    is AdMobInitializeState.OnAdMobLoaded -> {
                        loading_text.text = LOADED_MESSAGE

                        btn_load_ad_interstitial.isEnabled = true
                        btn_load_ad_rewarded.isEnabled = true

                        btn_load_ad_interstitial_ad_colony.isEnabled = true
                        btn_load_ad_rewarded_ad_colony.isEnabled = true
                    }
                    is AdMobInitializeState.OnAdMobFailedToLoad -> {
                        loading_text.text = NOT_LOADED_MESSAGE
                    }
                }
            }
        })
        AdMediator.getInstance().initAdMob(this)

        //Call Ads
        btn_load_ad_interstitial.setOnClickListener {
            AdMediator.getInstance().setAdMobInterstitialUnitId("ca-app-pub-6998935679750572/8509519952")
            AdMediator.getInstance().setAdMobInterstitialListener(object : AdMediator.AdMobInterstitialListener{
                override fun onAdMobStatus(status: AdMobInterstitialState) {
                    //Toast.makeText(this@MainActivity, status.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            AdMediator.getInstance().showAdMobInterstitial(this)
        }
        btn_load_ad_rewarded.setOnClickListener {
            AdMediator.getInstance().setAdMobRewardedUnitId("ca-app-pub-6998935679750572/6343834699")
            AdMediator.getInstance().setAdMobRewardedListener(object : AdMediator.AdMobRewardedListener{
                override fun onAdMobStatus(status: AdMobRewardedState, reward: Any?) {
                    //Toast.makeText(this@MainActivity, status.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            AdMediator.getInstance().showAdMobRewarded(this)
        }
        btn_load_ad_interstitial_ad_colony.setOnClickListener {
            AdMediator.getInstance().setAdColonyInterstitialCredentials(
                "app2dcb5ac53ef2475384",
                "vzf8e1500062db4fbe90",
                "vzf8e1500062db4fbe90")
            AdMediator.getInstance().setAdColonyInterstitialListener(object  : AdMediator.AdColonyInterstitialListener{
                override fun onAdColonyStatus(status: AdColonyInterstitialState) {
                    //Toast.makeText(this@MainActivity, status.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            AdMediator.getInstance().showAdColonyInterstitialAd(this)
        }
        btn_load_ad_rewarded_ad_colony.setOnClickListener {
            AdMediator.getInstance().setAdColonyRewardedCredentials(
                "app2dcb5ac53ef2475384",
                "vzf8e1500062db4fbe90",
                "vzf8e1500062db4fbe90")
            AdMediator.getInstance().setAdColonyRewardedListener(object  : AdMediator.AdColonyRewardedListener{
                override fun onAdColonyStatus(status: AdColonyRewardedState, rewardAmount : Int?) {
                    if(rewardAmount != null){
                        val text = REWARD_TITLE + rewardAmount
                        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            AdMediator.getInstance().showAdColonyRewardedAd(this)
        }
    }
}
