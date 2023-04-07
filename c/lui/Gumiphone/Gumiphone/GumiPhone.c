#include "Account.h"
#include "GumiPhone.h"
#include <Windows.h>

int main() {
	Account accounts[MAX_ACCOUNTS];
	int num_accounts = 0;
	Account acc = create_account();
	accounts[num_accounts] = acc;
	num_accounts++;

	change_security(&acc);

	return 0;
}