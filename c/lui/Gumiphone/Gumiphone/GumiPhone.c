#include "Account.h"
#include "GumiPhone.h"
#include <Windows.h>

Account accounts[MAX_ACCOUNTS];
Message messages[150];
Message announcements[150];
int num_accounts = 0;


int main() {
	Account acc = create_account();
	accounts[num_accounts] = acc;
	num_accounts++;

	change_security(&acc);

	return 0;
}