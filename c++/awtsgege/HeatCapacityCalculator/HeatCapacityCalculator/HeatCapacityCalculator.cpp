#include <iostream>
#include <conio.h>
#include <Windows.h>

using namespace std;

int main() {
	char choice;
	double heatCapacity, mass, deltaT, specificHeatCapacity;
	
	do
	{	
		system("color DF");
		system("cls");
		cout << R"(
 _____         _      _____                 _ _          _____     _         _     _           
|  |  |___ ___| |_   |     |___ ___ ___ ___|_| |_ _ _   |     |___| |___ _ _| |___| |_ ___ ___ 
|     | -_| .'|  _|  |   --| .'| . | .'|  _| |  _| | |  |   --| .'| |  _| | | | .'|  _| . |  _|
|__|__|___|__,|_|    |_____|__,|  _|__,|___|_|_| |_  |  |_____|__,|_|___|___|_|__,|_| |___|_|  
                               |_|               |___|                                        
)" << endl << endl;

		cout << "				[1] Heat Capacity" << endl;
		cout << "				[2] Specific Heat Capacity" << endl;
		cout << "				[3] Mass" << endl;
		cout << "				[4] Change in Temperature" << endl;
		cout << "				[5] Exit" << endl;
		cout << "				Press the number of the variable you want to calculate...";
		choice = _getch();

		switch (choice)
		{
		case '1':
			system("color 0E");
			system("cls");
			cout << R"(                              
 _____         _      _____                 _ _       
|  |  |___ ___| |_   |     |___ ___ ___ ___|_| |_ _ _ 
|     | -_| .'|  _|  |   --| .'| . | .'|  _| |  _| | |
|__|__|___|__,|_|    |_____|__,|  _|__,|___|_|_| |_  |
                               |_|               |___|
)" << endl << endl;

			cout << "		Enter mass (kgs): ";
			cin >> mass;
			cout << "		Enter change in temperature (degree celcius): ";
			cin >> deltaT;
			cout << "		Enter specific heat capacity (J/kgK): ";
			cin >> specificHeatCapacity;
			heatCapacity = mass * deltaT * specificHeatCapacity;
			cout << "		Heat Capacity: " << heatCapacity << endl;
			cout << "\n		Press any key to continue and X to exit...";
			choice = _getch();
			if (choice == 'x') return 0;
			break;
		case '2':
			system("color BF");
			system("cls");
			cout << R"(
 _____             _ ___ _        _____         _      _____                 _ _       
|   __|___ ___ ___|_|  _|_|___   |  |  |___ ___| |_   |     |___ ___ ___ ___|_| |_ _ _ 
|__   | . | -_|  _| |  _| |  _|  |     | -_| .'|  _|  |   --| .'| . | .'|  _| |  _| | |
|_____|  _|___|___|_|_| |_|___|  |__|__|___|__,|_|    |_____|__,|  _|__,|___|_|_| |_  |
      |_|                                                       |_|               |___|
)" << endl << endl;

			cout << "			Enter mass (kgs): ";
			cin >> mass;
			cout << "			Enter change in temperature (degree celcius): ";
			cin >> deltaT;
			cout << "			Enter heat capacity (J): ";
			cin >> heatCapacity;
			specificHeatCapacity = heatCapacity / (mass * deltaT);
			cout << "			Specific Heat Capacity: " << specificHeatCapacity << endl;
			cout << "\n			Press any key to continue and X to exit...";
			choice = _getch();
			if (choice == 'x') return 0;
			break;
		case '3':
			system("color AF");
			system("cls");
			cout << R"(
			 _____             
			|     |___ ___ ___ 
			| | | | .'|_ -|_ -|
			|_|_|_|__,|___|___|
)" << endl << endl;
			cout << "			Enter heat capacity (J): ";
			cin >> heatCapacity;
			cout << "			Enter change in temperature (degree celcius): ";
			cin >> deltaT;
			cout << "			Enter specific heat capacity (J/kgK): ";
			cin >> specificHeatCapacity;
			mass = heatCapacity / (deltaT * specificHeatCapacity);
			cout << "			Mass: " << mass << endl;
			cout << "\n			Press any key to continue and X to exit...";
			choice = _getch();
			if (choice == 'x') return 0;
			break;
		case '4':
			system("color CF");
			system("cls");
			cout << R"(
 _____ _                      _        _____                           _               
|     | |_ ___ ___ ___ ___   |_|___   |_   _|___ _____ ___ ___ ___ ___| |_ _ _ ___ ___ 
|   --|   | .'|   | . | -_|  | |   |    | | | -_|     | . | -_|  _| .'|  _| | |  _| -_|
|_____|_|_|__,|_|_|_  |___|  |_|_|_|    |_| |___|_|_|_|  _|___|_| |__,|_| |___|_| |___|
                  |___|                               |_|                              
)" << endl << endl;

			cout << "				Enter mass (kgs): ";
			cin >> mass;
			cout << "				Enter heat capacity (J): ";
			cin >> heatCapacity;
			cout << "				Enter specific heat capacity (J/kgK): ";
			cin >> specificHeatCapacity;
			deltaT = heatCapacity / (mass * specificHeatCapacity);
			cout << "				Change in Temperature: " << deltaT << endl;
			cout << "\n				Press any key to continue and X to exit...";
			choice = _getch();
			if (choice == 'x') return 0;
			break;
		case '5':
			cout << "\n				Exiting...";
			Sleep(1000);
			return 0;
			break;
		default:
			cout << "\n				Invalid choice" << endl;
			Sleep(1000);
		}
	} while (choice != '5');
}
