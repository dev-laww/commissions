#ifndef BANKINGSYSTEM_H
#define BANKINGSYSTEM_H

#include "BankAccountList.h"

class BankingSystem {
public:
	BankingSystem();
	void login();
	void logout();
	void run();

	// client
	void withdraw();
	void deposit();
	void transfer();

	// admin
	void addAccount();
	void removeAccount();
private:
	BankAccountList accounts;
	BankAccount* currentAccount;
	string adminUsernames[5];
	string adminPasswords[5];
	bool isAdmin;
};

#endif // !BANKINGSYSTEM_H

