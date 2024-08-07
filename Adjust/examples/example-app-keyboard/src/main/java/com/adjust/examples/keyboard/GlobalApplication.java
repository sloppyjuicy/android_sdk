package com.adjust.examples.keyboard;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEventFailure;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.AdjustSessionFailure;
import com.adjust.sdk.AdjustSessionSuccess;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.adjust.sdk.OnDeferredDeeplinkResponseListener;
import com.adjust.sdk.OnEventTrackingFailedListener;
import com.adjust.sdk.OnEventTrackingSucceededListener;
import com.adjust.sdk.OnSessionTrackingFailedListener;
import com.adjust.sdk.OnSessionTrackingSucceededListener;

/**
 * Created by pfms on 17/12/14.
 */
public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Configure adjust SDK.
        String appToken = "2fm9gkqubvpc";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;

        AdjustConfig config = new AdjustConfig(this, appToken, environment);

        // Change the log level.
        config.setLogLevel(LogLevel.VERBOSE);

        // Set attribution delegate.
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            @Override
            public void onAttributionChanged(AdjustAttribution attribution) {
                Log.d("example", "Attribution callback called!");
                Log.d("example", "Attribution: " + attribution.toString());
            }
        });

        // Set event success tracking delegate.
        config.setOnEventTrackingSucceededListener(new OnEventTrackingSucceededListener() {
            @Override
            public void onEventTrackingSucceeded(AdjustEventSuccess eventSuccessResponseData) {
                Log.d("example", "Event success callback called!");
                Log.d("example", "Event success data: " + eventSuccessResponseData.toString());
            }
        });

        // Set event failure tracking delegate.
        config.setOnEventTrackingFailedListener(new OnEventTrackingFailedListener() {
            @Override
            public void onEventTrackingFailed(AdjustEventFailure eventFailureResponseData) {
                Log.d("example", "Event failure callback called!");
                Log.d("example", "Event failure data: " + eventFailureResponseData.toString());
            }
        });

        // Set session success tracking delegate.
        config.setOnSessionTrackingSucceededListener(new OnSessionTrackingSucceededListener() {
            @Override
            public void onSessionTrackingSucceeded(AdjustSessionSuccess sessionSuccessResponseData) {
                Log.d("example", "Session success callback called!");
                Log.d("example", "Session success data: " + sessionSuccessResponseData.toString());
            }
        });

        // Set session failure tracking delegate.
        config.setOnSessionTrackingFailedListener(new OnSessionTrackingFailedListener() {
            @Override
            public void onSessionTrackingFailed(AdjustSessionFailure sessionFailureResponseData) {
                Log.d("example", "Session failure callback called!");
                Log.d("example", "Session failure data: " + sessionFailureResponseData.toString());
            }
        });

        // Evaluate deferred deeplink to be launched.
        config.setOnDeferredDeeplinkResponseListener(new OnDeferredDeeplinkResponseListener() {
            @Override
            public boolean launchReceivedDeeplink(Uri deeplink) {
                Log.d("example", "Deferred deeplink callback called!");
                Log.d("example", "Deeplink URL: " + deeplink);

                return true;
            }
        });

        // Set default tracker.
        // config.setDefaultTracker("{YourDefaultTracker}");

        // Set process name.
        // config.setProcessName("com.adjust.examples");

        // Allow to send in the background.
        config.enableSendingInBackground();

        // Add session callback parameters.
        Adjust.addGlobalCallbackParameter("sc_foo", "sc_bar");
        Adjust.addGlobalCallbackParameter("sc_key", "sc_value");

        // Add global partner parameters.
        Adjust.addGlobalPartnerParameter("sp_foo", "sp_bar");
        Adjust.addGlobalPartnerParameter("sp_key", "sp_value");

        // Remove session callback parameters.
        Adjust.removeGlobalCallbackParameter("sc_foo");

        // Remove global partner parameters.
        Adjust.removeGlobalPartnerParameter("sp_key");

        // Remove all session callback parameters.
        Adjust.removeGlobalCallbackParameters();

        // Remove all global partner parameters.
        Adjust.removeGlobalPartnerParameters();

        // Enable IMEI reading ONLY IF:
        // - IMEI plugin is added to your app.
        // - Your app is NOT distributed in Google Play Store.
        // AdjustImei.readImei();

        // Enable OAID reading ONLY IF:
        // - OAID plugin is added to your app.
        // - Your app is NOT distributed in Google Play Store & supports OAID.
        // AdjustOaid.readOaid();

        // Initialise the adjust SDK.
        Adjust.initSdk(config);

        // Put the SDK in offline mode.
        // Adjust.switchToOfflineMode();

        // Disable the SDK
        // Adjust.disable();

        // Send push notification token.
        // Adjust.setPushToken("token");
    }
}
