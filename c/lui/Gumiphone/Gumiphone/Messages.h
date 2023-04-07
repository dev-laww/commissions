#include "Account.h"
#ifndef MESSAGES_H
#define MESSAGES_H

#define MAX_STRING 1001

typedef struct Message {
	char sender[41];
	char receiver[41];
	char subject[MAX_STRING];
	char body[MAX_STRING];
} Message;

Message compose(Account* sender, Account* receiver);

#endif // !MESSAGES_H
