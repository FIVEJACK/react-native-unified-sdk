import { NativeModules } from "react-native";

const { unifiedSDK } = NativeModules as { unifiedSDK: number };

export default unifiedSDK;
