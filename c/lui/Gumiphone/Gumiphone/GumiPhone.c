#include "Account.h"
#include "GumiPhone.h"

#include <stdio.h>
#include <conio.h>
#include <Windows.h>


Account accounts[MAX_ACCOUNTS];
int num_accounts;
Message messages[1001];
int num_messages;
Message announcements[250];
int num_announcements;

char admin[] = "admin";
char admin_pass[] = "admin";

int load_accounts(Account accounts[]) {
	FILE* fp = fopen("accounts.txt", "r");

	if (fp == NULL) {
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
			"%s,%s,%s,%s,%s,%s",
			accounts[i].name,
			accounts[i].username,
			accounts[i].pass,
			accounts[i].sequrity_question,
			accounts[i].sequrity_answer,
			accounts[i].description
		);

		for (int j = 0; j < accounts[i].num_of_connections; j++) {
			fprintf(fp, ",%s", accounts[i].connections[j]);
		}
	}

	fclose(fp);
}

int load_messages(Message messages[], char mode[]) {

	FILE* fp;
	if (strcmp(mode, "announcement") == 0) {
		fp = fopen("announcements.txt", "r");
	}
	else {
		fp = fopen("messages.txt", "r");
	}

	if (fp == NULL) {
		return -1;
	}

	int num_messages = 0;
	char line[1024];
	while (fgets(line, 1024, fp)) {
		char* token = strtok(line, ",");
		strcpy(messages[num_messages].sender, token);
		token = strtok(NULL, ",");
		strcpy(messages[num_messages].receiver, token);
		token = strtok(NULL, ",");
		strcpy(messages[num_messages].subject, token);
		token = strtok(NULL, ",");
		strcpy(messages[num_messages].body, token);
		num_messages++;
	}

	fclose(fp);
	return num_messages;
}

void save_messages(Message messages[], char mode[]) {
	FILE* fp;
	if (strcmp(mode, "announcement") == 0) {
		fp = fopen("announcements.txt", "w");
	}
	else {
		fp = fopen("messages.txt", "w+");
	}

	if (fp == NULL) {
		printf("Error: Unable to open file\n");
		return;
	}

	int num_ = strcmp("announcement", mode) == 0 ? num_announcements : num_messages;

	for (int i = 0; i < num_; i++) {

		messages[i].body[strlen(messages[i].body) - 1] = '\0';

		fprintf(
			fp,
			"%s,%s,%s,%s\n",
			messages[i].sender,
			messages[i].receiver,
			messages[i].subject,
			messages[i].body
		);
	}

	fclose(fp);
}

char* encrypt(char* password) {
	int i;
	for (i = 0; i < strlen(password); i++) {
		password[i] = password[i] + 3; // add 3 to ASCII value
	}
	return password;
}

void manage_users() {
	system("cls");

	int choice;

	printf("Manage users\n");
	printf("1. View all users\n");
	printf("2. Modify user\n");
	printf("3. Reset password\n");
	printf("4. Delete user\n");
	printf("5. Edit security questions\n");
	printf("6. Back\n");
	printf("Enter your choice: ");
	scanf("%d", &choice);

	switch (choice) {
	case 1:
		system("cls");
		printf("All users\n\n");
		for (int i = 0; i < num_accounts; i++) {
			char* pass = encrypt(accounts[i].pass);
			printf("Name: %s\n", accounts[i].name);
			printf("Username: %s\n", accounts[i].username);
			printf("Password: %s\n\n", pass);

			printf("Connections\n");
			if (accounts[i].num_of_connections == 0) {
				printf("No connections\n");
			}
			else {
				for (int j = 0; j < accounts[i].num_of_connections; j++) {
					printf("%d. %s\n", j + 1, accounts[i].connections[j]);
				}
			}
			printf("-----------------------------------\n");
		}
		printf("Press any key to continue...");
		_getch();
		break;

	}
}

void manage_messages() {

}

void admin_page() {
	printf("Admin");

	int choice;

	system("cls");
	printf("-----------------ADMIN MODULE-----------------\n");
	printf("||                                          ||\n");
	printf("||     [1] MANAGE USER                      ||\n");
	printf("||     [2] MANAGE MESSAGES                  ||\n");
	printf("||     [3] CHANGE PASSWORD                  ||\n");
	printf("||     [4] EXIT                             ||\n");
	printf("||                                          ||\n");
	printf("----------------------------------------------\n");
	printf("CHOICE: ");
	scanf("%d", &choice);
	switch (choice) {
	case 1:
		manage_users();
		break;
	case 2:
		manage_messages();
		break;
	case 3:
		system("cls");
		char pass[MAX_PASS], confirm[MAX_PASS];

		printf("Change Password\n");
		printf("Enter your current password: ");
		while (fgets(pass, MAX_PASS, stdin) && pass[0] != '\n');
		fgets(pass, MAX_PASS, stdin);
		pass[strlen(pass) - 1] = '\0';

		if (strcmp(pass, admin_pass) != 0) {
			printf("Incorrect password. Try again.\n");
			Sleep(1000);
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

		strcpy(admin_pass, pass);
		printf("Password changed successfully.\n");
		Sleep(1000);
		break;
	case 4:
		return;
	default:
		printf("Invalid choice. Try again.\n");
		Sleep(1000);
		break;
	}
}

void account_page(Account* account) {
	int choice;

	Message message;

	do {
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
		scanf("%d", &choice);

		switch (choice) {
		case 1:
			system("cls");
			printf("Compose\n");
			printf("Send as:\n");
			printf("[1] Personal Message\n");
			printf("[2] Group Message \n");
			printf("[3] Announcement\n");
			printf("Choice: ");
			scanf("%d", &choice);

			switch (choice) {
			case 1:
				message = compose(account, accounts, num_accounts);
				messages[num_messages] = message;
				num_messages++;

				printf("Sending message...\n");
				printf("Message sent.\n");
				Sleep(1000);
				save_messages(messages, "message");
				break;
			case 2:
				printf("Enter the subject: ");
				fgets(message.subject, MAX_STRING, stdin);
				fgets(message.subject, MAX_STRING, stdin);
				message.subject[strlen(message.subject) - 1] = '\0';

				printf("Enter your message: ");
				fgets(message.body, MAX_STRING, stdin);
				message.body[strlen(message.body) - 1] = '\0';

				strcpy(message.sender, account->username);

				printf("Enter usernames of recipients (separated by commas without spaces): ");

				char line[1024];
				fgets(line, 1024, stdin);

				char* token = strtok(line, ",");
				char* usernames[15];
				int num_users = 0;

				while (token != NULL) {
					if (token[strlen(token) - 1] == '\n')
						token[strlen(token) - 1] = '\0';

					if (exists(token, accounts, num_accounts) != -1 && strcmp(token, account->username) != 0) {
						usernames[num_users++] = token;
					}
					token = strtok(NULL, ",");
				}

				printf("%d\n", num_users);

				for (int i = 0; i < num_users; i++) {
					strcpy(message.receiver, usernames[i]);
					messages[num_messages] = message;
					num_messages++;
					printf("test\n");
				}

				printf("%d\n", num_messages);

				for (int i = 0; i < num_messages; i++) {
					printf("%d. %s\n", i + 1, messages[i].subject);
				}

				Sleep(5000);

				save_messages(messages, "message");
				break;

			case 3:
				printf("Enter the subject: ");
				fgets(message.subject, MAX_STRING, stdin);
				fgets(message.subject, MAX_STRING, stdin);
				message.subject[strlen(message.subject) - 1] = '\0';

				printf("Enter your message: ");
				fgets(message.body, MAX_STRING, stdin);
				message.body[strlen(message.body) - 1] = '\0';

				strcpy(message.receiver, "Announcement");
				strcpy(message.sender, account->username);

				announcements[num_announcements] = message;
				num_announcements++;

				printf("Message sent.\n");
				save_messages(announcements, "announcement");
				break;
			default:
				printf("Invalid choice.\n");
				Sleep(1000);
				break;
			}
			break;
		case 2:
			system("cls");
			printf("Inbox\n");
			Message inbox[350];
			int num_inbox = 0;

			for (int i = 0; i < num_messages; i++) {
				if (strcmp(messages[i].receiver, account->username) == 0) {
					inbox[num_inbox++] = messages[i];
					printf("%d. %s from %s\n", num_inbox, messages[i].subject, messages[i]);
				}
			}

			if (num_inbox == 0) {
				printf("No messages.\n");
				Sleep(1000);
				break;
			}

			printf("Enter the number of the message you want to read: ");
			int mess_choice;
			scanf("%d", &mess_choice);

			if (mess_choice < 0 && mess_choice > num_inbox) {
				printf("Invalid choice.\n");
				Sleep(1000);
				break;
			}

			system("cls");
			printf("Subject: %s\n", inbox[mess_choice - 1].subject);
			printf("Sender: %s\n", inbox[mess_choice - 1].sender);
			printf("Message: %s\n\n", inbox[mess_choice - 1].body);
			printf("Do you want to reply? (y/n): ");

			char reply;
			scanf(" %c", &reply);

			if (reply == 'y') {
				system("cls");
				printf("Enter your message: ");
				fgets(message.body, MAX_STRING, stdin);
				fgets(message.body, MAX_STRING, stdin);
				message.body[strlen(message.body) - 1] = '\0';
				strcpy(message.receiver, inbox[mess_choice - 1].sender);
				strcpy(message.sender, account->username);
				strcpy(message.subject, inbox[mess_choice - 1].subject);
				messages[num_messages] = message;
				num_messages++;
				printf("Message sent.\n");
				save_messages(messages, "message");
			}
			Sleep(1000);
			break;
		case 3:
			system("cls");
			printf("Sent\n");
			int counter = 0;
			for (int i = 0; i < num_messages; i++) {
				if (strcmp(messages[i].sender, account->username) == 0) {
					counter++;
					printf("%d. %s to %s\n", counter, messages[i].subject, messages[i].receiver);
				}
			}

			if (counter == 0) {
				printf("No messages.\n");
				Sleep(1000);
				break;
			}

			printf("Press any key to continue...\n");
			_getch();
			break;

		case 4:
			system("cls");
			printf("Announcements\n");
			int ann_choice;

			for (int i = 0; i < num_announcements; i++) {
				printf("%d. %s\n", i + 1, announcements[i].subject);
			}

			printf("Enter the number of the announcement you want to read: ");
			scanf("%d", &ann_choice);

			system("cls");
			printf("Subject: %s\n", announcements[ann_choice - 1].subject);
			printf("Sender: %s\n", announcements[ann_choice - 1].sender);
			printf("Message: %s\n\n", announcements[ann_choice - 1].body);

			printf("Press any key to continue...\n");
			_getch();
			break;

		case 5:
			system("cls");
			edit_account_details(account);
			save_accounts(accounts, num_accounts);
			break;

		case 6:
			system("cls");
			change_security(account);
			save_accounts(accounts, num_accounts);
			break;
		case 7:
			system("cls");
			printf("Modify connections\n");
			printf("1. Add connection\n");
			printf("2. Remove connection\n");
			printf("3. View connections\n");
			printf("4. View user connections\n");
			printf("5. Back\n");
			printf("Enter your choice: ");
			int conn_choice;
			scanf("%d", &conn_choice);

			switch (conn_choice) {
			case 1:
				system("cls");
				printf("Add connection\n");
				add_conn(account, accounts, num_accounts);
				break;
			case 2:
				system("cls");
				printf("Remove connection\n");
				remove_conn(account, accounts, num_accounts);
				break;
			case 3:
				system("cls");
				printf("View connections\n");
				view_conn(account, accounts, num_accounts);
				break;
			case 4:
				system("cls");
				printf("View user connections\n");
				view_user_conn(account, accounts, num_accounts);
				break;
			case 5:
				break;
			default:
				printf("Invalid choice.\n");
				Sleep(1000);
				break;
			}
			break;
		case 8:
			system("cls");
			printf("Browse users\n");
			list_users(accounts, num_accounts);

			printf("Enter the number of the user you want to view: ");
			int user_choice;
			scanf("%d", &user_choice);

			if (user_choice < 0 && user_choice > num_accounts) {
				printf("Invalid choice.\n");
				Sleep(1000);
				break;
			}

			system("cls");
			view_account_details(&accounts[user_choice - 1]);

			printf("What do you want to do?\n");
			printf("1. Add connection\n");
			printf("2. Send message\n");
			printf("3. Back\n");

			int user_choice2;
			scanf("%d", &user_choice2);

			switch (user_choice2) {
			case 1:
				system("cls");
				printf("Add connection\n");
				strcpy(account->connections[account->num_of_connections], accounts[user_choice].username);
				account->num_of_connections++;
				printf("User successfully added as a connection.\n");
				save_accounts(accounts, num_accounts);
				Sleep(1000);
				break;
			case 2:
				system("cls");
				printf("Send message\n");
				printf("Enter your message: ");
				fgets(message.body, MAX_STRING, stdin);
				fgets(message.body, MAX_STRING, stdin);
				message.body[strlen(message.body) - 1] = '\0';
				strcpy(message.receiver, accounts[user_choice - 1].username);
				strcpy(message.sender, account->username);
				strcpy(message.subject, "No subject");
				messages[num_messages] = message;
				num_messages++;
				printf("Message sent.\n");
				save_messages(messages, "message");
				Sleep(1000);
				break;
			case 3:
				break;
			default:
				printf("Invalid choice.\n");
				Sleep(1000);
				break;
			}
			break;
		case 9:
			system("cls");
			printf("Logging out...\n");
			Sleep(1000);
			return;
		default:
			printf("Invalid choice.\n");
			Sleep(1000);
			break;
		}
	} while (choice != 9);
}

void list_users(Account accounts[], int num_accounts) {
	for (int i = 0; i < num_accounts; i++) {
		printf("%d. %s\n", i + 1, accounts[i].username);
	}
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
	num_announcements = load_messages(announcements, "announcement");
	num_messages = load_messages(messages, "message");

	do {
		system("cls");
		printf("GUMIPHONE\n");
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
			system("cls");
			printf("Create account\n");
			accounts[num_accounts] = create_account();
			num_accounts++;

			save_accounts(accounts, num_accounts);
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

