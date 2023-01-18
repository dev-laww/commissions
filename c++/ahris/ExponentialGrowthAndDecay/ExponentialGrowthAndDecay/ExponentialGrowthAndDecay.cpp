#include <iostream>
#include <math.h>
#include <conio.h>
#include <stdio.h>
#include <windows.h>
#include<cstring>
#include<iomanip>

using namespace std;

int progress = 0;

double amountI;
double amountF;
double rate;
double time1;
double gRate;

char next, mar, start, calc1, calc2, calc3, calc4, choice;

const int BLUE = 1;
const int GREEN = 2;
const int RED = 4;
const int WHITE = 15;
const int total = 10;
const char a = 177, b = 219;


void p(const char* str) {
	cout << str;
	Sleep(2000);
}


void finalGrowth()
{
	double finalGrowth;
	double exponent = (rate / 100) * time1;
	double multiplier = exp(exponent);

	finalGrowth = amountI * pow(2.71828, exponent);
	cout << fixed;
	cout << setprecision(2);
	cout << finalGrowth;
}

void initialGrowth()
{
	double initialGrowth;
	double exponent = (rate / 100) * time1;
	double multiplier = exp(exponent);

	initialGrowth = amountF / pow(2.71828, exponent);
	cout << fixed;
	cout << setprecision(2);
	cout << initialGrowth;
}

void lengthTime()
{
	double lengthTime;

	double amount = amountF / amountI;



	lengthTime = log(amount) / rate;
	cout << fixed;
	cout << setprecision(2);
	cout << lengthTime;
}

double growthRate()
{
	double growthRate;

	double amount = amountF / amountI;
	gRate = (log(amount) / time1) * 100;

	cout << fixed;
	cout << setprecision(2);
	return gRate;
}

void printNextLine(int n)
{
	for (int i = 0; i < n; i++)
		cout << endl;
}


int main()
{
start:
	system("color 0A");
	while (progress <= total)
	{
		system("cls");
		printNextLine(12);
		int loadingBarWidth = 4 * total;
		cout << setw(80 - loadingBarWidth) << "";
		for (int i = 0; i < total; i++)
		{
			for (int j = 0; j < 4; j++)
				cout << (i > progress ? a : b);
		}
		cout << endl << endl << setw(80 - loadingBarWidth) << "";
		cout << "                Loading..." << endl;
		progress++;
		Sleep(200);
	}
	system("cls");
	system("color F0");
	cout << "\nProgram by: Chris Marwin C. Tuvilleja";
	cout << "\nStub code: 1886";
	cout << "\nFINAL PROJECT: Exponential Growth and Decay Calculator\n\n";
			cout << "This Program Calculates the Exponential Growth or Decay following the formula:\n";
	cout << "P(t) = P(0)e^(rt)\n";
	cout << "Where:\n";
	cout << "\t P(t)	= the value at time t.\n";
	cout << "\t P(0) 	= the initial value at time t = 0;\n";
	cout << "\t t	= the time in discreet intervals and selected time units (hours).\n";
	cout << "\t r	= growth rate when r > 0 or decay rate when r < 0 .";
	cout << "\n----------------------------------------------------------------------------";
	cout << "\n\tA = Final Amount\n" << "\tB = Initial Amount\n" << "\tC = growth or decay rate\n" << "\tD = time\n";
	cout << "\nWhat is the unknown variable?: ";
	cin >> choice;

	if (toupper(choice) == 'A')
	{
		cout << "\n\tEnter the initial amount: ";
		cin >> amountI;
		if (amountI <= 0)
		{
			system("color 0C");
			cout << "\n\tInvalid Input!";
			goto next;
		}
		cout << "\n\tEnter the growth/decay rate (% rate/hr): ";
		cin >> rate;
		cout << "\n\tEnter time in hours: ";
		cin >> time1;
		cout << "\n----------------------------------------------------------------------------";
		cout << "\n\n\tThe Final Value after " << time1 << " hours is ";
		finalGrowth();
		cout << "\n----------------------------------------------------------------------------";
		goto next;
	}
	else if (toupper(choice) == 'B')
	{
		cout << "\n\tEnter the Final amount: ";
		cin >> amountF;
		if (amountF <= 0)
		{
			system("color 0C");
			cout << "\n\tInvalid Input!";
			goto next;
		}
		cout << "\n\tEnter the growth/decay rate (% rate/hr): ";
		cin >> rate;
		cout << "\n\tEnter time in hours: ";
		cin >> time1;
		cout << "\n----------------------------------------------------------------------------";
		cout << "\n\n\tThe Initial Amount before " << time1 << " hours" << " is ";
		initialGrowth();
		cout << "\n----------------------------------------------------------------------------";
		goto next;
	}
	else if (toupper(choice) == 'C')
	{
		cout << "\n\tEnter the Final amount: ";
		cin >> amountF;
		if (amountF <= 0)
		{
			system("color 0C");
			cout << "\n\tInvalid Input!";
			goto next;
		}
		cout << "\n\tEnter the Initial amount: ";
		cin >> amountI;
		if (amountI <= 0)
		{
			system("color 0C");
			cout << "\n\tInvalid Input!";
			goto next;
		}
		cout << "\n\tEnter the time in hours: ";
		cin >> time1;
		cout << "\n----------------------------------------------------------------------------";
		double newGRate = growthRate();
		cout << "\n\n\tThe " << ((newGRate > 0) ? "growth" : "decay") << " rate is " << newGRate << "% per hour";
		cout << "\n----------------------------------------------------------------------------";
		goto next;
	}
	else if (toupper(choice) == 'D')
	{
		cout << "\n\tEnter the Final amount: ";
		cin >> amountF;
		if (amountF <= 0)
		{
			system("color 0C");
			cout << "\n\tInvalid Input!";
			goto next;
		}
		cout << "\n\tEnter the Initial amount: ";
		cin >> amountI;
		if (amountI <= 0)
		{
			system("color 0C");
			cout << "\n\tInvalid Input!";
			goto next;
		}
		cout << "\n\tEnter the growth/decay rate (% rate/hr): ";
		cin >> rate;
		cout << "\n----------------------------------------------------------------------------";
		cout << "\n\n\tThe time it takes to reach the value of " << amountF << " is ";
		cout << fixed;
		cout << setprecision(2);
		lengthTime();
		cout << " hours";
		cout << "\n----------------------------------------------------------------------------";
		goto next;
	}
	else
	{	
		system("color 0C");
		cout << "\n\tInvalid Input!";
		goto next;
	}

next:
	cout << "\n\n\n\t\t\tPress any key to Try again/x to EXIT: ";
	mar = _getch();
	if (toupper(mar) != 'X')
	{
		return main();
	}
	else
	{
		cout << "\n\n\n\t\t\tIt Was a Pleasure Helping You My Friend. Until Next Time.\n";
		system("pause");
	}
	return 0;
}