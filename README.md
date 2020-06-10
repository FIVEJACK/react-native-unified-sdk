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
import UnifiedSDK from "@itemku/react-native-unified-sdk";

UnifiedSDK.initSDK("https://itemku.zendesk.com", "testId", "testKey");
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
