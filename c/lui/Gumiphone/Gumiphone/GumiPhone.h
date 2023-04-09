#include "Admin.h"
#include "User.h"
#include "Account.h"
#include "Messages.h"

#pragma once
#ifndef GUMIPHONE_H
#define GUMIPHONE_H

int load_accounts(Account accounts[]);
void save_accounts(Account accounts[]);

void list_users(Account accounts[], int num_accounts);

void load_sec_questions(char questions[]);
void save_sec_questions(char questions[]);

void load_admin(char admin[2][41]);
void save_admin(char admin[2][41]);

int load_messages(Message messages[], char mode[]);
void save_messages(Message messages[], char mode[]);

int load_messages(Message messages[], char mode[]);
void save_messages(Message messages[], char mode[]);

void login();

void admin_page();
void account_page(Account* acc);

void manage_users();
void manage_messages();

#endif // !GUMIPHONE_H
