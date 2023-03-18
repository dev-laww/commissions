#ifndef BANK_ACCOUNT_H
#define BANK_ACCOUNT_H

#include <iostream>
#include <string>
#include <fstream>

using namespace std;

struct Transaction {
    string account;
    string type;
    string recipient;
    double amount;
};

class BankAccount {
public:
	BankAccount(); // default constructor
	BankAccount(string name, double balance); // constructor with parameters
	void deposit(double amount);
	void withdraw(double amount);
	void transfer(double amount, BankAccount& receipient);
    void checkBalance();
	double getBalance() const;
	string getOwner() const;
private:
	string owner;
	double balance;
	void writeTransaction(const string& transactionType, double amount = 0.0, string receipient = "") {
        time_t now = time(nullptr);
        tm localTime;
        localtime_s(&localTime, &now);

        char buffer[80];
        strftime(buffer, 80, "%Y-%m-%d %H:%M:%S", &localTime);

        // Create transaction receipt
        Transaction transaction;
        transaction.account = owner;
        transaction.type = transactionType;
        transaction.amount = amount;
        transaction.recipient = receipient;

        // Write receipt to file
        ofstream receiptFile;

        receiptFile.open("transactions.txt", ios_base::app);

        if (receiptFile) {
            cout << "Writing receipt\n";
            receiptFile << "------------------------------------------\n";
            receiptFile << buffer << endl << endl;

            receiptFile << "Type: " << transaction.type << endl;
            receiptFile << "Account: " << transaction.account << "\n";
            if (transaction.recipient != "") receiptFile << "Recipient: " << transaction.recipient << endl;
            if (transaction.amount != 0.0) receiptFile << "Amount: $" << transaction.amount << "\n";

            receiptFile.close();
        }
    }
};

#endif // BANK_ACCOUNT_H