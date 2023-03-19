#include <iostream>
#include "BankingSystem.h"

using namespace std;

BankingSystem::BankingSystem() {
	for (int i = 0; i < 5; i++) {
        adminUsernames[i] = "admin" + to_string(i + 1);
		adminPasswords[i] = "password" + to_string(i + 1);
	}
}

void BankingSystem::login() {
    string username, password;
    int choice;
    bool success = false;
    cout << "Login as:" << endl
        << "[1] Admin" << endl
        << "[2] User" << endl
        << "Enter your choice: ";
    cin >> choice;

    do {
        if (choice != 1 && choice != 2) {
            cout << "Invalid choice!" << endl
                << "Enter your choice: ";
            cin >> choice;
            system("cls");
        }
        isAdmin = choice == 1 ? true : false;
    } while (choice != 1 && choice != 2);
    
    do {
        system("cls");
        cout << "Enter username: ";
        cin >> username;
        cout << "Enter password: ";
        cin >> password;

        if (isAdmin) {
            // check if admin username and password match
            for (int i = 0; i < 5; i++) {
                if (username == adminUsernames[i] && password == adminPasswords[i]) {
                    cout << "Login successful!" << endl;
                    success = true;
                    break;
                }
            }
            continue;
        }

        // check if client username and password match
        BankAccount* account = accounts.login(username, password);

        if (account != nullptr) {
            cout << "Login successful!" << endl;
            success = true;
            currentAccount = account;
        }
        

        if (!success) {
            cout << "Invalid username or password. Please try again." << endl;
        }
    } while (!success);
}

void BankingSystem::logout() {
    currentAccount = nullptr;
    isAdmin = false;
    cout << "Logged out successfully." << endl;
}


void BankingSystem::run() {
    int choice;

    cout << "Welcome to Banking System!" << endl
        << "Please login before proceeding . . ." << endl << endl;

    login();

    if (isAdmin) {
        do {
            system("cls");
            cout << "What would you like to do?" << endl
                << "[1] Create Account" << endl
                << "[2] Delete Account" << endl
                << "[3] List All Accounts" << endl
                << "[4] Logout" << endl
                << "Enter your choice: ";
            cin >> choice;

            switch (choice)
            {
            case 1:
                addAccount();
                break;
            case 2:
                removeAccount();
                break;
            case 3:
                cout << "Accounts List:" << endl;
                accounts.printAllAccounts();
                break;
            case 4:
                logout();
            default:
                cout << "Invalid Choice!" << endl;
                break;
            }
        } while (choice != 4);

        return;
    }

    cout << "Welcome " << currentAccount->getOwner() << "!" << endl;
    do {
        system("cls");
        cout << "What would you like to do?" << endl
            << "[1] Check Balance" << endl
            << "[2] Deposit" << endl
            << "[3] Withdraw" << endl
            << "[4] Transfer" << endl
            << "Enter your choice: ";

        cin >> choice;

        switch (choice) {
        default:
            switch (choice)
            {
            case 1:
                cout << "Current Balance: " << currentAccount->getBalance() << endl;
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                logout();
                break;
            default:
                cout << "Invalid Choice!" << endl;
                break;
            }
        }
    } while (choice != 4);
}

void BankingSystem::addAccount() {
    system("cls");
    string name, pin, username;
    cout << "Enter bank account name: ";
    cin >> name;
    cout << "Enter username: ";
    cin >> username;
    cout << "Enter pin: ";
    cin >> pin;

    while (pin.length() != 4) {
        system("cls");
        cout << "Invalid pin length! Try again." << endl
            << "Enter new pin: ";
        cin >> pin;
    }

    BankAccount account(name, 0.0, username, pin);
    
    accounts.addAccount(account);
}

void BankingSystem::removeAccount() {
    string name, pin;
    BankAccount* account = nullptr;
    system("cls");
    do {
        cout << "Enter bank account name: ";
        cin >> name;

        account = accounts.findAccount(name);
    } while (account == nullptr);

    do {
        cout << "Enter pin: ";
        cin >> pin;

        if (pin != account->getPin()) {
            cout << "Incorrect pin! Try again." << endl;
        }
    } while (pin != account->getPin());

    accounts.removeAccount(name);

}

void BankingSystem::deposit() {
    double amount;

    system("cls");
    do {
        cout << "Enter amount: ";
        cin >> amount;

        if (amount <= 0) {
            cout << "Invalid amount!" << endl;
        }
    } while (amount <= 0);

    currentAccount->deposit(amount);
}

void BankingSystem::withdraw() {
    double amount;
    string pin;

    system("cls");
    do {
        cout << "Enter amount: ";
        cin >> amount;

        if (amount <= 0) {
            cout << "Invalid amount!" << endl;
        }
    } while (amount <= 0);

    do {
        cout << "Enter pin: ";
        cin >> pin;

        if (pin != currentAccount->getPin()) {
            cout << "Incorrect pin! Try again." << endl;
        }
    } while (pin != currentAccount->getPin());

    currentAccount->withdraw(amount);
}

void BankingSystem::transfer() {
    double amount;
    string receipientName, pin;
    BankAccount* receipient = nullptr;

    system("cls");
    do {
        cout << "Enter amount: ";
        cin >> amount;

        if (amount <= 0) {
            cout << "Invalid amount!" << endl;
        }
    } while (amount <= 0);

    do {
        cout << "Enter bank account name: ";
        cin >> receipientName;

        receipient = accounts.findAccount(receipientName);
    } while (receipient == nullptr);

    do {
        cout << "Enter pin: ";
        cin >> pin;

        if (pin != currentAccount->getPin()) {
            cout << "Incorrect pin! Try again." << endl;
        }
    } while (pin != currentAccount->getPin());

    currentAccount->transfer(amount, *receipient);
}