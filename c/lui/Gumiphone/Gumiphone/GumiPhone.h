#include "Admin.h"
#include "User.h"
#include "Account.h"
#include "Messages.h"

#pragma once
#ifndef GUMIPHONE_H
#define GUMIPHONE_H

int load_accounts(Account accounts[]);
void save_accounts(Account accounts[]);

int load_messages(Message messages[], char mode[]);
void save_messages(Message messages[], char mode[]);

void login();
void logout();

void admin_page();
void user_page(Account* acc);

#endif // !GUMIPHONE_H
