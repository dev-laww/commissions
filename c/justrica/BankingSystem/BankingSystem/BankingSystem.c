#include "Account.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ACCOUNTS 500
#define MAX_LINE_LENGTH 255

Account accounts[MAX_ACCOUNTS];
int num_accounts = 0;
char filepath[] = "C:\\Users\\roren\\Documents\\Projects\\commissions\\c\\justrica\\BankingSystem\\x64\\Debug\\accounts.csv";

void create_account() {
	char account_no[13], name[50], gender[7], contact_no[12], address[150];
	double balance;
	// Get user input for account information

	printf("Enter account name: ");
	fgets(name, sizeof(name), stdin);
	fgets(name, sizeof(name), stdin);
	name[strcspn(name, "\n")] = '\0';


	printf("Enter gender (Male/Female): ");
	scanf_s("%s", gender, sizeof(gender));

	printf("Enter phone number: ");
	scanf_s("%s", contact_no, sizeof(contact_no));

	printf("Enter address: ");
	fgets(address, sizeof(address), stdin);
	fgets(address, sizeof(address), stdin);
	address[strcspn(address, "\n")] = '\0';

	printf("Enter account balance: ");
	scanf_s("%lf", &balance);

	generate_account_no(&account_no);

	if (num_accounts == MAX_ACCOUNTS) {
		printf("Error: Unable to create account. Maximum number of accounts reached.\n");
		return;
	}

	// Create a new account
	Account account;
	strcpy_s(account.account_no, sizeof(account.account_no), account_no);
	strcpy_s(account.name, sizeof(account.name), name);
	strcpy_s(account.gender, sizeof(account.gender), gender);
	strcpy_s(account.contact_no, sizeof(account.contact_no), contact_no);
	strcpy_s(account.address, sizeof(account.address), address);
	account.balance = balance;
	accounts[num_accounts] = account;
	num_accounts++;

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
		fprintf(
			fp,
			"%s,%s,%s,%s,%s,%.2f\n",
			accounts[i].account_no,
			accounts[i].name,
			accounts[i].gender,
			accounts[i].contact_no,
			accounts[i].address,
			accounts[i].balance
		);
	}

	fclose(fp);
}

int load_accounts(Account accounts[]) {
	FILE* fp;
	char line[MAX_LINE_LENGTH];
	int num_accounts = 0;

	errno_t error = fopen_s(&fp, filepath, "r");

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
		error = strcpy_s(accounts[num_accounts].address, 150, token);

		token = strtok_s(NULL, ",", &next_token);
		accounts[num_accounts].balance = strtod(token, &next_token);

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
	do {
		num_accounts = load_accounts(accounts);

		if (num_accounts == -1) {
			return 1;
		}

		list_accounts();

		printf("MENU\n");
		printf("[A] Admin Menu\n");
		printf("[B] User Menu\n");
		printf("[C] Exit\n");
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
					save_accounts(accounts, num_accounts, filepath);
					break;
				default:
					printf("Invalid choice!\n");
					break;
				}

			} while (choice != 'd' && choice != 'D');

		}
		else if (choice == 'b' || choice == 'B') {
			char account_no[13];
			int account_index = -1;

			printf("Enter account number: ");
			scanf_s("%s", account_no, sizeof(account_no));

			for (int i = 0; i < num_accounts; i++) {
				if (strcmp(accounts[i].account_no, account_no) != 0)
					continue;

				printf("Welcome %s!\n", accounts[i].name);
				account_index = i;
				break;
			}

			if (account_index == -1) {
				printf("Account not found!\n");
				return 1;
			}

			do {
				double amount;

				printf("USER MENU\n");
				printf("[A] Check Balance\n");
				printf("[B] Deposit\n");
				printf("[C] Withdraw\n");
				printf("[D] Account Details\n");
				printf("[E] Exit\n");
				printf("Enter your choice: ");
				scanf_s("%c", &choice, sizeof(choice));
				scanf_s("%c", &choice, sizeof(choice));

				switch (choice)
				{
				case 'a':
				case 'A':
					check_balance(&accounts[account_index]);
					break;
				case 'b':
				case 'B':
					printf("Enter amount to deposit: ");
					scanf_s("%lf", &amount);
					deposit(&accounts[account_index], amount);
					break;
				case 'c':
				case 'C':
					printf("Enter amount to withdraw: ");
					scanf_s("%lf", &amount);
					withdraw(&accounts[account_index], amount);
					break;
				case 'd':
				case 'D':
					details(&accounts[account_index]);
					break;
				case 'e':
				case 'E':
					printf("Exiting...\n");
					save_accounts(accounts, num_accounts, filepath);
					break;
				default:
					printf("Invalid choice!\n");
					break;
				}

			} while (choice != 'e' && choice != 'E');
		}
		else if (choice == 'c' || choice == 'C') {
			printf("Exiting...\n");
			save_accounts(accounts, num_accounts, filepath);
		}
		else {
			printf("Invalid choice!\n");
		}
	} while (choice != 'c' && choice != 'C');

	return 0;
}
