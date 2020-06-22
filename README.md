# React Native Zendesk Unified SDK

[![npm version](https://badge.fury.io/js/%40itemku%2Freact-native-unified-sdk.svg)](https://badge.fury.io/js/%40itemku%2Freact-native-unified-sdk)

React Native Zendesk Unified SDK, current only Android Ver
https://developer.zendesk.com/embeddables/docs/android-unified-sdk/introduction

# Getting started

## Android

#### Add packages

```jsx
yarn add react-native-unified-sdk
```

### Add Zendesk Maven Url in build.gradle

```
maven {
    url "https://zendesk.jfrog.io/zendesk/repo"
}
```

### Quickstart

Init Zendesk in index.js

```jsx
import {
  UnifiedSDK,
  HelpCenterProvider,
} from "@itemku/react-native-unified-sdk";

UnifiedSDK.initSDK("https://itemku.zendesk.com", "testId", "testKey");
HelpCenterProvider.init();
```

### Call Function

```jsx
UnifiedSDK.setAnonymousIdentity("itemku", "itemku@itemku.com"); // set anonymous identity
UnifiedSDK.showMessagingActivity(useAnswerBot); // show messaging activity
UnifiedSDK.showCreateTicket({
  subject: "itemku Hebat",
  tags: ["itemku", "hebat"],
  fields: [
    {
      id: 12345678,
      value: "rionardo",
    },
  ],
}); // create ticket with configuration
UnifiedSDK.showTicketList({
  isContactUsVisible: false,
  showConversationButton: false,
  labels: ["rionardo", "itemku"],
  categoryIds: [1231, 1232],
  sectionIds: [12345, 123456],
}); // show ticket list

//Help Center API Get Categories
const getCategories = async () => {
  let result = await HelpCenterProvider.getAllCategories();
  console.log(result);
};

//Help Center API Get Sections
const getSections = async () => {
  let result = await HelpCenterProvider.getSections(202586637);
  console.log(result);
};

//Help Center API Get Articles
const getArticles = async () => {
  let result = await HelpCenterProvider.getArticles(203885138);
  console.log(result);
};

//Help Center API Get Suggested Articles
const getSuggestedArticles = async () => {
  try {
    let result = await HelpCenterProvider.getSuggestedArticles("itemku");
    console.log(result);
  } catch (e) {
    console.log(e);
  }
};

//Help Center API upvote article
const upvoteArticle = async () => {
  try {
    let result = await HelpCenterProvider.upvoteArticle(115002238612);
    console.log(result);
  } catch (e) {
    console.log(e);
  }
};

//Help Center API downvote article
const downvoteArticle = async () => {
  try {
    let result = await HelpCenterProvider.downvoteArticle(115002238612);
    console.log(result);
  } catch (e) {
    console.log(e);
  }
};
```

## Customize Theme

### in your Android styles.xml

```jsx
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    ...
    <!-- Customize your theme here. -->
    <item name="android:textColor">#000000</item>
    <item name="colorPrimary">#307FE2</item>
    <item name="colorPrimaryDark">#0065D2</item>
    <item name="colorAccent">#E1EEFF</item>
</style>
```

### More info

https://developer.zendesk.com/embeddables/docs/android-support-sdk/customize_the_look
