#include "Account.h"

#ifndef PERSONAL_CONN_H
#define PERSONAL_CONN_H

typedef struct PersonalConn {
	Account* acc;
	char relationship[MAX_STRING];
} PersonalConn;


void add_conn(Account* acc);
void remove_conn(Account* acc);
void view_conn(Account* acc);

#endif // !PERSONAL_CONN_H
