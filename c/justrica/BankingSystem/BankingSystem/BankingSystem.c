#include "Account.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ACCOUNTS 100
#define MAX_LINE_LENGTH 255

Account* create_account() {
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

    return account;
}


int main() {
    Account* account = create_account();
    printf("%s", account->account_no);
}
