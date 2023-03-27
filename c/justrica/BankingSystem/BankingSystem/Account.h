#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#ifndef ACCOUNT_H
#define ACCOUNT_H
#define ACCOUNT_NO_LENGTH 12

typedef struct Account {
	char account_no[ACCOUNT_NO_LENGTH];
	char name[50];
	char gender[6];
	char contact_no[11];
	char address[150];
	double balance;
} Account;

void generate_account_no(char* account_no);
void withdraw(Account* account, double amount);
void deposit(Account* account, double amount);
void check_balance(Account* account);

#endif

