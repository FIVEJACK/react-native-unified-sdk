import React from 'react';
import {View, Text, Button} from 'react-native';
import UnifiedSDK from '@itemku/react-native-unified-sdk';

const App = () => {
  UnifiedSDK.initSDK('https://itemku.zendesk.com', '', '');

  return (
    <View>
      <Text>Unified SDK</Text>
      <Button
        title="Show Support Ticket List"
        onPress={() => {
          UnifiedSDK.showTicketList();
        }}
      />
    </View>
  );
};

export default App;
