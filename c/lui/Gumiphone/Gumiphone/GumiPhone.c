#include "Account.h"
#include "GumiPhone.h"

#include <stdio.h>
#include <Windows.h>


Account accounts[MAX_ACCOUNTS];
int num_accounts;
Message messages[150];
int num_messages;
Message announcements[150];
int num_announcements;

int load_accounts(Account accounts[]) {
	FILE* fp = fopen("accounts.txt", "r");

	if (fp == NULL) {
		printf("Error: Unable to open file `accounts.txt`\n");
		return -1;
	}

	int num_accounts = 0;
	char line[1024];
	while (fgets(line, 1024, fp)) {
		char* token = strtok(line, ",");
		strcpy(accounts[num_accounts].name, token);

		token = strtok(NULL, ",");
		strcpy(accounts[num_accounts].username, token);

		token = strtok(NULL, ",");
		strcpy(accounts[num_accounts].pass, token);

		token = strtok(NULL, ",");
		strcpy(accounts[num_accounts].sequrity_question, token);

		token = strtok(NULL, ",");
		strcpy(accounts[num_accounts].sequrity_answer, token);

		token = strtok(NULL, ",");
		strcpy(accounts[num_accounts].description, token);

		token = strtok(NULL, ",");
		int num_of_connections = 0;
		while (token != NULL) {
			strcpy(accounts[num_accounts].connections[num_of_connections], token);
			num_of_connections++;
			token = strtok(NULL, ",");
		}

		accounts[num_accounts].num_of_connections = num_of_connections;

		num_accounts++;
	}

	fclose(fp);

	return num_accounts;
}

void save_accounts(Account accounts[]) {
	FILE* fp = fopen("accounts.txt", "w");

	if (fp == NULL) {
		printf("Error: Unable to open file `accounts.txt`\n");
		return;
	}

	for (int i = 0; i < num_accounts; i++) {
		fprintf(
			fp,
			"%s,%s,%s,%s,%s,%s,",
			accounts[i].name,
			accounts[i].username,
			accounts[i].pass,
			accounts[i].sequrity_question,
			accounts[i].sequrity_answer,
			accounts[i].description
		);
		
		for (int j = 0; j < accounts[i].num_of_connections; j++) {
			fprintf(
				fp,
				"%s%s",
				accounts[i].connections[j],
				j == accounts[i].num_of_connections - 1 ? "" : ","
			);
		}

		fprintf(fp, "\n");
	}
}


int main() {
	//int choice;

	//do {
	//	printf("\nGUMIPHONE\n");
	//	printf("---------------MAIN PAGE---------------\n");
	//	printf("||                                   ||\n");
	//	printf("||     [1] LOG-IN                    ||\n");
	//	printf("||     [2] CREATE ACCOUNT            ||\n");
	//	printf("||     [3] FORGOT PASSWORD           ||\n");
	//	printf("||     [4] EXIT                      ||\n");
	//	printf("||                                   ||\n");
	//	printf("---------------------------------------\n");
	//	printf("CHOICE:");
	//	scanf("%d", choice);

	//	switch (choice) {
	//	case 1:
	//		login();
	//		break;
	//	case 2:
	//		create_account();
	//		break;
	//	case 3:
	//		//forgot_password();
	//		break;
	//	case 4:
	//		exit(0);
	//		break;
	//	default:
	//		printf("Invalid choice. Try again.\n");
	//	}
	//} while (choice != 4);
	num_accounts = load_accounts(accounts);

	printf("%d\n", num_accounts);

	for (int i = 0; i < num_accounts; i++) {
		for (int j = 0; j < accounts[i].num_of_connections; j++) {
			printf("%s ", accounts[i].connections[j]);
		}
		printf("\n");
	}

	return 0;
}

