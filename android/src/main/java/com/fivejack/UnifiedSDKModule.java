package com.fivejack;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import zendesk.core.Zendesk;
import zendesk.core.AnonymousIdentity;
import zendesk.core.Identity;
import zendesk.chat.Chat;
import zendesk.chat.ChatEngine;
import zendesk.messaging.Engine;
import zendesk.support.Support;
import zendesk.support.SupportEngine;
import zendesk.answerbot.AnswerBot;
import zendesk.answerbot.AnswerBotEngine;
import zendesk.messaging.MessagingActivity;
import zendesk.support.guide.HelpCenterActivity;
import zendesk.support.request.RequestActivity;
import zendesk.support.requestlist.RequestListActivity;
import zendesk.messaging.MessagingConfiguration;
import com.zendesk.logger.Logger;

public class UnifiedSDKModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public UnifiedSDKModule(ReactApplicationContext reactContext) {
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
    public void setAnonymousIdentity(String nameIdentifier, String emailIdentifier) {        
        Identity identity = new AnonymousIdentity.Builder()
        .withNameIdentifier(nameIdentifier)
        .withEmailIdentifier(emailIdentifier)
        .build();

        Zendesk.INSTANCE.setIdentity(identity);
    }

    @ReactMethod
    public void showMessagingActivity(boolean useAnswerBot) {        
        if(!useAnswerBot) {
            MessagingActivity.builder()
            .withEngines(SupportEngine.engine())
            .show(this.reactContext.getCurrentActivity());
        }
        else {
            MessagingActivity.builder()
            .withEngines(AnswerBotEngine.engine(), SupportEngine.engine())
            .show(this.reactContext.getCurrentActivity());
        }
    }

    @ReactMethod
    public void showMessagingActivity() {
        this.showMessagingActivity(false);
    }

    @ReactMethod
    public void showCreateTicket(String requestSubject) {
        RequestActivity.builder()
        .withRequestSubject(requestSubject)
        .show(this.reactContext.getCurrentActivity());    
    }

    @ReactMethod
    public void showTicketList() {
        RequestListActivity.builder()
        .show(this.reactContext.getCurrentActivity());
    }

    @ReactMethod
    public void initSDK(String zendeskUrl, String appId, String clientId) {       
        Zendesk.INSTANCE.init(this.reactContext, zendeskUrl, appId, clientId);
        Support.INSTANCE.init(Zendesk.INSTANCE);
        AnswerBot.INSTANCE.init(Zendesk.INSTANCE, Support.INSTANCE);        
    }    


}
