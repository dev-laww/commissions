#include "Account.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ACCOUNTS 500
#define MAX_LINE_LENGTH 255

Account accounts[MAX_ACCOUNTS];
int num_accounts = 0;
const char* admin_username = "admin";
const char* admin_password = "admin";

void create_account() {
	// Get user input for account information
	printf("\nEnter name: ");
	scanf_s("%s", accounts[num_accounts].name, sizeof(accounts[num_accounts].name));

	printf("Enter gender: ");
	scanf_s("%s", accounts[num_accounts].gender, sizeof(accounts[num_accounts].gender));

	printf("Enter contact number: ");
	scanf_s("%s", accounts[num_accounts].contact_no, sizeof(accounts[num_accounts].contact_no));

	printf("Enter address: ");
	scanf_s("%s", accounts[num_accounts].address, sizeof(accounts[num_accounts].address));

	printf("Enter initial balance: ");
	scanf_s("%lf", &accounts[num_accounts].balance);

	// Generate a random account number
	generate_account_no(accounts[num_accounts].account_no);

	printf("\nAccount created successfully!\n");
}

void save_accounts(Account accounts[], int num_accounts, const char* filename) {
	FILE* fp;
	fopen_s(&fp, filename, "w");

	if (fp == NULL) {
		printf("Error: Unable to open file '%s'\n", filename);
		return;
	}

	for (int i = 0; i < num_accounts; i++) {
		fprintf(fp, "%s,%s,%s,%s,%.2f\n", accounts[i].account_no, accounts[i].name, accounts[i].gender, accounts[i].contact_no, accounts[i].balance);
	}

	fclose(fp);
}

int load_accounts(Account accounts[]) {
	FILE* fp;
	char line[MAX_LINE_LENGTH];
	int num_accounts = 0;

	errno_t error = fopen_s(&fp, "C:\\Users\\roren\\Documents\\Projects\\commissions\\c\\justrica\\BankingSystem\\x64\\Debug\\accounts.csv", "r");

	if (error != 0) {
		printf("Error: Unable to open file 'accounts.csv'\n");
		return -1;
	}

	char* next_token = NULL;

	while (fgets(line, MAX_LINE_LENGTH, fp) != NULL) {
		// Split the line into tokens
		char* next_token;
		char* token = strtok_s(line, ",", &next_token);
		error = strcpy_s(accounts[num_accounts].account_no, 13, token);

		token = strtok_s(NULL, ",", &next_token);
		error = strcpy_s(accounts[num_accounts].name, 50, token);

		token = strtok_s(NULL, ",", &next_token);
		error = strcpy_s(accounts[num_accounts].gender, 7, token);

		token = strtok_s(NULL, ",", &next_token);
		error = strcpy_s(accounts[num_accounts].contact_no, 12, token);

		token = strtok_s(NULL, ",", &next_token);
		error = strcpy_s(accounts[num_accounts].address, 100, token);

		token = strtok_s(NULL, ",", &next_token);
		accounts[num_accounts].balance = atoi(token);
		
		num_accounts++;
	}

	fclose(fp);
	return num_accounts;
}

void list_accounts() {
	printf("Accounts list:\n\n");
	for (int i = 0; i < num_accounts; i++) {
		printf(
			"%d. %s - %s\n",
			i + 1,
			accounts[i].account_no,
			accounts[i].name
		);
		printf("\tBalance: %.2f\n", accounts[i].balance);
	}
}

void delete_account() {
	char account_no[13];
	printf("\nEnter account number: ");
	scanf_s("%s", account_no, sizeof(account_no));

	for (int i = 0; i < num_accounts; i++) {
		if (strcmp(accounts[i].account_no, account_no) == 0) {
			// Found the account to delete
			for (int j = i; j < num_accounts; j++) {
				accounts[j] = accounts[j + 1];
			}

			num_accounts--;
			return;
		}
	}

	printf("\nAccount not found!\n");
}

int main() {
	char choice;
	num_accounts = load_accounts(accounts);

	if (num_accounts == -1) {
		return 1;
	}

	list_accounts();

	printf("MENU\n");
	printf("[A] Admin Menu\n");
	printf("[B] User Menu\n");
	printf("Enter your choice: ");
	scanf_s("%c", &choice, sizeof(choice));

	if (choice == 'A' || choice == 'a') {
		do {
			scanf_s("%c", &choice, sizeof(choice));

			printf("ADMIN MENU\n");
			printf("[A] Create account\n");
			printf("[B] Delete account\n");
			printf("[C] List all accounts\n");
			printf("[D] Exit\n");
			printf("Enter your choice: ");
			scanf_s("%c", &choice, sizeof(choice));

			switch (choice)
			{
			case 'a':
			case 'A':
				create_account();
				break;
			case 'b':
			case 'B':
				delete_account();
				break;
			case 'c':
			case 'C':
				list_accounts();
				break;
			case 'd':
			case 'D':
				printf("Exiting...\n");
				break;
			default:
				printf("Invalid choice!\n");
				break;
			}

			if (choice == 'd' || choice == 'D')
				break;

		} while (choice != 'd' || choice != 'D');

	}
	else if (choice == 'b' || choice == 'B') {
		char account_no[13];

		printf("Enter account number: ");
		scanf_s("%s", account_no, sizeof(account_no));

		printf("USER MENU\n");
		printf("[A] Check Balance\n");
		printf("[B] Deposit\n");
		printf("[C] Withdraw\n");
		printf("[D] Exit\n");
		scanf_s("%c", &choice, sizeof(choice));

		switch (choice)
		{
		case 'a':
		case 'A':

			break;
		case 'b':
		case 'B':
			break;
		case 'c':
		case 'C':
			break;
		default:
			break;
		}
	}
	else {
		printf("Invalid choice!\n");
	}

	return 0;
}
