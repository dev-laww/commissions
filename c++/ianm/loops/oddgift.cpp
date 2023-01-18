#include <iostream>

using namespace std;

int main()
{
    // Odd gift
    int n;
    cout << "Odd gift: ";
    cin >> n;
    if (n % 2 == 0)
    {
        cout << "Invalid input" << endl;
        return 0;
    }
    for (int i = n; i >= 1; i--)
    {
        for (int j = 1; j <= 2 * n - 1; j++)
        {
            if (i == 1 || i == n)
            {
                if (j == n && i != n)
                    cout << "*";
                else
                    cout << i;
                continue;
            }
            if (j == 1 || j == 2 * n - 1 || j == n)
            {
                cout << i;
            }
            else
            {
                cout << " ";
            }
        }
        cout << endl;
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= 2 * n - 1; j++)
        {
            if (i == n)
            {
                cout << i;
                continue;
            }
            if (j == 1 || j == 2 * n - 1 || j == n)
            {
                cout << i;
            }
            else
            {
                cout << " ";
            }
        }
        cout << endl;
    }
}