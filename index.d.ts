

interface TicketConfiguration {
  subject?:string;
  tags?:Array<string>;
  fields?:Array<{id:number,value:any}>;
}

interface HelpConfiguration {
  isContactUsVisible?: boolean;
  showConversationButton?: boolean;
  labels?: Array<string>;
  categoryIds?: Array<number>;
  sectionIds?: Array<number>;
}

export const UnifiedSDK : {
  initSDK: (zendeskUrl: string, appId: string, clientId: string) => void;
  setAnonymousIdentity: (name: string, email: string) => void;
  showMessagingActivity: (useAnswerBot: boolean) => void;
  showCreateTicket: (ticketConfiguration: TicketConfiguration) => void;
  showTicketList: () => void;
  showHelpCenterActivity: (helpConfiguration: HelpConfiguration) => void;
}

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
}

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
}


export const HelpCenterProvider : {
  init: () => void;
  getAllCategories: () => Promise<Array<Category>>;
  getSections: (categoryId:number) => Promise<Array<Section>>;
}



