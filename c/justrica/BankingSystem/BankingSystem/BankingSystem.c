#include "Account.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ACCOUNTS 500
#define MAX_LINE_LENGTH 255

Account* accounts[MAX_ACCOUNTS];
int num_accounts = 0;

void create_account() {
    Account* account = malloc(sizeof(Account));

    // Get user input for account information
    printf("\nEnter name: ");
    scanf_s("%s", account->name, sizeof(account->name));

    printf("Enter gender: ");
    scanf_s("%s", account->gender, sizeof(account->gender));

    printf("Enter contact number: ");
    scanf_s("%s", account->contact_no, sizeof(account->contact_no));

    printf("Enter address: ");
    scanf_s("%s", account->address, sizeof(account->address));

    printf("Enter initial balance: ");
    scanf_s("%lf", &account->balance);

    // Generate a random account number
    generate_account_no(account->account_no);

    accounts[num_accounts++] = account;

    printf("\nAccount created successfully!\n");
}

void save_accounts(Account* accounts[], int num_accounts, const char* filename) {
    FILE* fp;
    fopen_s(&fp, filename, "w");

    if (fp == NULL) {
        printf("Error: Unable to open file '%s'\n", filename);
        return;
    }

    for (int i = 0; i < num_accounts; i++) {
        fprintf(fp, "%s,%s,%s,%s,%.2f\n", accounts[i]->account_no, accounts[i]->name, accounts[i]->gender, accounts[i]->contact_no, accounts[i]->balance);
    }

    fclose(fp);
}

Account* load_account(char* account_no[], char* name[], char* gender[], char* contact_no[], char* address, double balance) {
    Account* account = malloc(sizeof(Account));

    strcpy_s(account->account_no, sizeof(account->account_no), account_no);
    strcpy_s(account->name, sizeof(account->name), name);
    strcpy_s(account->gender, sizeof(account->gender), gender);
    strcpy_s(account->contact_no, sizeof(account->contact_no), contact_no);
    strcpy_s(account->address, sizeof(account->address), address);

    account->balance = balance;

    return account;
}

int load_accounts(Account* accounts[]) {
    FILE* fp;
    char line[MAX_LINE_LENGTH];
    int num_accounts = 0;

    fopen_s(&fp, "accounts.csv", "r");

    if (fp == NULL) {
        printf("Error: Unable to open file 'accounts.csv'\n");
        return 0;
    }

    while (fgets(line, MAX_LINE_LENGTH, fp) != NULL) {
        char* account_no = strtok_s(line, ",", NULL);
        char* name = strtok_s(NULL, ",", NULL);
        char* gender = strtok_s(NULL, ",", NULL);
        char* contact_no = strtok_s(NULL, ",", NULL);
        char* address = strtok_s(NULL, ",", NULL);
        char* balance_str = strtok_s(NULL, ",", NULL);
        double balance = atof(balance_str);

        accounts[num_accounts] = load_account(account_no, name, gender, contact_no, address, balance);
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
            accounts[i]->account_no,
            accounts[i]->name
        );
        printf("\tBalance: %.2f", accounts[i]->balance);
    }
}

void delete_account() {
    char account_no[13];
    printf("\nEnter account number: ");
    scanf_s("%s", account_no, sizeof(account_no));

    for (int i = 0; i < num_accounts; i++) {
        if (strcmp(accounts[i]->account_no, account_no) == 0) {
            // Found the account to delete
            free(accounts[i]);
            accounts[i] = NULL;

            // Shift all accounts after this one back by one position
            for (int j = i + 1; j < num_accounts; j++) {
                accounts[j - 1] = accounts[j];
            }
            num_accounts--;

            printf("\nAccount deleted successfully!\n");
            return;
        }
    }

    printf("\nAccount not found!\n");
}

int main() {
  
}
