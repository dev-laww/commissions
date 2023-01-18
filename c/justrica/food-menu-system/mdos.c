#include <stdio.h>
#include <stdlib.h>

typedef struct Item
{
    float price;
    char* name;
} Item;

// array of food items
Item food[3];
// array of drink items
Item drink[3];
// array of dessert items
Item dessert[3];

// function to print the menu
void printMenu(Item *item, int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("%d. %s - $%.2f", i + 1, item[i].name, item[i].price);
    }
{
    

