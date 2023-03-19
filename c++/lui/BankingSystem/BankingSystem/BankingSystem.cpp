#include <iostream>
#include <Windows.h>
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

            if (!success) {
                cout << "Invalid username or password. Please try again." << endl;
                Sleep(500);
            }

            continue;
        }

        // check if client username and password match
        BankAccountNode* node = accounts.login(username, password);

        if (node != nullptr) {
            cout << "Login successful!" << endl;
            success = true;
            currentAccountNode = node;

            cout << node->account.getOwner();
        }


        if (!success) {
            cout << "Invalid username or password. Please try again." << endl;
            Sleep(500);
        }
    } while (!success);
}

void BankingSystem::logout() {
    currentAccountNode = nullptr;
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
                Sleep(1000);
                break;
            case 2:
                removeAccount();
                Sleep(2000);
                break;
            case 3:
                system("cls");
                cout << "Accounts List:" << endl;
                accounts.printAllAccounts();
                Sleep(2000);
                break;
            case 4:
                logout();
                break;
            default:
                cout << "Invalid Choice!" << endl;
                Sleep(2000);
                break;
            }
        } while (choice != 4);

        return;
    }


    do {
        system("cls");
        cout << "Welcome " << currentAccountNode->account.getOwner() << "!" << endl;
        cout << "What would you like to do?" << endl
            << "[1] Check Balance" << endl
            << "[2] Deposit" << endl
            << "[3] Withdraw" << endl
            << "[4] Transfer" << endl
            << "[5] Logout" << endl
            << "Enter your choice: ";

        cin >> choice;

        switch (choice) {
        default:
            switch (choice)
            {
            case 1:
                cout << "Current Balance: " << currentAccountNode->account.getBalance() << endl;
                Sleep(2000);
                break;
            case 2:
                deposit();
                Sleep(2000);
                break;
            case 3:
                withdraw();
                Sleep(2000);
                break;
            case 4:
                transfer();
                Sleep(2000);
                break;
            case 5:
                logout();
                Sleep(2000);
                break;
            default:
                cout << "Invalid Choice!" << endl;
                Sleep(2000);
                break;
            }
        }
    } while (choice != 4);
}

void BankingSystem::addAccount() {
    system("cls");
    string name, pin, username;
    cout << "Enter bank account name: ";
    getline(cin, name);
    getline(cin, name);
    cout << "Enter pin: ";
    cin >> pin;

    do {
        cout << "Enter username: ";
        cin >> username;

        if (accounts.usernameExists(username)) {
            cout << "Username Exists! Try again." << endl;
        }

    } while (accounts.usernameExists(username));

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
    BankAccountNode* node = nullptr;
    system("cls");
    do {
        cout << "Enter bank account name: ";
        cin >> name;

        node = accounts.findAccount(name);
    } while (node == nullptr);

    do {
        cout << "Enter pin: ";
        cin >> pin;

        if (pin != node->account.getPin()) {
            cout << "Incorrect pin! Try again." << endl;
        }
    } while (pin != node->account.getPin());

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

    currentAccountNode->account.deposit(amount);
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

        if (pin != currentAccountNode->account.getPin()) {
            cout << "Incorrect pin! Try again." << endl;
        }
    } while (pin != currentAccountNode->account.getPin());

    currentAccountNode->account.withdraw(amount);
}

void BankingSystem::transfer() {
    double amount;
    string receipientName, pin;
    BankAccountNode* receipient = nullptr;

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

        if (pin != currentAccountNode->account.getPin()) {
            cout << "Incorrect pin! Try again." << endl;
        }
    } while (pin != currentAccountNode->account.getPin());

    currentAccountNode->account.transfer(amount, receipient->account);
}