#include <iostream>
#include <stdlib.h>

using namespace std;

int main()
{
    int elements, operation, smallest, largest, sum, num, digit, factorial;
    char answer;
    do
    {   
        system("cls");
        cout << "Input number of numbers: ";
        cin >> elements;
        int *numbers = new int[elements];
        for (int i = 0; i < elements; i++)
        {
            cout << "Input number " << i + 1 << ": ";
            cin >> numbers[i];
        }

        cout << "\nSelect operation:\n"
             << "[1] Largest number\n"
             << "[2] Reverse number\n"
             << "[3] Strong number\n"
             << "[4] Cube number\n";

        cin >> operation;

        cout << "\nSummary:" << endl;
        cout << "The numbers are: ";
        for (int i = 0; i < elements; i++)
        {
            cout << numbers[i] << " ";
        }

        switch (operation)
        {
        case 1:
            cout << "\nThe operation is: Largest number" << endl;
            cout << "The answer is/are: ";
            largest = numbers[0];
            for (int i = 0; i < elements; i++)
            {
                if (numbers[i] > largest)
                {
                    largest = numbers[i];
                }
            }
            cout << largest << endl;
            break;
        case 2:
            cout << "\nThe operation is: Reverse number" << endl;
            cout << "The answer is/are: ";
            for (int i = elements - 1; i >= 0; i--)
            {   
                cout << numbers[i] << " ";

            }
            cout << endl;
            break;
        case 3:
            cout << "\nThe operation is: Strong number" << endl;
            cout << "The answer is/are: ";
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
                    cout << numbers[i] << " ";
                }
            }
            cout << endl;
            break;
        case 4:
            cout << "\nThe operation is: Cube number" << endl;
            cout << "The answer is/are: ";
            for (int i = 0; i < elements; i++)
            {
                // add commas if not last element
                cout << numbers[i] * numbers[i] * numbers[i] << " ";
            }
            cout << endl;
            break;
        default:
            cout << "Invalid operation" << endl;
            break;
        }

        cout << "\nDou you want to continue? (y/n): ";
        cin >> answer;
    } while (answer == 'y' || answer == 'Y');

    return 0;
}