#include "Account.h"
#include <stdio.h>
#include <string.h>
#include <Windows.h>

Account create_account() {
	Account acc;
	acc.num_of_connections = 0;

	char pass[MAX_PASS], confirm[MAX_PASS];
	printf("Enter your name: ");
	scanf("%s", acc.name);
	printf("Enter your username: ");
	scanf("%s", acc.username);
	printf("Enter your password: ");
	fgets(pass, MAX_PASS, stdin);
	fgets(pass, MAX_PASS, stdin);
	pass[strlen(pass) - 1] = '\0';

	printf("Confirm your password: ");
	fgets(confirm, MAX_PASS, stdin);
	confirm[strlen(confirm) - 1] = '\0';

	while (strcmp(pass, confirm) != 0) {
		system("cls");
		printf("Passwords do not match. Try again.\n");
		printf("Enter your password: ");

		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';
		printf("Confirm your password: ");
		fgets(confirm, MAX_PASS, stdin);
		confirm[strlen(confirm) - 1] = '\0';
	}

	strcpy(acc.pass, pass);

	char* sec_question = generate_sec_question();

	strcpy(acc.sequrity_question, sec_question);
	printf("%s ", sec_question);
	fgets(acc.sequrity_answer, MAX_STRING, stdin);
	acc.sequrity_answer[strlen(acc.sequrity_answer) - 1] = '\0';

	strcpy(acc.description, "DEFAULT USER");

	acc.is_locked = 0;
	return acc;
}

void view_account_details(Account* acc) {
	system("cls");
	printf("User details\n");
	printf("Name: %s\n", acc->name);
	printf("Username: %s\n", acc->username);
	printf("Description: %s\n", acc->description);
}

void edit_account_details(Account* acc) {
	system("cls");
	printf("Edit account details\n");
	printf("1. Name\n");
	printf("2. Change description\n");
	printf("3. Back\n");
	printf("Enter your choice: ");

	int choice;
	char pass[MAX_PASS];
	scanf("%d", &choice);

	switch (choice) {
	case 1:
		printf("Enter new name: ");
		fgets(acc->name, MAX_STRING, stdin);
		acc->name[strlen(acc->name) - 1] = '\0';

		printf("Enter your current password: ");
		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';

		if (strcmp(pass, acc->pass) != 0) {
			printf("Incorrect password. Try again.\n");
			Sleep(1000);
			edit_account_details(acc);
			return;
		}
		break;
	case 2:
		printf("Enter new description: ");

		fgets(acc->description, MAX_STRING, stdin);
		fgets(acc->description, MAX_STRING, stdin);
		acc->description[strlen(acc->description) - 1] = '\0';

		printf("Enter your current password: ");
		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';

		if (strcmp(pass, acc->pass) != 0) {
			printf("Incorrect password. Try again.\n");
			Sleep(1000);
			edit_account_details(acc);
			return;
		}

		printf("Account description changed successfully.\n");
		Sleep(1000);
		break;
	case 3:
		return;
	default:
		printf("Invalid choice. Try again.\n");
		break;
	}
	edit_account_details(acc);
}

void change_security(Account* acc) {
	system("cls");

	printf("Change account security\n");
	printf("1. Change password\n");
	printf("2. Change security question answer\n");
	printf("3. Back\n");
	printf("Enter your choice: ");
	int choice;

	char pass[MAX_PASS], confirm[MAX_PASS];
	char sec_ans[MAX_STRING];

	scanf("%d", &choice);
	switch (choice) {
	case 1:
		system("cls");
		printf("Change Password\n");
		printf("Enter your current password: ");
		while (fgets(pass, MAX_PASS, stdin) && pass[0] != '\n');
		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';

		if (strcmp(pass, acc->pass) != 0) {
			printf("Incorrect password. Try again.\n");
			Sleep(1000);
			change_security(acc);
			return;
		}

		printf("Enter your new password: ");
		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';
		printf("Confirm password: ");
		fgets(confirm, MAX_PASS, stdin);
		confirm[strlen(confirm) - 1] = '\0';

		while (strcmp(pass, confirm) != 0) {
			system("cls");
			printf("Passwords do not match. Try again.\n");
			printf("Enter your password: ");
			fgets(pass, MAX_PASS, stdin);
			pass[strlen(pass) - 1] = '\0';
			printf("Confirm your password: ");
			fgets(confirm, MAX_PASS, stdin);
			confirm[strlen(confirm) - 1] = '\0';
		}

		strcpy(acc->pass, pass);
		printf("Password Changed!\n");
		Sleep(1000);
		break;
	case 2:
		system("cls");
		printf("Change Security Question Answer\n");
		printf("%s ", acc->sequrity_question);
		while (fgets(sec_ans, MAX_STRING, stdin) && sec_ans[0] != '\n');
		fgets(sec_ans, MAX_STRING, stdin);
		sec_ans[strlen(sec_ans) - 1] = '\0';

		strcpy(acc->sequrity_answer, sec_ans);

		printf("Enter your current password: ");
		while (fgets(pass, MAX_PASS, stdin) && pass[0] != '\n');
		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';

		if (strcmp(pass, acc->pass) != 0) {
			printf("Incorrect password. Try again.\n");
			Sleep(1000);
			change_security(acc);
			return;
		}

		printf("Answer changed!\n");
		Sleep(1000);
		break;
	case 3:
		return;
	default:
		printf("Invalid Choice!");
		break;
	}
	change_security(acc);
}

void add_conn(Account* acc, Account accounts[], int num_accounts) {
	system("cls");
	if (acc->num_of_connections == MAX_CONNECTIONS) {
		printf("You have reached the maximum number of connections.\n");
		return;
	}

	char user_name[MAX_STRING];
	printf("Enter the username of the user you want to add: ");
	while(fgets(user_name, MAX_STRING, stdin) && user_name[0] != '\n');
	fgets(user_name, MAX_STRING, stdin);
	user_name[strlen(user_name) - 1] = '\0';

	int index = -1;
	for (int i = 0; i < num_accounts; i++) {
		if (strcmp(user_name, accounts[i].username) == 0) {
			index = i;
			break;
		}
	}

	if (index == -1) {
		printf("User not found.\n");
		return;
	}

	if (strcmp(user_name, acc->username) == 0) {
		printf("You cannot add yourself as a connection.\n");
		return;
	}

	for (int i = 0; i < acc->num_of_connections; i++) {
		if (strcmp(user_name, acc->connections[i]) == 0) {
			printf("User is already a connection.\n");
			return;
		}
	}

	strcpy(acc->connections[acc->num_of_connections], user_name);
	acc->num_of_connections++;
	printf("User successfully added as a connection.\n");
}


void remove_conn(Account* acc, Account accounts[]) {
	system("cls");
	if (acc->num_of_connections == 0) {
		printf("You have no connections.\n");
		return;
	}

	char user_name[MAX_STRING];
	printf("Enter the username of the user you want to remove: ");
	fgets(user_name, MAX_STRING, stdin);
	user_name[strlen(user_name) - 1] = '\0';
	int index = -1;
	for (int i = 0; i < acc->num_of_connections; i++) {
		if (strcmp(user_name, acc->connections[i]) == 0) {
			index = i;
			break;
		}
	}

	if (index == -1) {
		printf("User not found.\n");
		return;
	}

	for (int i = index; i < acc->num_of_connections - 1; i++) {
		strcpy(acc->connections[i], acc->connections[i + 1]);
	}
	acc->num_of_connections--;
	printf("User successfully removed as a connection.\n");
}

void view_conn(Account* acc) {
	system("cls");

	if (acc->num_of_connections == 0) {
		printf("No connections.\n");
		return;
	}

	printf("Account Connections\n");
	for (int i = 0; i < acc->num_of_connections; i++) {
		printf("%d. %s\n", i + 1, acc->connections[i]);
	}
}

int exists(char username[], Account accounts[], int num_accounts) {
	for (int i = 0; i < num_accounts; i++) {
		if (strcmp(username, accounts[i].username) == 0) {
			return i;
		}
	}
	return -1;
}

void view_user_conn(Account* acc, Account accounts[], int num_accounts) {
	int counter = 0;
	printf("List of users in %s's connection list:\n", acc->name);
	
	for (int i = 0; i < acc->num_of_connections; i++) {
		for (int j = 0; j < num_accounts; j++) {
			if (strcmp(acc->connections[i], accounts[j].username) == 0) {
				counter++;
				printf("%d. %s\n", counter, accounts[j].name);
			}
		}
	}
}

char* generate_sec_question() {
	FILE* fp;

	char questions[10][MAX_STRING];

	fp = fopen("questions.txt", "r");

	if (fp == NULL) {
		printf("Error opening file.\n");
		exit(1);
	}

	int i = 0;
	while (fgets(questions[i], MAX_STRING, fp)) {
		strtok(questions[i], "\n"); // remove the newline character
		i++;
	}

	fclose(fp);

	srand(time(NULL));
	int random_index = rand() % 5;
	return questions[random_index];
}
