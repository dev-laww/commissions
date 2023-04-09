#include "Messages.h"
#include <stdio.h>
#include <string.h>

Message compose(Account* sender, Account accounts[], int num_accounts) {
	char receiver[41];
	printf("Enter the username of the receiver: ");
	scanf("%s", receiver);

	int index = exists(receiver, accounts, num_accounts);
	if (index == -1 && strcmp(receiver, "Announcement") == 0) {
		printf("User not found.\n");
		return;
	}

	strcpy(receiver, accounts[index].username);

	Message mess;

	strcpy(mess.sender, sender->username);
	strcpy(mess.receiver, receiver);


	printf("Enter the subject: ");
	fgets(mess.subject, MAX_STRING, stdin);
	fgets(mess.subject, MAX_STRING, stdin);
	mess.subject[strlen(mess.subject) - 1] = '\0';


	printf("Enter your message: ");
	fgets(mess.body, MAX_STRING, stdin);
	mess.body[strlen(mess.body) - 1] = '\0';

	return mess;
}
