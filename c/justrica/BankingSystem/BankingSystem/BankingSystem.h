#include "Account.h"

#ifndef BANKING_SYSTEM
#define BANKING_SYSTEM

void save_accounts(Account* accounts[], int num_accounts, const char* filename);
int load_accounts(Account* accounts[]);
void create_account();
void delete_account();
void list_accounts();
Account* load_account(
	char* account_no[],
	char* name[],
	char* gender[],
	char* contact_no[],
	char* address,
	double balance
);

#endif // !BANKING_SYSTEM

