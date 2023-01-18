

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int generateRandomNumber(int min, int max)
{
	int randomNumber = (rand() % (max - min + 1)) + min;
	return randomNumber;
}

struct node
{
	int data;
	int destination;
	int floor;
	time_t time_of_request;
	time_t time_of_arrival;
	time_t time_of_departure;
	int direction;
	struct node *prev, *next;
};

struct node *front = NULL, *rear = NULL;


// enqueue function
void enqueue(int data, int floor)
{
	struct node *newnode = (struct node *)malloc(sizeof(struct node));
	newnode->data = data;
	newnode->floor = floor;
	newnode->destination = generateRandomNumber(1, 10);
	newnode->time_of_request = time(NULL);
	newnode->time_of_arrival = time(NULL);
	newnode->time_of_departure = time(NULL);
	newnode->direction = (floor < newnode->destination) ? 1 : -1;
	newnode->prev = NULL;
	newnode->next = NULL;
	if (front == NULL)
	{
		front = rear = newnode;
	}
	else
	{
		rear->next = newnode;
		newnode->prev = rear;
		rear = newnode;
	}
}

// dequeue function
void dequeue()
{
	struct node *temp = front;
	if (front == NULL)
	{
		printf("Queue is empty");
	}
	else
	{
		front = front->next;
		front->prev = NULL;
		free(temp);
	}
}

// is_empty function
int is_empty()
{
	if (front == NULL)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

// peek function
struct node *peek()
{
	if (!is_empty())
	{
		return front;
	}
	return NULL;
}

// print queue function
void print_queue()
{
	struct node *temp = front;
	if (front == NULL)
	{
		printf("Queue is empty");
	}
	else
	{
		while (temp != NULL)
		{
			printf("User: %d, Destination: %d, Direction: %s, Floor: %d\n", temp->data, temp->destination, (temp->direction == 1) ? "Up" : "Down", temp->floor);
			temp = temp->next;
		}
	}
}

void printList()
{
	struct node *temp = front;

	while (temp)
	{
		printf("%d->", temp->data);
    temp = temp->next;
  }
	printf("NULL\n");
}



int main()
{
	enqueue(1, 1);
	enqueue(2, 3);
	enqueue(3, 5);
	printf("Queue :");
	printList();
	print_queue();
	dequeue();
	printf("After dequeue the new Queue :");
	printList();
	print_queue();
	dequeue();
	printf("After dequeue the new Queue :");
	printList();
	print_queue();

	return 0;
}