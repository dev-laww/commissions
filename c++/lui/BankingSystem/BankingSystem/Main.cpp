#include <iostream>
#include <Windows.h>
#include "BankingSystem.h"

using namespace std;

int main() {
	BankingSystem system;

	system.run();

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
            system.run();
        }

    } while (tolower(again) != 'n');

	return 0;
}