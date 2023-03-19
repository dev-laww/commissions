#ifndef BANKACCOUNTLIST_H
#define BANKACCOUNTLIST_H

#include <iostream>
#include "BankAccount.h"

using namespace std;

struct BankAccountNode {
    BankAccount account;
    BankAccountNode* next;
    BankAccountNode* prev;

    BankAccountNode() : account(), next(nullptr), prev(nullptr) {}
    BankAccountNode(const BankAccount& acc) : account(acc), next(nullptr), prev(nullptr) {}
};

class BankAccountList {
private:
    BankAccountNode* head;
    BankAccountNode* tail;

public:
    BankAccountList() : head(nullptr), tail(nullptr) {}

    void addAccount(const BankAccount& account) {
        BankAccountNode* newNode = new BankAccountNode(account);

        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail->next = newNode;
            newNode->prev = tail;
            tail = newNode;
        }
    }

    void removeAccount(const string& accountName) {
        BankAccountNode* current = head;

        while (current != nullptr && current->account.getOwner() != accountName) {
            current = current->next;
        }

        if (current != nullptr) {
            if (current == head) {
                head = current->next;
            }
            else {
                current->prev->next = current->next;
            }

            if (current == tail) {
                tail = current->prev;
            }
            else {
                current->next->prev = current->prev;
            }

            delete current;
        }
    }

    BankAccount* findAccount(const string& accountName) {
        BankAccountNode* current = head;

        while (current != nullptr && current->account.getOwner() != accountName) {
            current = current->next;
        }
        
        if (current == nullptr) {
            cout << "Acccount not found!" << endl;
            return nullptr;
        }

        BankAccount account = current->account;

        return &account;
    }

    BankAccount* login(const string& accountUsername, const string& pin) {
        BankAccountNode* current = head;

        while (current != nullptr && current->account.getUsername() != accountUsername) {
            current = current->next;
        }

        if (current == nullptr) {
            cout << "Acccount not found!" << endl;
            return nullptr;
        }

        BankAccount account = current->account;

        if (!(account.getPin() == pin)) {
            cout << "Wrong pin!" << endl;
            return nullptr;
        }

        return &account;
    }

    void printAllAccounts() const {
        BankAccountNode* current = head;
        
        int counter = 0;

        while (current != nullptr) {
            counter++;

            BankAccount account = current->account;

            cout << counter << ". " << account.getOwner() << " - " << account.getBalance() << endl;
            current = current->next;
        }
    }

    ~BankAccountList() {
        BankAccountNode* current = head;

        while (current != nullptr) {
            BankAccountNode* next = current->next;
            delete current;
            current = next;
        }
    }
};


#endif // !BANKACCOUNTLIST_H