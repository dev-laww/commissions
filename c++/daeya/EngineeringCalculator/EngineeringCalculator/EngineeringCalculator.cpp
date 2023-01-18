#include <iostream>
#include <iomanip>
#include <conio.h>
#include <cmath>
#include <windows.h>

using namespace std;
  
int main()
{   
    int choice;
    double speed, frequency, wavelength, time, angularFrequency;
    char con;
    do 
    {   
		system("cls");
        system("color F0");
        cout << R"(
_________        .__               .__          __                
\_   ___ \_____  |  |   ____  __ __|  | _____ _/  |_  ___________ 
/    \  \/\__  \ |  | _/ ___\|  |  \  | \__  \\   __\/  _ \_  __ \
\     \____/ __ \|  |_\  \___|  |  /  |__/ __ \|  | (  <_> )  | \/
 \______  (____  /____/\___  >____/|____(____  /__|  \____/|__|   
        \/     \/          \/                \/                   )" << endl << endl;

        cout
            << "                    [1] Frequency\n"
            << "                    [2] Wavelength\n"
            << "                    [3] Angular Frequency\n"
            << "                    [4] Exit\n"
            << "\n                    Enter your choice: ";
        cin >> choice;

        switch (choice)
        {
        case 1:
            system("cls");
            system("color 4F");
            cout << R"(
___________                                                       
\_   _____/______   ____  ________ __   ____   ____   ____ ___.__.
 |    __) \_  __ \_/ __ \/ ____/  |  \_/ __ \ /    \_/ ___<   |  |
 |     \   |  | \/\  ___< <_|  |  |  /\  ___/|   |  \  \___\___  |
 \___  /   |__|    \___  >__   |____/  \___  >___|  /\___  > ____|
     \/                \/   |__|           \/     \/     \/\/    
)" << endl << endl;
            cout << "                    Enter speed (m/s): ";
            cin >> speed;
            cout << "                    Enter wavelength (m): ";
            cin >> wavelength;
            frequency = speed / wavelength;
            cout << "                    Frequency: " << frequency << endl << endl;
            cout << "                    Press any key to continue or X to exit...";
            con = _getch();
            if (con == 'x') return 0;
            break;
        case 2:
            system("cls");
            system("color 2F");
            cout << R"(
 __      __                    .__                         __  .__     
/  \    /  \_____ ___  __ ____ |  |   ____   ____    _____/  |_|  |__  
\   \/\/   /\__  \\  \/ // __ \|  | _/ __ \ /    \  / ___\   __\  |  \ 
 \        /  / __ \\   /\  ___/|  |_\  ___/|   |  \/ /_/  >  | |   Y  \
  \__/\  /  (____  /\_/  \___  >____/\___  >___|  /\___  /|__| |___|  /
       \/        \/          \/          \/     \//_____/           \/ 
)" << endl << endl;
            cout << "                    Enter speed (m/s): ";
            cin >> speed;
            cout << "                    Enter frequency (Hz): ";
            cin >> frequency;
            wavelength = speed / frequency;
            cout << "                    Wavelength: " << wavelength << endl << endl;
            cout << "                    Press any key to continue or X to exit...";
			con = _getch();
            if (con == 'x') return 0;
            break;
        case 3:
            system("cls");
            system("color 1F");
            cout << R"(
   _____                       .__                 ___________                                                       
  /  _  \   ____    ____  __ __|  | _____ _______  \_   _____/______   ____  ________ __   ____   ____   ____ ___.__.
 /  /_\  \ /    \  / ___\|  |  \  | \__  \\_  __ \  |    __) \_  __ \_/ __ \/ ____/  |  \_/ __ \ /    \_/ ___<   |  |
/    |    \   |  \/ /_/  >  |  /  |__/ __ \|  | \/  |     \   |  | \/\  ___< <_|  |  |  /\  ___/|   |  \  \___\___  |
\____|__  /___|  /\___  /|____/|____(____  /__|     \___  /   |__|    \___  >__   |____/  \___  >___|  /\___  > ____|
        \/     \//_____/                 \/             \/                \/   |__|           \/     \/     \/\/      
)" << endl << endl;
            cout << "                                        Enter time (s): ";
            cin >> time;
            angularFrequency = (2 * acos(-1.0)) / time;
            cout << "                                        Angular frequency: " << angularFrequency << endl << endl;
            cout << "                                        Press any key to continue or X to exit...";
            con = _getch();
            if (con == 'x') return 0;
            break;
        case 4:
            cout << "                    Exiting...";
            break;
        default:
            cout << "                    Invalid choice!";
        }
	} while (choice != 4);
    
    return 0;
}

