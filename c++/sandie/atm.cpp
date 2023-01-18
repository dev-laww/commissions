#include <iostream>
#include <string>
#include <windows.h>

using namespace std;

int main()
{
    string pin = "0123", userIn, number;
    int tries = 0, choice;
    float bal = 0, amount;
    cout << "Enter your pin: ";
    getline(cin, userIn);
    while (userIn != pin && tries < 2)
    {
        cout << "Incorrect pin. Try again: ";
        getline(cin, userIn);
        tries++;
    }

    if (tries >= 2)
    {
        cout << "Too many tries. Exiting..." << endl;
        return 0;
    }

    while (1)
    {
        system("cls");
        cout << "Welcome to the ATM" << endl;
        cout << "1. Check balance" << endl;
        cout << "2. Deposit" << endl;
        cout << "3. Withdraw" << endl;
        cout << "4. Payments" << endl;
        cout << "5. Exit" << endl;
        cout << "Enter your choice: ";

        cin >> choice;
        switch (choice)
        {
        case 1:
            cout << "\nBalance" << endl;
            cout << "Your balance is: " << bal << endl;
            Sleep(2000);
            break;
        case 2:
            cout << "\nDeposit" << endl;
            cout << "Enter amount to deposit: ";
            cin >> amount;
            bal += amount;
            cout << "Your new balance is: " << bal << endl;
            Sleep(2000);
            break;
        case 3:
            cout << "\nWithdraw" << endl;
            cout << "Enter amount to withdraw: ";
            cin >> amount;
            if (amount > 25000)
            {
                cout << "Maximum withdrawal is 25000" << endl;
                Sleep(2000);
                break;
            }

            if (amount > bal)
            {
                cout << "Insufficient funds" << endl;
            }
            else
            {
                bal -= amount;
                cout << "Success!" << endl;
                cout << "Your new balance is: " << bal << endl;
            }
            Sleep(2000);
            break;
        case 4:
            cout << "\n Sub Menu" << endl;
            cout << "1. VECO" << endl;
            cout << "2. MWCD" << endl;
            cout << "3. Globe" << endl;
            cout << "4. PLDT" << endl;
            cout << "Enter your choice: ";
            cin >> choice;
            switch (choice)
            {
            case 1:
                cout << "\nVECO" << endl;
                cout << "Enter account #: ";
                cin >> number;
                cout << "Enter amount to pay: ";
                cin >> amount;
                if (amount > bal)
                {
                    cout << "Insufficient funds" << endl;
                }
                else
                {
                    bal -= amount;
                    cout << "Success!" << endl;
                    cout << "Your new balance is: " << bal << endl;
                }
                Sleep(2000);
                break;
            case 2:
                cout << "\nMWCD" << endl;
                cout << "Enter account #: ";
                cin >> number;
                cout << "Enter amount to pay: ";
                cin >> amount;
                if (amount > bal)
                {
                    cout << "Insufficient funds" << endl;
                }
                else
                {
                    bal -= amount;
                    cout << "Success!" << endl;
                    cout << "Your new balance is: " << bal << endl;
                }
                Sleep(2000);
                break;
            case 3:
                cout << "\nGlobe" << endl;
                cout << "Enter account number: ";
                cin >> number;
                cout << "Enter amount to pay: ";
                cin >> amount;
                if (amount > bal)
                {
                    cout << "Insufficient funds" << endl;
                }
                else
                {
                    bal -= amount;
                    cout << "Success!" << endl;
                    cout << "Your new balance is: " << bal << endl;
                }
                Sleep(2000);
                break;
            case 4:
                cout << "\nPLDT" << endl;
                cout << "Enter account number: ";
                cin >> number;
                cout << "Enter amount to pay: ";
                cin >> amount;
                if (amount > bal)
                {
                    cout << "Insufficient funds" << endl;
                }
                else
                {
                    bal -= amount;
                    cout << "Success!" << endl;
                    cout << "Your new balance is: " << bal << endl;
                }
                Sleep(2000);
                break;
            default:
                cout << "Invalid choice" << endl;
                Sleep(2000);
            }
            Sleep(2000);
            break;
        case 5:
            cout << "\nExiting..." << endl;
            return 0;
        }
    }
}