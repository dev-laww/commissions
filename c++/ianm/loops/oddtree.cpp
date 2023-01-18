#include <iostream>

using namespace std;

int main()
{
    // Odd tree
    int n;
    cout << "Odd tree: ";
    cin >> n;

    if (n % 2 == 0)
    {
        cout << "Invalid input" << endl;
        return 0;
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n - i; j++)
        {
            cout << " ";
        }
        for (int j = 1; j <= 2 * i - 1; j++)
        {
            if (i == n)
            {
                cout << i;
                continue;
            }
            if (j == 1 || j == 2 * i - 1)
            {
                cout << i;
            }
            else
            {
                cout << "*";
            }
        }
        cout << endl;
    }
    for (int i = 0; i < 3; i++)
    {
        if (i == 0 || i == 1)
        {
            for (int j = 1; j <= n - 1; j++)
            {
                cout << " ";
            }
            cout << 1 << endl;
            continue;
        }

        for (int j = 1; j <= (n % 2 == 1 ? n / 2 : n); j++)
        {
            cout << " ";
        }
        for (int j = 1; j <= (n % 2 == 1 ? n : n + 1); j++)
        {
            cout << 1;
        }
        cout << endl;
    }
    
    return 0;
}