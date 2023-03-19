#include "BankAccount.h"
#include <iostream>

using namespace std;


BankAccount::BankAccount() {
    owner = "Unknown";
    balance = 0;
    pin = "0000";
    username = "unkown";
}

BankAccount::BankAccount(string name, double balance, string username, string pin) {
    owner = name;
    this->balance = balance;
    this->username = username;
    this->pin = pin;
}

void BankAccount::deposit(double amount) {
    balance += amount;
    cout << "Deposit Success!" << endl;

    writeTransaction("Withdraw", amount);
}

void BankAccount::withdraw(double amount) {
    if (balance < amount) {
        cout << "Not enough balance" << endl;
        return;
    }

    balance -= amount;
    cout << "Withdraw Success!" << endl;

    writeTransaction("Withdraw", amount);
}

void BankAccount::transfer(double amount, BankAccount &receipient) {
    if (!(balance >= amount)) {
        cout << "Not enough balance!" << endl;
        return;
    }

    balance -= amount;
    receipient.balance += amount;

    cout << "Transfer Success!" << endl;
    writeTransaction("Transfer", amount, receipient.getOwner());
}

void BankAccount::checkBalance() {
    cout << "Balance: $" << getBalance() << endl;

    writeTransaction("Check Balance");
}

double BankAccount::getBalance() const {
    return balance;
}

string BankAccount::getOwner() const {
    return owner;
}

string BankAccount::getPin() const {
    return pin;
}

string BankAccount::getUsername() const {
    return username;
}