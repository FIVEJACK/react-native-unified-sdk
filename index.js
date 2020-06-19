import { NativeModules } from "react-native";

const { UnifiedSDK, HelpCenterProvider } = NativeModules;
console.log(HelpCenterProvider, UnifiedSDK);
HelpCenterProvider.getAllCategories = () => {    
    
    return HelpCenterProvider.getAllCategoriesPromise().catch((error) => {        
      throw new Error(getError(error));      
  });  
}

HelpCenterProvider.getSections = (categoryId) => {    
    
    return HelpCenterProvider.getSectionsPromise(categoryId).catch((error) => {        
      throw new Error(getError(error));      
  });  

}


const getError = (error) => {  
    const errorObject = JSON.parse(errorBody);           
    return errorObject;
};

export { UnifiedSDK, HelpCenterProvider };

