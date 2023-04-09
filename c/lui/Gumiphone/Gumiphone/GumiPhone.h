#include "Admin.h"
#include "User.h"
#include "Account.h"
#include "Messages.h"

#pragma once
#ifndef GUMIPHONE_H
#define GUMIPHONE_H

int load_accounts(Account accounts[]);
void save_accounts(Account accounts[]);

int load_messages(Message messages[]);
void save_messages(Message messages[]);

int load_announcements(Message announcements[]);
void save_announcements(Message announcements[]);

void login();
void logout();

#endif // !GUMIPHONE_H
