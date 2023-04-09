#include "Messages.h"
#include <stdio.h>
#include <string.h>

Message compose(Account* sender, Account accounts[], int num_accounts) {
	char receiver[41];
	printf("Enter the username of the receiver: ");
	scanf("%s", receiver);

	int index = exists(receiver, accounts, num_accounts);
	if (index == -1) {
		printf("User not found.\n");
		return;
	}

	char receiver[] = accounts[index].name;

	Message mess;

	strcpy(mess.sender, sender->name);
	strcpy(mess.receiver, receiver);


	printf("Enter the subject: ");
	while (fgets(mess.subject, MAX_STRING, stdin) && mess.subject[0] == '\n');
	fgets(mess.subject, MAX_STRING, stdin);

	printf("Enter your message: ");
	fgets(mess.body, MAX_STRING, stdin);

	return mess;
}
