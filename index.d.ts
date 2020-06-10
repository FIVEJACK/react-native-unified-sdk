export function initSDK(
  zendeskUrl: string,
  appId: string,
  clientId: string
): void;

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

export function setAnonymousIdentity(name: string, email: string): void;
export function showMessagingActivity(useAnswerBot: boolean): void;
export function showCreateTicket(ticketConfiguration: TicketConfiguration): void;
export function showTicketList(): void;
export function showHelpCenterActivity(helpConfiguration: HelpConfiguration): void;

