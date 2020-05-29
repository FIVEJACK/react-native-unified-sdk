package com.fivejack;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import zendesk.core.Zendesk;
import zendesk.support.Support;

public class UnifiedSDKModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public UnifiedSdkModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "UnifiedSDK";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void initSDK(String zendeskUrl, String appId, String clientId) {
        Zendesk.INSTANCE.init(this.reactContext, zendeskUrl, appId, clientId);
        Support.INSTANCE.init(Zendesk.INSTANCE);
    }
}
