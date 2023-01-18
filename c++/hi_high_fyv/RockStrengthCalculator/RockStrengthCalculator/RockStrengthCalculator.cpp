// ROCK STRENGTH CALCULATOR 

#include<iostream>
#include<iomanip>
#include<windows.h>
#include<conio.h>
#include<stdio.h>
#include<cmath>

using namespace std;
char HCM, opt;
double p = 0, a = 0, d = 0, t = 0, z = 0, f = 0, i = 0, compressive, tensile, shear;


int main()
{
	system("cls");
	system("color E0");

	cout << R"(
__________               __       _________ __                                 __  .__     
\______   \ ____   ____ |  | __  /   _____//  |________   ____   ____    _____/  |_|  |__  
 |       _//  _ \_/ ___\|  |/ /  \_____  \\   __\_  __ \_/ __ \ /    \  / ___\   __\  |  \ 
 |    |   (  <_> )  \___|    <   /        \|  |  |  | \/\  ___/|   |  \/ /_/  >  | |   Y  \
 |____|_  /\____/ \___  >__|_ \ /_______  /|__|  |__|    \___  >___|  /\___  /|__| |___|  /
        \/            \/     \/         \/                   \/     \//_____/           \/ 
           _________        .__               .__          __                              
           \_   ___ \_____  |  |   ____  __ __|  | _____ _/  |_  ___________               
           /    \  \/\__  \ |  | _/ ___\|  |  \  | \__  \\   __\/  _ \_  __ \              
           \     \____/ __ \|  |_\  \___|  |  /  |__/ __ \|  | (  <_> )  | \/              
            \______  (____  /____/\___  >____/|____(____  /__|  \____/|__|                 
                   \/     \/          \/                \/                                 
)" << endl;

	cout << "\n\n\t Name ";
	cout << "\n\t Stubcode ";
	cout << "\n\t -----------------------------------------------------";

	cout << "\n\t Choose: ";
	cout << "\n\t\t C - Compressive Strength ";
	cout << "\n\t\t T - Tensile Strength ";
	cout << "\n\t\t S - Shear strength ";
	cout << "\n\t -----------------------------------------------------";

	cout << "\n\t What type of rock strength you wanted to calculate? : ";
	cin >> opt;

	switch (tolower(opt)) 
	{
	case 'c':
		system("cls");
		system("color 9F");
		cout << R"(
_________                                     .__                         .__              
\_   ___ \  ____   _____ _____________   ____ |  |__   ____   ____   _____|__|__  __ ____  
/    \  \/ /  _ \ /     \\____ \_  __ \_/ __ \|  |  \_/ __ \ /    \ /  ___/  \  \/ // __ \ 
\     \___(  <_> )  Y Y  \  |_> >  | \/\  ___/|   Y  \  ___/|   |  \\___ \|  |\   /\  ___/ 
 \______  /\____/|__|_|  /   __/|__|    \___  >___|  /\___  >___|  /____  >__| \_/  \___  >
        \/             \/|__|               \/     \/     \/     \/     \/              \/ 
                _________ __                                 __  .__                       
               /   _____//  |________   ____   ____    _____/  |_|  |__                    
               \_____  \\   __\_  __ \_/ __ \ /    \  / ___\   __\  |  \                   
               /        \|  |  |  | \/\  ___/|   |  \/ /_/  >  | |   Y  \                  
              /_______  /|__|  |__|    \___  >___|  /\___  /|__| |___|  /                  
                      \/                   \/     \//_____/           \/                   
)" << endl;
		cout << "\n\t C= P/A ";
		cout << "\n\t\t Where: ";
		cout << "\n\t\t P = failure load (in N or lbs) ";
		cout << "\n\t\t A = cross-sectional area (in m2 or in2)";
		cout << "\n\n\t Enter value of P: ";
		cin >> p;
		cout << "\t Enter value of A: ";
		cin >> a;
		compressive = p / a;
		cout << setprecision(2) << fixed;
		cout << "\n\t\t The strength of the rock is " << compressive << " .";

		cout << "\n\n\t Click any key to calculate rock strength again and n to exit: ";
		HCM = _getch();
		if (HCM != 'n')
		{
			return main();
		}
		else
		{
			cout << "\n\n\t\t Thank you for the c++mazing journey with you, Ma'am! ";
			cout << "\t\t Merry Christmas and Happy New Year ! ";
			cout << "\t\t PADAYON, ENGINEERS! ";
		}
		system("pause");
		break;
	case 't':
		system("cls");
		system("color AF");
		cout << R"(
___________                  .__.__             _________ __                                 __  .__     
\__    ___/___   ____   _____|__|  |   ____    /   _____//  |________   ____   ____    _____/  |_|  |__  
  |    |_/ __ \ /    \ /  ___/  |  | _/ __ \   \_____  \\   __\_  __ \_/ __ \ /    \  / ___\   __\  |  \ 
  |    |\  ___/|   |  \\___ \|  |  |_\  ___/   /        \|  |  |  | \/\  ___/|   |  \/ /_/  >  | |   Y  \
  |____| \___  >___|  /____  >__|____/\___  > /_______  /|__|  |__|    \___  >___|  /\___  /|__| |___|  /
             \/     \/     \/             \/          \/                   \/     \//_____/           \/ 
)" << endl;
		cout << "\n\t T= 2P/(pi)(Dt) ";
		cout << "\n\t\t Where: ";
		cout << "\n\t\t P = failure load (in N or lbs) ";
		cout << "\n\t\t t = disc thickness ";
		cout << "\n\t\t D = disc diameter ";
		cout << "\n\n\t Enter value of P: ";
		cin >> p;
		cout << "\t Enter value of t: ";
		cin >> t;
		cout << "\t Enter value of D: ";
		cin >> d;
		tensile = (2 * p) / (3.1416 * d * t);
		cout << setprecision(2) << fixed;
		cout << "\n\t\t The strength of the rock is " << tensile << " .";

		cout << "\n\n\t Click any key to calculate rock strength again and n to exit: ";
		HCM = _getch();
		if (HCM != 'n')
		{
			return main();
		}
		else
		{
			cout << "\n\n\t\t Thank you for the c++mazing journey with you, Ma'am! ";
			cout << "\t\t Merry Christmas and Happy New Year ! ";
			cout << "\t\t PADAYON, ENGINEERS! ";
		}
		system("pause");
		break;
	case 's':
		system("cls");
		system("color BF");
		cout << R"(
  _________.__                            _________ __                                 __  .__     
 /   _____/|  |__   ____ _____ _______   /   _____//  |________   ____   ____    _____/  |_|  |__  
 \_____  \ |  |  \_/ __ \\__  \\_  __ \  \_____  \\   __\_  __ \_/ __ \ /    \  / ___\   __\  |  \ 
 /        \|   Y  \  ___/ / __ \|  | \/  /        \|  |  |  | \/\  ___/|   |  \/ /_/  >  | |   Y  \
/_______  /|___|  /\___  >____  /__|    /_______  /|__|  |__|    \___  >___|  /\___  /|__| |___|  /
        \/      \/     \/     \/                \/                   \/     \//_____/           \/ 
)" << endl;
		cout << "\n\t S= Z+Ctan(t) ";
		cout << "\n\t\t Where: ";
		cout << "\n\t\t Z = cohesion ";
		cout << "\n\t\t t = slope angle";
		cout << "\n\t\t C = compressive strength";
		cout << "\n\n\t Enter value of Z: ";
		cin >> z;
		cout << "\t Enter value of t: ";
		cin >> f;
		cout << "\t Enter value of C: ";
		cin >> i;
		shear = z + i * tan(f);
		cout << setprecision(2) << fixed;
		cout << "\n\t\t The strength of the rock is " << shear << " .";

		cout << "\n\n\t Click any key to calculate rock strength again and n to exit: ";
		HCM = _getch();
		if (HCM != 'n')
		{
			return main();
		}
		else
		{
			cout << "\n\n\t\t Thank you for the c++mazing journey with you, Ma'am! ";
			cout << "\t\t Merry Christmas and Happy New Year ! ";
			cout << "\t\t PADAYON, ENGINEERS! ";
		}
		system("pause");
		break;
	default:
		system("cls");
		system ("color 04");
		cout << "\n\t I N V A L I D  I N P U T ! ";
		cout << "\n\n\t Click any key to calculate rock strength again and n to exit: ";
		HCM = _getch();
		if (HCM != 'n')
		{
			return main();
		}
		else
		{
			cout << "\n\n\t\t Thank you for the c++mazing journey with you, Ma'am! ";
			cout << "\n\t\t Merry Christmas and Happy New Year ! ";
			cout << "\n\t\t PADAYON, ENGINEERS! ";
		}
		system("pause");
	}
	return 0;
}
