import React from 'react';
import {View, Text, Button} from 'react-native';
import {UnifiedSDK, HelpCenterProvider} from '@itemku/react-native-unified-sdk';

const App = () => {
  UnifiedSDK.initSDK('https://itemku.zendesk.com', '', '');

  UnifiedSDK.setAnonymousIdentity('Rio', 'rio@fivejack.com');

  HelpCenterProvider.init();

  const getCategories = async () => {
    let result = await HelpCenterProvider.getAllCategories();
    console.log(result);
  };

  const getSections = async () => {
    let result = await HelpCenterProvider.getSections(202586637);
    console.log(result);
  };

  const getArticles = async () => {
    try {
      let result = await HelpCenterProvider.getArticles(203885138);
      console.log(result);
    } catch (e) {
      console.log(e);
    }
  };

  const getSuggestedArticles = async () => {
    try {
      let result = await HelpCenterProvider.getSuggestedArticles('itemku');
      console.log(result);
    } catch (e) {
      console.log(e);
    }
  };

  const upvoteArticle = async () => {
    try {
      let result = await HelpCenterProvider.upvoteArticle(115002238612);
      console.log(result);
    } catch (e) {
      console.log(e);
    }
  };

  const downvoteArticle = async () => {
    try {
      let result = await HelpCenterProvider.downvoteArticle(115002238612);
      console.log(result);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <View>
      <Text>Unified SDK</Text>
      <Button
        title="Get Categories"
        onPress={() => {
          getCategories();
        }}
      />
      <Button
        title="Get Sections"
        onPress={() => {
          getSections();
        }}
      />
      <Button
        title="Get Articles"
        onPress={() => {
          getArticles();
        }}
      />
      <Button
        title="upvote Article"
        onPress={() => {
          upvoteArticle();
        }}
      />
      <Button
        title="downvote Article"
        onPress={() => {
          downvoteArticle();
        }}
      />
      <Button
        title="Get Suggested Articles"
        onPress={() => {
          getSuggestedArticles();
        }}
      />
      <Button
        title="Show Support Ticket List"
        onPress={() => {
          UnifiedSDK.showTicketList();
        }}
      />
      <Button
        title="Show Create Ticket"
        onPress={() => {
          UnifiedSDK.showCreateTicket({
            subject: 'itemku https://itemku.com',
            tags: ['itemku', 'voted'],
            fields: [
              {
                id: 360032165032,
                value: 'Aku Pembeli',
              },
            ],
          });
        }}
      />
      <Button
        title="Show Help Center"
        onPress={() => {
          UnifiedSDK.showHelpCenterActivity({
            isContactUsVisible: false,
            showConversationButton: false,
            sectionIds: [203947968],
          });
        }}
      />
    </View>
  );
};

export default App;
