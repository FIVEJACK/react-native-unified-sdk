package com.fivejack;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.fivejack.JSONHelper;

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
import zendesk.support.CustomField;

import java.util.Arrays;
import java.util.ArrayList;

import org.json.JSONException;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import zendesk.support.HelpCenterProvider;
import zendesk.support.Support;
import com.zendesk.service.ZendeskCallback;
import zendesk.support.Category;
import zendesk.support.Section;
import zendesk.support.Article;
import zendesk.support.ArticleVote;
import zendesk.support.SuggestedArticleResponse;
import zendesk.support.SimpleArticle;
import zendesk.support.SuggestedArticleSearch;
import com.zendesk.service.ErrorResponse;
import com.facebook.react.bridge.Promise;

public class HelpCenterProviderSDKModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private HelpCenterProvider provider;

    public HelpCenterProviderSDKModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "HelpCenterProvider";
    }

    @ReactMethod
    public void init() {
        this.provider = Support.INSTANCE.provider().helpCenterProvider();
    }

    @ReactMethod
    public void getAllCategoriesPromise(final Promise promise) {
        this.provider.getCategories(new ZendeskCallback<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {
                try {
                    promise.resolve(JSONHelper
                            .convertJsonToArray(new JSONArray(JSONHelper.createDefaultGson().toJson(categories))));
                } catch (JSONException e) {
                    promise.reject("", e.getMessage());

                }

            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                promise.reject("", errorResponse.getReason());
            }
        });
    }

    @ReactMethod
    public void getSectionsPromise(Double categoryId, final Promise promise) {
        Long catId = categoryId.longValue();
        this.provider.getSections(catId, new ZendeskCallback<List<Section>>() {
            @Override
            public void onSuccess(List<Section> sections) {
                try {
                    promise.resolve(JSONHelper
                            .convertJsonToArray(new JSONArray(JSONHelper.createDefaultGson().toJson(sections))));
                } catch (JSONException e) {
                    promise.reject("", e.getMessage());

                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                promise.reject("", errorResponse.getReason());
            }
        });
    }

    @ReactMethod
    public void getArticlesPromise(Double sectionId, final Promise promise) {
        Long sectId = sectionId.longValue();
        this.provider.getArticles(sectId, new ZendeskCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> articles) {
                try {
                    promise.resolve(JSONHelper
                            .convertJsonToArray(new JSONArray(JSONHelper.createDefaultGson().toJson(articles))));
                } catch (JSONException e) {
                    promise.reject("", e.getMessage());

                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                promise.reject("", errorResponse.getReason());
            }
        });
    }

    @ReactMethod
    public void getSuggestedArticlesPromise(String searchQuery, final Promise promise) {
        SuggestedArticleSearch.Builder builder = new SuggestedArticleSearch.Builder();
        builder.withQuery(searchQuery);

        SuggestedArticleSearch search = builder.build();

        this.provider.getSuggestedArticles(search, new ZendeskCallback<SuggestedArticleResponse>() {
            @Override
            public void onSuccess(SuggestedArticleResponse response) {
                try {
                    promise.resolve(JSONHelper
                            .convertJsonToMap(new JSONObject(JSONHelper.createDefaultGson().toJson(response))));
                } catch (JSONException e) {
                    promise.reject("", e.getMessage());

                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                promise.reject("", errorResponse.getReason());
            }
        });
    }

    @ReactMethod
    public void upvoteArticlePromise(Double articleId, final Promise promise) {
        Long artId = articleId.longValue();
        this.provider.upvoteArticle(artId, new ZendeskCallback<ArticleVote>() {
            @Override
            public void onSuccess(ArticleVote article) {
                try {
                    promise.resolve(JSONHelper
                            .convertJsonToMap(new JSONObject(JSONHelper.createDefaultGson().toJson(article))));
                } catch (JSONException e) {
                    promise.reject("", e.getMessage());

                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                promise.reject("", errorResponse.getReason());
            }
        });
    }

    @ReactMethod
    public void downvoteArticlePromise(Double articleId, final Promise promise) {
        Long artId = articleId.longValue();
        this.provider.downvoteArticle(artId, new ZendeskCallback<ArticleVote>() {
            @Override
            public void onSuccess(ArticleVote article) {
                try {
                    promise.resolve(JSONHelper
                            .convertJsonToMap(new JSONObject(JSONHelper.createDefaultGson().toJson(article))));
                } catch (JSONException e) {
                    promise.reject("", e.getMessage());

                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                promise.reject("", errorResponse.getReason());
            }
        });
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
