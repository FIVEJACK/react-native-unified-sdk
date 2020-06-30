interface TicketConfiguration {
  subject?: string;
  tags?: Array<string>;
  fields?: Array<{ id: number; value: any }>;
}

interface HelpConfiguration {
  isContactUsVisible?: boolean;
  showConversationButton?: boolean;
  labels?: Array<string>;
  categoryIds?: Array<number>;
  sectionIds?: Array<number>;
}

export const UnifiedSDK: {
  initSDK: (zendeskUrl: string, appId: string, clientId: string) => void;
  setAnonymousIdentity: (name: string, email: string) => void;
  showMessagingActivity: (useAnswerBot: boolean) => void;
  showCreateTicket: (ticketConfiguration: TicketConfiguration) => void;
  showTicketList: () => void;
  showHelpCenterActivity: (helpConfiguration: HelpConfiguration) => void;
};

export type Category = {
  description: string;
  htmlUrl: string;
  id: number;
  locale: string;
  name: string;
  outdated: boolean;
  position: number;
  sourceLocale: string;
  url: string;
  updatedAt: string;
  createdAt: string;
};

export type Section = {
  id: number;
  article_count: number;
  categoryId: number;
  description: string;
  htmlUrl: string;
  locale: string;
  name: string;
  outdated: boolean;
  position: number;
  sorting: string;
  sourceLocale: string;
  url: string;
  updatedAt: string;
  createdAt: string;
};

export type SuggestedArticle = {
  results: Array<{ id: number; title: string }>;
};

export type Article = {
  author: {
    agent: boolean;
    id: string;
    name: string;
    organizationId: number;
    photo: {
      contentUrl: string;
    };
    tags: Array<string>;
    userFields: any;
  };
  authorId: string;
  body: string;
  commentsDisabled: boolean;
  createdAt: string;
  draft: boolean;
  htmlUrl: string;
  id: string;
  labelNames: Array<string>;
  locale: string;
  outdated: boolean;
  sectionId: number;
  sourceLocale: string;
  title: string;
  updatedAt: string;
  url: string;
  voteCount: number;
  voteSum: number;
};

export type ArticleVote = {
  createdAt: string;
  id: string;
  itemId: string;
  itemType: string;
  updatedAt: string;
  url: string;
  userId: string;
  value: number;
};

export const HelpCenterProvider: {
  init: () => void;
  getAllCategories: () => Promise<Array<Category>>;
  getSections: (categoryId: number) => Promise<Array<Section>>;
  getArticles: (sectionId: number) => Promise<Array<Article>>;
  getSuggestedArticles: (query: string) => Promise<SuggestedArticle>;
  downvoteArticle: (articleId: number) => Promise<Array<ArticleVote>>;
  upvoteArticle: (articleId: number) => Promise<Array<ArticleVote>>;
};
