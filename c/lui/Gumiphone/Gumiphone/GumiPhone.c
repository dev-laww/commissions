#include "Account.h"
#include "GumiPhone.h"
#include <Windows.h>

Account accounts[MAX_ACCOUNTS];
int num_accounts = 0;
Message messages[150];
int num_messages = 0;
Message announcements[150];
int num_announcements = 0;


int main() {
	Account acc = create_account();
	accounts[num_accounts] = acc;
	num_accounts++;

	change_security(&acc);

	return 0;
}