#include "BankAccount.h"
#include <iostream>

using namespace std;


BankAccount::BankAccount() {
    owner = "Unknown";
    balance = 0;
}

BankAccount::BankAccount(string name, double balance) {
    owner = name;
    this->balance = balance;
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