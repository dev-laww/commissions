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

char admin[] = "admin";
char admin_pass[] = "admin";

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

void admin_page() {
	printf("Admin");
}

void account_page(Account* account) {
	system("cls");
	printf("-----------------USER MODULE-----------------\n");
	printf("||                                         ||\n");
	printf("||     [1] COMPOSE MESSAGE                 ||\n");
	printf("||     [2] INBOX                           ||\n");
	printf("||     [3] SENT                            ||\n");
	printf("||     [4] VIEW ANNOUNCEMENTS              ||\n");
	printf("||     [5] MODIFY PERSONAL CONTENTS        ||\n");
	printf("||     [6] MODIFY ACCOUNT SECURITY         ||\n");
	printf("||     [7] MODIFY PERSONAL CONNECTIONS     ||\n");
	printf("||     [8] BROWSE USERS                    ||\n");
	printf("||     [9] LOGOUT                          ||\n");
	printf("||                                         ||\n");
	printf("---------------------------------------------\n");
	printf("CHOICE: ");

	Sleep(1000);
}

void login() {
	char username[41];
	char pass[41];
	printf("Enter your username: ");
	scanf("%s", username);
	printf("Enter your password: ");
	scanf("%s", pass);
	
	if (strcmp(admin, username) == 0) {
		if (strcmp(admin_pass, pass) == 0) {
			admin_page();
			return;
		}

		printf("Invalid password.\n");
		Sleep(1000);
		return;
	}

	int index = exists(username, accounts, num_accounts);
	if (index == -1) {
		printf("Account does not exist.\n");
		Sleep(1000);
		return;
	}

	if (strcmp(accounts[index].pass, pass) == 0) {
		account_page(&accounts[index]);
		return;
	}

	printf("Invalid password.\n");
	Sleep(1000);
}

int main() {
	int choice;

	num_accounts = load_accounts(accounts);


	do {
		system("cls");
		printf("\nGUMIPHONE\n");
		printf("---------------MAIN PAGE---------------\n");
		printf("||                                   ||\n");
		printf("||     [1] LOG-IN                    ||\n");
		printf("||     [2] CREATE ACCOUNT            ||\n");
		printf("||     [3] FORGOT PASSWORD           ||\n");
		printf("||     [4] EXIT                      ||\n");
		printf("||                                   ||\n");
		printf("---------------------------------------\n");
		printf("CHOICE: ");
		scanf("%d", &choice);

		switch (choice) {
		case 1:
			login();
			break;
		case 2:
			create_account();
			break;
		case 3:
			//forgot_password();
			break;
		case 4:
			exit(0);
			break;
		default:
			printf("Invalid choice. Try again.\n");
		}
	} while (choice != 4);


	return 0;
}

