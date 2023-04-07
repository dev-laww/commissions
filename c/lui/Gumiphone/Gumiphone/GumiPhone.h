#include "Admin.h"
#include "User.h"
#include "Account.h"
#include "Messages.h"

#pragma once
#ifndef GUMIPHONE_H
#define GUMIPHONE_H

void run();
void load_accounts();
void save_accounts();
void load_messages();
void save_messages();
void logout();

#endif // !GUMIPHONE_H
