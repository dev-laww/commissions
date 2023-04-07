#include "Messages.h"
#include <stdio.h>
#include <string.h>

Message compose(Account* sender, Account* receiver) {
	Message mess;

	strcpy(mess.sender, sender->name);
	strcpy(mess.receiver, receiver->name);

	printf("Enter the subject: ");
	while (fgets(mess.subject, MAX_STRING, stdin) && mess.subject[0] == '\n');
	fgets(mess.subject, MAX_STRING, stdin);

	printf("Enter your message: ");
	fgets(mess.body, MAX_STRING, stdin);

	return mess;
}

void send(char mode[]) {
	if (strcmp(mode, "send") == 0) {
		printf("Message sent!\n");
	}
	else if (strcmp(mode, "draft") == 0) {
		printf("Message saved as draft!\n");
	}
}
