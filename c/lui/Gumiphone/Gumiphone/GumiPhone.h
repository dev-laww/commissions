#include "Admin.h"
#include "User.h"
#include "Account.h"
#include "Messages.h"

#pragma once
#ifndef GUMIPHONE_H
#define GUMIPHONE_H

int load_accounts(Account accounts[]);
void save_accounts(Account accounts[]);

void list_users(Account accounts[]);

int load_messages(Message messages[], char mode[]);
void save_messages(Message messages[], char mode[]);

void send_personal_message(Message message, Account* account, Account accounts[], int num_accounts);
void send_group_message(Message message, Account* account, Account accounts[], int num_accounts);
void send_announcement(Message message, Account* account, int num_accounts);

int load_messages(Message messages[], char mode[]);
void save_messages(Message messages[], char mode[]);

void login();
void logout();

void admin_page();
void account_page(Account* acc);

#endif // !GUMIPHONE_H
