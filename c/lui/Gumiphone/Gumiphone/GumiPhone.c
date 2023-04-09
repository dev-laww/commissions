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

		);
	}

}


int main() {
	int choice;

	do {
		printf("\nGUMIPHONE\n");
		printf("---------------MAIN PAGE---------------\n");
		printf("||                                   ||\n");
		printf("||     [1] LOG-IN                    ||\n");
		printf("||     [2] CREATE ACCOUNT            ||\n");
		printf("||     [3] FORGOT PASSWORD           ||\n");
		printf("||     [4] EXIT                      ||\n");
		printf("||                                   ||\n");
		printf("---------------------------------------\n");
		printf("CHOICE:");
		scanf("%d", choice);

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

