#include "BankAccount.h"
#include <iostream>

using namespace std;

int main() {
    BankAccount myAccount("John Smith", 1000.0);
    myAccount.deposit(500.0);
    myAccount.withdraw(200.0);
    cout << "Account owner: " << myAccount.getOwner() << endl;
    cout << "Account balance: " << myAccount.getBalance() << endl;
    BankAccount account("Test", 500.0);
    myAccount.transfer(1000, account);
    cout << "Smith balance: " << myAccount.getBalance() << endl;
    cout << "Acconnt " << account.getBalance() << endl;
    return 0;
}