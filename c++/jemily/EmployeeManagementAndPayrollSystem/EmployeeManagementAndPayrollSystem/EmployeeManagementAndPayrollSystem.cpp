#include <iostream>
#include <string>
#include <Windows.h>
#include <conio.h>

using namespace std;

int main()
{
	int daysOfWork = 5;
	string adminPass = "1234", adminInp;
	float salary = 70, tax = 0.1;
	int option, choice, counter = 0;
	string name;
	int workHours;
	string empName[10];
	int empWorkHours[10];
	int iniminute = 0, inihour = 0, inHour, inMinute, outHour, outMin, outhour, outmin, total, empIndex, tries;
	bool found = false;

	do
	{
		system("cls");
		cout << "Empolyee Management and Payroll System" << endl;
		cout << "--------------------------------------" << endl;
		cout << "1. Admin" << endl;
		cout << "2. Employee" << endl;
		cout << "3. Exit" << endl;

		cout << "Enter your option: ";
		cin >> option;
		getline(cin, adminInp);
		switch (option)
		{
		case 1:
			system("cls");
			cout << "Admin" << endl;
			tries = 0;

			do {
				cout << "Enter your pin: ";
				getline(cin, adminInp);
				if (adminInp != adminPass) 
				{
					cout << "Incorrect pin. Please try again." << endl;
					cout << "\nPress any key to continue...";
					_getch();
				} 
				system("cls");
				tries++;
			} while (adminInp != adminPass && tries < 3);

			if (tries == 3)
			{
				cout << "Too many tries!";
				break;
			}

			do
			{
				system("cls");
				cout << "Admin" << endl;
				cout << "--------------------------------------" << endl;
				cout << "1. Add Employee" << endl;
				cout << "2. Remove Employee" << endl;
				cout << "3. Check Attendance" << endl;
				cout << "4. Check Payroll" << endl;
				cout << "5. Exit" << endl;

				cout << "Enter your option: ";
				cin >> choice;
				getline(cin, name);

				switch (choice)
				{
				case 1:
					system("cls");
					cout << "Add Employee" << endl;
					cout << "Enter employee name: ";
					getline(cin, name);

					for (int i = 0; i < 10; i++)
					{
						if (empName[i] == "")
						{
							empName[i] = name;
							empWorkHours[i] = 0;
							break;
						}
					}
					break;
				case 2:
					system("cls");
					cout << "Remove Employee" << endl;
					cout << "Enter employee name: ";
					getline(cin, name);

					for (int i = 0; i < 10; i++)
					{
						if (empName[i] == name)
						{
							empName[i] = "";
							empWorkHours[i] = 0;
							break;
						}
					}
					break;
				case 3:
					system("cls");
					cout << "Current records" << endl;
					cout << "No. | Name | Work Hours" << endl;
					for (int i = 0; i < 10; i++)
					{
						if (empName[i] != "")
						{
							cout << i + 1 << ". " << empName[i] << " - " << empWorkHours[i] << endl;
							counter++;
						}
					}
					if (counter == 0) cout << "No records found!" << endl;
					cout << "\nPress any key to continue...";
					_getch();
					break;
				case 4:
					system("cls");
					counter = 0;
					cout << "Current records" << endl;
					cout << "No. | Name | Salary" << endl;
					for (int i = 0; i < 10; i++)
					{
						if (empName[i] != "")
						{
							cout << i + 1 << ". " << empName[i] << " - " << (empWorkHours[i] * salary) - ((empWorkHours[i] * salary) * tax) << endl;
							counter++;
						}
					}
					if (counter == 0) cout << "No records found!" << endl;
					cout << "\nPress any key to continue...";
					_getch();
					break;
				case 5:
					system("cls");
					cout << "Exiting..." << endl;
					break;
				default:
					system("cls");
					cout << "Invalid option!";
				}
			} while (choice != 5);
			break;
		case 2:
			system("cls");
			cout << "Employee" << endl;
			cout << "Enter your name: ";
			getline(cin, name);

			for (int i = 0; i < 10; i++)
			{
				if (empName[i] == name)
				{
					found = true;
					empIndex = i;
					break;
				}
			}

			if (found == false)
			{
				cout << "Employee not found!" << endl;
				break;
			}

			do
			{
				system("cls");
				cout << "Employee" << endl;
				cout << "--------------------------------------" << endl;
				cout << "1. Check in" << endl;
				cout << "2. Check out" << endl;
				cout << "3. Check pay" << endl;
				cout << "4. Exit" << endl;
				cout << "Enter your choice: ";
				cin >> choice;

				switch (choice)
				{
				case 1:
					system("cls");
					cout << "Enter time in (hours minute): ";
					cin >> inHour;
					cin >> inMinute;

					inihour = ((inHour >= 0 && inHour <= 24) ? inHour : 0);
					iniminute = ((inMinute >= 0 && inMinute <= 60) ? inMinute : 0);

					cout << "Time In" << endl;
					cout << inihour << ":" << iniminute;
					cout << "\nPress any key to continue...";
					_getch();
					break;
				case 2:
					system("cls");
					if (inihour == 0)
					{
						cout << "Check in first!" << endl;
						cout << "\nPress any key to continue...";
						_getch();
						break;
					}

					cout << "Enter time out (hours minute): ";
					cin >> outHour;
					cin >> outMin;

					outhour = ((outHour >= 0 && outHour <= 24) ? outHour : 0);
					outmin = ((outMin >= 0 && outMin <= 60) ? outMin : 0);

					cout << "Time In" << endl;
					cout << outhour << ":" << outmin;
					outhour -= inihour;
					outmin -= iniminute;
					cout << "Hours Worked: " << outhour;
					empWorkHours[empIndex] += outhour;
					cout << "\nPress any key to continue...";
					_getch();
					break;
				case 3:
					system("cls");
					cout << "Check pay" << endl;
					cout << "Your pay: " << (empWorkHours[empIndex] * salary) - ((empWorkHours[empIndex] * salary) * tax) << endl;
					cout << "\nPress any key to continue...";
					_getch();
					break;
				case 4:
					system("cls");
					cout << "Exiting..." << endl;
					break;
				default:
					system("cls");
					cout << "Invalid option!";
				}
			} while (choice != 4);
			break;
		case 3:
			system("cls");
			cout << "Exiting..." << endl;
			break;
		default:
			system("cls");
			cout << "Invalid option!";
		}
	} while (option != 3);
}

