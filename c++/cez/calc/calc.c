#include <stdio.h>
#include <stdlib.h>

int main()
{
    int elements, operation, smallest, largest, sum, num , digit, factorial, numbers[100];
    char answer;

    do
    {
        system("cls");
        printf("Input number of numbers: ");
        scanf("%d", &elements);

        for (int i = 0; i < elements; i++)
        {
            printf("Input number %d: ", i + 1);
            scanf("%d", &numbers[i]);
        }

        printf("\nSelect operation:\n");
        printf("[1] Largest number\n");
        printf("[2] Reverse number\n");
        printf("[3] Strong number\n");
        printf("[4] Cube number\n");

        scanf("%d", &operation);

        printf("\nSummary:\n");
        printf("The numbers are: ");
        for (int i = 0; i < elements; i++)
        {
            printf("%d ", numbers[i]);
        }

        switch (operation)
        {
        case 1:
            printf("\nThe operation is: Largest number\n");
            printf("The answer is/are: ");
            largest = numbers[0];
            for (int i = 0; i < elements; i++)
            {
                if (numbers[i] > largest)
                {
                    largest = numbers[i];
                }
            }
            printf("%d\n", largest);
            break;
        case 2:
            printf("\nThe operation is: Reverse number\n");
            printf("The answer is/are: ");
            for (int i = elements - 1; i >= 0; i--)
            {
                printf("%d ", numbers[i]);
            }
            printf("\n");
            break;
        case 3:
            printf("\nThe operation is: Strong number\n");
            printf("The answer is/are: ");
            for (int i = 0; i < elements; i++)
            {
                sum = 0;
                num = numbers[i];
                while (num > 0)
                {
                    digit = num % 10;
                    factorial = 1;
                    for (int j = 1; j <= digit; j++)
                    {
                        factorial *= j;
                    }
                    sum += factorial;
                    num /= 10;
                }
                if (sum == numbers[i])
                {
                    printf("%d ", numbers[i]);
                }
            }
            printf("\n");
            break;
        case 4:
            printf("\nThe operation is: Cube number");
            printf("\nThe answer is/are: ");
            for (int i = 0; i < elements; i++)
            {
                // add commas if not last element
                printf("%d ", numbers[i] * numbers[i] * numbers[i]);
            }
            printf("\n");
            break;
        default:
            printf("Invalid operation\n");
            break;
        }

        printf("\nDou you want to continue? (y/n): ");
        scanf("%s", &answer);
    } while (answer == 'y' || answer == 'Y');

    return 0;
}