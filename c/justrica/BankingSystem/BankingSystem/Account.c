#include "Account.h"

void generate_account_no(char* account_no) {
    // Seed the random number generator with the current time
    srand((unsigned int)time(NULL));

    // Generate a random 10-digit number
    int rand_num = rand() % 10000000000;

    // Format the account number as a string with leading zeros
    sprintf_s(account_no, ACCOUNT_NO_LENGTH + 1, "%0*d", ACCOUNT_NO_LENGTH, rand_num);
}

void check_balance(Account* account) {
    printf("Account balance: %.2f\n", account->balance);
}

void deposit(Account* account, double amount) {
    account->balance += amount;
    printf("Success!\n");
}

void withdraw(Account* account, double amount) {
    if (account->balance < amount) {
        printf("Error: Insufficient funds.\n");
        return;
    }

    account->balance -= amount;
    printf("Success!\n");
}

void details(Account* account) {
    printf("Account No. %s\n\n", account->account_no);
    printf("Name: %s\n", account->name);
    printf("Gender: %s\n", account->gender);
    printf("Address: %s\n", account->address);
    printf("Contact number: %s\n", account->contact_no);
}
