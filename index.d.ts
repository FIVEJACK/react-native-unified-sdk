export function initSDK(
  zendeskUrl: string,
  appId: string,
  clientId: string
): void;

export function setAnonymousIdentity(name: string, email: string): void;
export function showMessagingActivity(useAnswerBot: boolean): void;
export function showCreateTicket(subject: string): void;
export function showTicketList(): void;
