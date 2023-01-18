#include <conio.h>
#include <ctype.h>
#include "list.h"


int main(){
int firstElement, count, element1, element2, num, pos;
nodePtr L;
char c;

	L = createList();
	
do { 
printf("\nWhat do you want to do?\n");
    printf("\n A - Add at the Front\n");
    printf(" B - Add at the Last\n");
    printf(" C - Delete First\n");
    printf(" D - Delete Last\n");
    printf(" E - Insert number\n");
    printf(" F - Count elements\n");
    printf(" G - Return First element\n");
    printf(" H - Return Last element\n");
    printf(" I - Delete specific element\n");
    printf(" Q - Quit\n\n");
    printf("\n Choice: ");
    c = getche();
    printf("\n");
    switch (toupper(c)) 	{
    		case 'A':
    			system("cls");
    			printf("Enter number: ");
    			scanf("%d", &num);
                addFront(&L,num);
                display(L);
                break;
            case 'B':
            	system("cls");
            	printf("Enter number: ");
    			scanf("%d", &num);
              	append(&L, num);
              	display(L);
                break;
            case 'C':
            	system("cls");
                L = delFront(L);
                display(L);
                break;
            case 'D':
            	system("cls");
            	del_Last(L);
            	display(L);
            	break;
            case 'E':
            	system("cls");
            	printf("Enter data: ");
   			 	scanf("%d", &num);
				printf("Enter the position to add after: ");
				scanf("%d", &pos);
            	addAfter(L, num, pos);
            	display(L);
            	break;
			case 'F':
				system("cls");
				count = counter(L);
				printf("There are %d elements in this list", count);
            	break;	
            case 'G':
				system("cls");
				element1=first(L);
				printf("%d is the first element", element1);
            	break;	
            case 'H':
				system("cls");
				element2=last(L);
				printf("%d is the last element", element2);
            	break;	
            case 'I':
				system("cls");
				printf("Enter element to delete: ");
				scanf("%d", &num);
				L=del(L, num);
				display(L);
            	break;	
            case 'Q':
           		exit(0);
            	break;
            default:
            	{	
                printf("Invalid selection\n");
                break;
					}		
        	}
} while(c != 'Q'||c !='Q');  

getche();
return 0;
}