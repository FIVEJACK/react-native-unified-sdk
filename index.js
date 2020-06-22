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

HelpCenterProvider.getArticles = (sectionId) => {    
    
    return HelpCenterProvider.getArticlesPromise(sectionId).catch((error) => {        
      throw new Error(getError(error));      
  });  

}

HelpCenterProvider.getSuggestedArticles = (search) => {    
    
    return HelpCenterProvider.getSuggestedArticlesPromise(search).catch((error) => {        
      throw new Error(getError(error));      
  });  

}

HelpCenterProvider.downvoteArticle = (articleId) => {    
    
    return HelpCenterProvider.downvoteArticlePromise(articleId).catch((error) => {        
      throw new Error(getError(error));      
  });  

}

HelpCenterProvider.upvoteArticle = (articleId) => {    
    
    return HelpCenterProvider.upvoteArticlePromise(articleId).catch((error) => {        
      throw new Error(getError(error));      
  });  

}

const getError = (error) => {  
    const errorObject = error;           
    return errorObject;
};

export { UnifiedSDK, HelpCenterProvider };

