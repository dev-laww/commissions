#include <iostream>
#include <Windows.h>
#include "BankingSystem.h"

using namespace std;

int main() {
	BankingSystem bankingSystem;

	bankingSystem.run();

    char again;

    do {
        cout << "Login again? (y/n) ";
        cin >> again;

        if (tolower(again) != 'y' && tolower(again) != 'n') {
            cout << "Invalid choice! Try again." << endl;
            Sleep(500);
            continue;
        }

        if (tolower(again) == 'y') {
            system("cls");
            bankingSystem.run();
        }

    } while (tolower(again) != 'n');

	return 0;
}