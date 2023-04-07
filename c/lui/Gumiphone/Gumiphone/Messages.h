#pragma once
#ifndef MESSAGES_H
#define MESSAGES_H

#define MAX_STRING 1001

typedef struct Message {
	char sender[41];
	char receiver[41];
	char text[MAX_STRING];
} Message;

Message compose();

#endif // !MESSAGES_H
