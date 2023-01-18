#include <iostream>
#include <string>
#include <vector>
#include <limits>
#include <cmath>
#include <cstring>

using namespace std;
//----------------------------------------------------

void hex2binary(string hex)
{
  int result = 0;
  for (int i = 0; i < hex.length(); i++)
  {
    if (hex[i] >= 48 && hex[i] <= 57)
    {
      result += (hex[i] - 48) * pow(16, hex.length() - i - 1);
    }
    else if (hex[i] >= 65 && hex[i] <= 70)
    {
      result += (hex[i] - 55) * pow(16, hex.length() - i - 1);
    }
  }
  int binary[32];
  int i = 0;
  while (result > 0)
  {
    binary[i] = result % 2;
    result /= 2;
    i++;
  }
  for (int j = i - 1; j >= 0; j--)
    cout << binary[j];
}

//----------------------------------------------------

void hex2octal(string hex)
{
  int result = 0;
  for (int i = 0; i < hex.length(); i++)
  {
    if (hex[i] >= 48 && hex[i] <= 57)
    {
      result += (hex[i] - 48) * pow(16, hex.length() - i - 1);
    }
    else if (hex[i] >= 65 && hex[i] <= 70)
    {
      result += (hex[i] - 55) * pow(16, hex.length() - i - 1);
    }
  }
  int octal[32];
  int i = 0;
  while (result > 0)
  {
    octal[i] = result % 8;
    result /= 8;
    i++;
  }
  for (int j = i - 1; j >= 0; j--)
    cout << octal[j];
}

//----------------------------------------------------

void hex2decimal(string hexString)
{
  int ans = 0;
  int x = 1;
  int s = hexString.size();

  for (int i = s - 1; i >= 0; i--)
  {
    if (hexString[i] >= '0' && hexString[i] <= '9')
    {
      ans += x * (hexString[i] - '0');
    }
    else if (hexString[i] >= 'A' && hexString[i] <= 'F')
    {
      ans += x * (hexString[i] - 'A' + 10);
    }
    x *= 16;
  }
  cout << ans;
}


//----------------------------------------------------

int main()
{
  string hex;
  cout << "Enter Hexadecimal Number: ";
  cin >> hex;

  cout << "Decimal Equivalent is: ";
  hex2decimal(hex);
  cout << endl;

  cout << "Binary Equivalent is: ";
  hex2binary(hex);
  cout << endl;

  cout << "Octal Equivalent is: ";
  hex2octal(hex);
  cout << endl;
  return 0;
}