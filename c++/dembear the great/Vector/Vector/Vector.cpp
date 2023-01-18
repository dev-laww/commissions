#include <iostream>
#include <iomanip>
#include <sstream>
#include <cmath>
#include <windows.h>

using namespace std;

int count_char(string str, char ch);
string indent(int length);
void loading_bar(int loading_bar_length, int loading_time, string before_string = "");
void unroll_print(int loading_time, string str, string before_line = "");

int main()
{
    // Settings
    static CONSOLE_FONT_INFO font;
    const HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    const int loading_bar_length = 31;
    const int loading_time = 1;
    const string title = "Vcalc";
    const string title_art = "         _____  (o>o)          _____ \n"
        " _   __|       |__/___ ___\\\\  |       |\n"
        "|  | |  |       |       |   |o`)|       |\n"
        "|  |_|  |      _|   _   |   |/  |      _|\n"
        "|       |     | |  |_|  |   8   |     |  \n"
        "|       |     |_|       |   |___|     |_ \n"
        " |     ||_______|   _   |       |_______|\n"
        "  |___|   \\(oo)/|__| |__|_______| \\(oo)/ \n";
    const int title_art_row_length = (title_art.length() - count_char(title_art, '\n')) / count_char(title_art, '\n');

    // Constant center indent
    CONSOLE_SCREEN_BUFFER_INFO csbi;
    GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
    int cols = csbi.srWindow.Right - csbi.srWindow.Left + 1;
    string constant_center_indent = indent((cols - title_art_row_length) / 2);

    // Helper function
    auto center_indent = [&](int length)
    {
        return indent((title_art_row_length - length) / 2 + constant_center_indent.length());
    };

    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
    // Title
    unroll_print(loading_time / 2 * 1000, title_art, constant_center_indent);
    Sleep(loading_time / 2 * 1000);

    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
    // Loading bar
    cout << endl;
    loading_bar(loading_bar_length, loading_time, center_indent(loading_bar_length + 2));

    while (true)
    {
        // Clear console
        system("cls");

        // Reset constant_indent
        GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
        cols = csbi.srWindow.Right - csbi.srWindow.Left + 1;
        constant_center_indent = indent((cols - title_art_row_length) / 2);

        // Title
        unroll_print(loading_time / 2 * 1000, title_art, constant_center_indent);
        cout << endl;

        // Calculate
        float x, y;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
        cout << center_indent(15) << "Inputing vector" << endl;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
        cout << center_indent(25) << "Enter the value of X: ";
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
        cin >> x;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
        cout << center_indent(25) << "Enter the value of Y: ";
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
        cin >> y;
        float raw_angle = atan(y / x) * 180 / 3.14;
        float angle = signbit(x) ? (signbit(y) ? raw_angle - 180 : raw_angle + 180) : raw_angle;
        stringstream s;
        s << angle;
        string angle_stirng = s.str();
        s.str("");
        s.clear();
        s << sqrt(x * x + y * y);
        string resultant_string = s.str();
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 13);
        cout << center_indent(19 + angle_stirng.length()) << "Angle(in degrees): ";
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
        cout << angle_stirng << endl;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 13);
        cout << center_indent(11 + resultant_string.length()) << "Resultant: ";
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
        cout << resultant_string << endl;

        // Arrow
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
        cout << center_indent(14) << "Arrow of Angle" << endl;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
        string arrow_indent = center_indent(3);
        if (angle <= 45 && angle > -45)
        {
            cout << '\n'
                << arrow_indent << "-->\n\n";
        }
        else if (angle <= 135 && angle > 45)
        {
            cout << '\n'
                << arrow_indent << "^\n"
                << arrow_indent << "|\n"
                << arrow_indent << "|\n\n";
        }
        else if (angle >= 135 && angle > -135)
        {
            cout << '\n';
            for (int i = 0; i < 4; i++)
            {
                cout << '\r' << arrow_indent << "<--";
                Sleep(0.5 * 1000);
            }
            cout << "\n\n";
        }
        else if (angle <= -45 && angle > -135)
        {
            cout << '\n'
                << arrow_indent << "|\n"
                << arrow_indent << "|\n"
                << arrow_indent << "v\n\n";
        }

        // Try again
        string input;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
        cout << center_indent(25) << "Enter 'y' to try again: ";
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
        cin >> input;
        cout << endl;
        if (input != "y" && input != "Y")
            break;
    }
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
    cout << center_indent(20) << "Thank you for using!" << endl;
}

int count_char(string str, char ch)
{
    int count = 0;

    for (int i = 0; i < str.size(); i++)
        if (str[i] == ch)
            count++;

    return count;
}
string indent(int length)
{
    string result;
    for (int i = 0; i < length; i++)
        result += ' ';
    return result;
}
void loading_bar(int bar_length, int loading_time, string before_string)
{
    for (int i = 0; i < bar_length; i++)
    {
        cout << '\r' << before_string << '[';
        for (int j = 0; j < bar_length; j++)
            cout << (i < j ? ' ' : '=');
        cout << ']';
        Sleep(loading_time * 1000 / bar_length);
    }
}
void unroll_print(int loading_time, string str, string before_line)
{
    for (int i = 0, n = count_char(str, '\n'); i < n; i++)
    {
        system("cls");
        int k = 0;
        for (int j = 0; j < i + 1; j++)
        {
            cout << before_line;
            for (; str[k] != '\n'; k++)
            {
                cout << str[k];
            }
            k++;
            cout << '\n';
        }
        Sleep(loading_time / 2 * 1000 / n);
    }
}