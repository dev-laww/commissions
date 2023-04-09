#include <stdlib.h>
#include <time.h>

#ifndef ACCOUNT_H
#define ACCOUNT_H
#define MAX_PASS 21
#define MAX_STRING 1001
#define MAX_CONNECTIONS 29
#define MAX_ACCOUNTS 31

typedef struct Account {
	char name[MAX_STRING];
	char user_name[41];
	char pass[MAX_PASS];
	char sequrity_question[MAX_STRING];
	char sequrity_answer[MAX_STRING];
	char description[MAX_STRING];
	char connections[MAX_CONNECTIONS][41];
	int num_of_connections;
} Account;


Account create_account();
void view_account_details(Account* acc);

void edit_account_details(Account* acc);
void change_security(Account* acc);
void add_conn(Account* acc, Account accounts[], int num_accounts);
void remove_conn(Account* acc, Account accounts[], int num_accounts);
void view_conn(Account* acc, Account accounts[], int num_accounts);
int exists(char username[41], Account accounts[], int num_accounts);

char* generate_sec_question();

#endif // !ACCOUNT_H
