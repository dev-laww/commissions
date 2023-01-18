#include <stdlib.h>
#include <stdio.h>
#ifndef list_h
#define list_h


typedef int itemType;
typedef struct node *nodePtr;

struct node{
	itemType item;
	nodePtr next;
};

nodePtr createList(); 									//init list
nodePtr makeNode(itemType x);							//allocate node 	
void display(nodePtr L);								//print output										//displ
void addFront(nodePtr L, itemType x);					//add as first element								//push
void append(nodePtr L, itemType x);						//add after the last element						//enqueue
void addAfter(nodePtr L, itemType x, itemType pos);		//add after specific position						//pop 	//dequeue
struct node *delFront(nodePtr L);						//delete first element
struct node *del_Last(nodePtr L);						//delete last element
int counter(nodePtr L);									//count elements
itemType first(nodePtr L);								//return first element								//stacktop  //queue front
itemType last(nodePtr L);								//return last element								//queue rear
struct node* del(nodePtr, itemType x);					//remove element x from list if it is there
int isEmpty(node *head);								//verify if list is empty
int search(itemType x);									//return location of x element


nodePtr	createList() {		
	nodePtr L;
	itemType firstElement;
	
	printf("Add an element: "); 
	scanf("%d", &firstElement);
	L= makeNode(firstElement);
	return L;
}

nodePtr makeNode(itemType x) {
	nodePtr temp = (nodePtr) malloc(sizeof(struct node));
	temp->item=x;
	temp->next=NULL;
	return temp;
}

void display(nodePtr L) {
	nodePtr temp;
	temp = L;
	if(L==NULL) {
        printf("\nList is empty\n");
        return;
        }
	while (temp!=NULL) {
		printf("%d ->", temp->item);
		temp = temp->next;
	}
	printf("\n");
}

void addFront(nodePtr *L, itemType x) {
    nodePtr newNode = (nodePtr) malloc(sizeof(struct node));
    if(newNode == NULL) {
        printf("Unable to allocate memory.");
    }
    else {
    newNode->item = x;
    newNode->next = *L;
    *L=newNode;
    printf("NODE INSERTED\n");
    }   
}

void append(nodePtr *L, itemType x) {
	nodePtr newNode, temp;
	newNode = (nodePtr) malloc(sizeof(struct node));
	if(newNode == NULL) {
        printf("Unable to allocate memory.");
    }
    else{
    	newNode->item = x; 
        newNode->next = NULL; 
        temp = *L;
        while(temp != NULL && temp->next != NULL)
            temp = temp->next;
        temp->next = newNode; 
	}
}

void addAfter(nodePtr L, itemType x, itemType pos) {
	int i=0;
	nodePtr temp= L;
	nodePtr temp2 = (nodePtr) malloc(sizeof(struct node));
	temp2->item = x;
	temp2->next = NULL;
	
	while(temp!=NULL) {
		i++;
		if (i==pos) {
			break;
		}
		temp = temp->next;
	}
	temp2->next=temp->next;
	temp->next=temp2;
}		
struct node* delFront(nodePtr L) {
	if (L == NULL) {
		printf("\n List is Empty");
	}
	else {
		struct node *toDelete = L;
		L = L->next;
		free(toDelete);
		toDelete=NULL;
	}
	return L;
}

struct node* del_Last(nodePtr L) {
	if (L == NULL) {
		printf("\n List is Empty");
	}
	else {
		struct node *toDelete = L;
		struct node *secondLastNode = L;
		 while(toDelete->next != NULL) {
            secondLastNode = toDelete;
            toDelete = toDelete->next;
        }
        if(toDelete == L) {
            L = NULL;
        }
        else {
            /* Disconnect link of second last node with last node */
            secondLastNode->next = NULL;
        }
        /* Delete the last node */
        free(toDelete);
	}
	return L;
}
							
int counter(nodePtr L) {
	int count = 0;
    nodePtr temp;
    temp = L;

    while(temp != NULL)
    {
        count++;
        temp = temp->next;
    }

    return count;
}

itemType first(nodePtr L) {
	return L->item;
}
itemType last(nodePtr L) {
	nodePtr lastNode = L;
	while(lastNode->next != NULL)
        {
            lastNode = lastNode->next;
        }
    int lastN;
	lastN=lastNode->item;
    return lastN;
}
struct node* del(nodePtr L, itemType x) {
	nodePtr temp, prev;
	temp = L;
	if (temp != NULL && temp->item == x) {
        L = temp->next; // Changed head
        free(temp); // free old head
        return L;
    }
    	// Search for the key to be deleted, keep track of the
    	// previous node as we need to change 'prev->next'
    while (temp != NULL && temp->item != x) {
        prev = temp;
        temp = temp->next;
    }
    // If key was not present in linked list
    if (temp == NULL)
        return L;
    // Unlink the node from linked list
    prev->next = temp->next;
    free(temp);
    return L;
}
int isEmpty(node *head) {
	if (head->next == NULL)
	return 1;
	else 
	return 0;
}
//int search(itemType x);									//return location of x element

#endif