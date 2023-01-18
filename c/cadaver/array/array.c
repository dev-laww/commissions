#include <stdio.h>
#include <stdlib.h>

int main()

{
    char op, again;
    int num, array[100], i;
    float sum = 0, diff = 0, product = 1, quotient = 1;

    do
    {
        system("cls");
        
        // HOW MANY NUMBERS ARE YOU GOING TO INPUT
        printf("Input number of numbers: ");
        scanf("%d", &num);

        for (i = 0; i < num; i++)
        {
            printf("\tInput number %d: ", i + 1);
            scanf("%d", &array[i]);
        }

        // SELECTION OF OPERATORS
        printf("\n[+] - Addition");
        printf("\n[-] - Subtraction");
        printf("\n[*] - Multiplication");
        printf("\n[/] - Division");
        printf("\n[%%] - Modulo Division");
        printf("\n[>] - Increment");
        printf("\n[<] - Decrement");
        printf("\nSelect Operator: ");
        scanf("%s", &op);

        // SUMMARY
        printf("\nSummary:");
        printf("\nThe numbers are: ");
        for (i = 0; i < num; i++)
        {
            printf("%d ", array[i]);
            sum += array[i];
            diff -= array[i];
            product *= array[i];
            quotient /= array[i];
        }

        // OPERATOR AND ANSWER
        switch (op)
        {
        case '+':
            printf("\nThe operator is: Addition");
            printf("\nThe answer is: %.2f", sum);
            break;
        case '-':
            printf("\nThe operator is: Subtraction");
            printf("\nThe answer is: %.2f", diff);
            break;
        case '*':
            printf("\nThe operator is: Multiplication");
            printf("\nThe answer is: %.2f", product);
            break;
        case '/':
            printf("\nThe operator is: Division");
            printf("\nThe answer is: %.2f", quotient);
            break;
        case '%':
            printf("\nThe operator is: Modulo Division");
            printf("\nThe answer is: ");
            for (i = 0; i < num; i++)
            {
                int modulo = (int) sum % array[i];
                printf("%d ", modulo);
            }
            break;
        case '>':
            printf("\nThe operator is: Increment");
            for (i = 0; i < num; i++)
            {
                printf("%d ", array[i] + 1);
            }
            break;
        case '<':
            printf("\nThe operator is: Decrement");
            for (i = 0; i < num; i++)
            {
                printf("%d ", array[i] - 1);
            }
            break;
        default:
            printf("Invalid operator!");
        }

        // TRY AGAIN
        printf("\n\nDo you want to try again?[Y/N]: ");
        scanf("%s", &again);
    } while (again == 'Y' || again == 'y');

    return 0;
}