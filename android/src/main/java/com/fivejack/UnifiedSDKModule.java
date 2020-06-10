package com.fivejack;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

import zendesk.core.Zendesk;
import zendesk.core.AnonymousIdentity;
import zendesk.core.Identity;
import zendesk.chat.Chat;
import zendesk.chat.ChatEngine;
import zendesk.messaging.Engine;
import zendesk.configurations.Configuration;
import zendesk.support.Support;
import zendesk.support.SupportEngine;
import zendesk.answerbot.AnswerBot;
import zendesk.answerbot.AnswerBotEngine;
import zendesk.messaging.MessagingActivity;
import zendesk.support.guide.HelpCenterActivity;
import zendesk.support.guide.HelpCenterConfiguration;
import zendesk.support.guide.ViewArticleActivity;
import zendesk.support.request.RequestConfiguration;
import zendesk.support.request.RequestActivity;
import zendesk.support.requestlist.RequestListActivity;
import zendesk.messaging.MessagingConfiguration;
import com.zendesk.logger.Logger;
import zendesk.support.CustomField;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.json.JSONException;

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
        Identity identity = new AnonymousIdentity.Builder().withNameIdentifier(nameIdentifier)
                .withEmailIdentifier(emailIdentifier).build();

        Zendesk.INSTANCE.setIdentity(identity);
    }

    @ReactMethod
    public void showHelpCenterActivity(ReadableMap configurationObject) {
        try {
            Configuration articleConfig = ViewArticleActivity.builder().withContactUsButtonVisible(true).config();

            String configurationObjectJSON = JSONHelper.convertMapToJson(configurationObject).toString();
            HelpConfiguration helpConfiguration = JSONHelper.createDefaultGson().fromJson(configurationObjectJSON,
                    HelpConfiguration.class);

            HelpCenterConfiguration.Builder builder = HelpCenterActivity.builder();

            if (helpConfiguration.isContactUsVisible != null) {
                builder.withContactUsButtonVisible(helpConfiguration.isContactUsVisible);
                if (!helpConfiguration.isContactUsVisible) {
                    articleConfig = ViewArticleActivity.builder().withContactUsButtonVisible(false).config();
                }
            }

            if (helpConfiguration.showConversationButton != null) {
                builder.withShowConversationsMenuButton(helpConfiguration.showConversationButton);
            }

            if (helpConfiguration.labels != null && helpConfiguration.labels.length > 0) {
                List<String> labelsList = new ArrayList();
                for (String label : helpConfiguration.labels) {
                    labelsList.add(label);
                }
                builder.withLabelNames(labelsList);
            }

            if (helpConfiguration.categoryIds != null && helpConfiguration.categoryIds.length > 0) {
                List<Long> categoryIdsList = new ArrayList();
                for (Long id : helpConfiguration.categoryIds) {
                    categoryIdsList.add(id);
                }
                builder.withArticlesForCategoryIds(categoryIdsList);
            }

            if (helpConfiguration.sectionIds != null && helpConfiguration.sectionIds.length > 0) {
                List<Long> sectionIdsList = new ArrayList();
                for (Long id : helpConfiguration.sectionIds) {
                    sectionIdsList.add(id);
                }
                builder.withArticlesForSectionIds(sectionIdsList);
            }

            HelpCenterActivity.builder().withContactUsButtonVisible(false).withShowConversationsMenuButton(false)
                    .show(this.reactContext.getCurrentActivity(), articleConfig);

        } catch (JSONException e) {
            e.getMessage();
        }

    }

    @ReactMethod
    public void showMessagingActivity(boolean useAnswerBot) {
        if (!useAnswerBot) {
            MessagingActivity.builder().withEngines(SupportEngine.engine())
                    .show(this.reactContext.getCurrentActivity());
        } else {
            MessagingActivity.builder().withEngines(AnswerBotEngine.engine(), SupportEngine.engine())
                    .show(this.reactContext.getCurrentActivity());
        }
    }

    @ReactMethod
    public void showMessagingActivity() {
        this.showMessagingActivity(false);
    }

    @ReactMethod
    public void showCreateTicket(ReadableMap configurationObject) {
        try {
            String configurationObjectJSON = JSONHelper.convertMapToJson(configurationObject).toString();
            TicketConfiguration ticketConfiguration = JSONHelper.createDefaultGson().fromJson(configurationObjectJSON,
                    TicketConfiguration.class);

            RequestConfiguration.Builder builder = RequestActivity.builder();

            if (ticketConfiguration.subject != "") {
                builder.withRequestSubject(ticketConfiguration.subject);
            }

            if (ticketConfiguration.tags != null && ticketConfiguration.tags.length > 0) {
                List<String> tagsList = new ArrayList();
                for (String tag : ticketConfiguration.tags) {
                    tagsList.add(tag);
                }
                builder.withTags(tagsList);
            }

            if (ticketConfiguration.fields != null && ticketConfiguration.fields.length > 0) {
                List<CustomField> customFieldsList = new ArrayList();
                for (CustomFieldRN field : ticketConfiguration.fields) {
                    customFieldsList.add(new CustomField(field.id, field.value));
                }

                builder.withCustomFields(customFieldsList);
            }

            // .withRequestSubject(requestSubject)
            builder.show(this.reactContext.getCurrentActivity());
        } catch (JSONException e) {
            e.getMessage();
        }
    }

    @ReactMethod
    public void showTicketList() {
        Configuration config = HelpCenterActivity.builder().withContactUsButtonVisible(false)
                .withShowConversationsMenuButton(false).config();

        RequestListActivity.builder().show(this.reactContext.getCurrentActivity(), config);
    }

    @ReactMethod
    public void initSDK(String zendeskUrl, String appId, String clientId) {
        Zendesk.INSTANCE.init(this.reactContext, zendeskUrl, appId, clientId);
        Support.INSTANCE.init(Zendesk.INSTANCE);
        AnswerBot.INSTANCE.init(Zendesk.INSTANCE, Support.INSTANCE);
    }

    private class TicketConfiguration {
        String subject;
        String[] tags;
        CustomFieldRN[] fields;
    }

    private class HelpConfiguration {
        Boolean isContactUsVisible = false;
        Boolean showConversationButton = false;
        String[] labels;
        Long[] categoryIds;
        Long[] sectionIds;
    }

    private class CustomFieldRN {
        Long id;
        Object value;
    }

}
