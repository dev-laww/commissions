#include <iostream>
#include <iomanip>
#include <conio.h>
#include <windows.h>
#include <math.h>

using namespace std;

void printNextLine(int n) {
	for (int i = 0; i < n; i++) {
		cout << endl;
	}
}

void wait() {
	printNextLine(13);
	cout << setw(43) << "";
	cout << "Just wait for a couple of seconds..." << endl;
	Sleep(2000);
	system("cls");
}


int main()
{
	system("cls");

	char choose;
	float fB, fC, fD, fE, fF, fG, FH, GH, DF, FG, DG, EG, DE, CD, CE, BD, BC, AC, AB;
	float length, height, fAs, fHs;

	float fAup, fAlow, fBb, fCc, fDd, fEe, fGg, bAB, bAC, bBC, bBD, bBE, bCE, bDE, bDF, bDG, bEF, bEH, bFG;
	float fHss, fGys, fGxs;
	double pi = 3.141592653589793238;
	float x = pi / 6, y = pi / 3;

	float fcA, fcB, fcC, fcD, fcE, fcF, fcG, cAB, cAC, cBC, cBD, cCD, cCE, cDE, cDF, cDG, cEF, cEG, cFG;
	float LcA, HcA, fcAh, fcGh;

	int progress = 0;
	const int total = 10;
	char a = 177, b = 219;

	// Get the console handle
	HANDLE consoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);

	// Set the console window size
	SMALL_RECT windowRect;
	windowRect.Left = 0;
	windowRect.Top = 0;
	windowRect.Right = 99;
	windowRect.Bottom = 79;
	SetConsoleWindowInfo(consoleHandle, TRUE, &windowRect);

	// Set the console buffer size
	COORD bufferSize;
	bufferSize.X = 100;
	bufferSize.Y = 80;
	SetConsoleScreenBufferSize(consoleHandle, bufferSize);


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
	system("color DF");
	printNextLine(6);
	cout << R"(
		 _____ _____  __ __  ____   ____      ____   __  _   ____   _   __  __ ____  _   ____ 
		|_   _|| () )|  |  |(_ (_` (_ (_`    / () \ |  \| | / () \ | |__\ \/ /(_ (_`| | (_ (_`
		  |_|  |_|\_\ \___/.__)__).__)__)   /__/\__\|_|\__|/__/\__\|____||__|.__)__)|_|.__)__)
)";
	cout << "\n\n" << setw(45) << "";
	cout << "a program by: Donnie O. Tidor" << endl;
	cout << setw(52) << "";
	cout << "BSCE 2 SC: 1886" << endl;
	cout << "\n\n" << setw(45) << "";
	cout << "Press any key to continue...";
	_getch();
	
	system("cls");
	system("color 9F");
	printNextLine(8);
	cout << R"(
                                               __  __  ____  __  _  __ __ 
                                              |  \/  || ===||  \| ||  |  |
                                              |_|\/|_||____||_|\__| \___/ 
)";
	cout << "\n\n" << setw(38) << "";
	cout << "Chose among the three types of trusses below:" << endl;
	cout << setw(33) << "";
	cout << "(a) Howe Truss" << setw(3) << "" << "(b) Cantilever Truss" << setw(3) << "" << "(c) Fink Truss" << endl;
	cout << "\n\n" << setw(40) << "";
	cout << "Press the letter to solve or x to exit...";
	choose = _getch();
	if (tolower(choose) == 'x') {
		return 0;
	}

	if (choose == 'a' || choose == 'A')
	{	
		system("cls");
		cout << R"(
			    _   _  ____ __    __ ____     _____ _____  __ __  ____   ____ 
			   | |_| |/ () \\ \/\/ /| ===|   |_   _|| () )|  |  |(_ (_` (_ (_`
			   |_| |_|\____/ \_/\_/ |____|     |_|  |_|\_\ \___/.__)__).__)__)
		)";
		cout << "\n\n" << setw(10) << "";
		cout << "We are about to make a truss analysis on the Howe Truss. Fill in the necessay values to get started.\n" << endl;
		cout << setw(45) << "";
		cout << "For Force at joint B: ";
		cin >> fB;
		cout << setw(45) << "";
		cout << "For Force at joint C: ";
		cin >> fC;
		cout << setw(45) << "";
		cout << "For Force at joint D: ";
		cin >> fD;
		cout << setw(45) << "";
		cout << "For Force at joint E: ";
		cin >> fE;
		cout << setw(45) << "";
		cout << "For Force at joint F: ";
		cin >> fF;
		cout << setw(45) << "";
		cout << "For Force at joint G: ";
		cin >> fG;
		cout << setw(45) << "";
		cout << "Height of the truss: ";
		cin >> height;
		cout << setw(45) << "";
		cout << "Length of each panel: ";
		cin >> length;

		if (fB < 0 || fC < 0 || fD < 0 || fE < 0 || fF < 0 || fG < 0 || height < 0 || length < 0)
		{
			cout << "\n\n" << setw(30) << "";
			cout << "You have entered a negative value. Please enter a positive value." << endl;
			goto here;
		}

		system("cls");
		

		fAs = (fB * (3 * length) + fC * (3 * length) + fD * (2 * length) + fE * (2 * length) + fF * (length)+fG * (length)) / (4 * length);

		fHs = fB + fC + fD + fE + fF + fG - fAs;

		wait();

		float length2 = pow(length, 2);
		float height2 = pow(height, 2);
		float hyp = sqrt(length2 + height2);

		FH = -(fHs) / (height / hyp);
		GH = -FH * (length / hyp);
		DF = FH * (length / hyp);
		FG = -FH * (height / hyp) - fF;
		DG = fG - FG / (height / hyp);
		EG = GH - DG * (length / hyp);
		DE = fE;
		CE = EG;
		CD = (-DE - DG * (height / hyp) - fD) / (height / hyp);
		BD = DF + DG * (length / hyp) - CD * (length / hyp);
		BC = fC - CD * (height / hyp);
		AC = CE + CD * (length / hyp);
		AB = (-fB - BC) / (height / hyp);

		printNextLine(5);
		cout << setw(45) << "";
		cout << "The support force A is " << fAs << "." << endl;
		cout << setw(45) << "";
		cout << "The support force H is " << fHs << "." << endl;
		cout << "\n" << setw(30) << "";
		cout << "Force of Member AB = " << AB << " N or " << abs(AB) << ((AB > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member AC = " << AC << " N or " << abs(AC) << ((AC > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member BC = " << BC << " N or " << abs(BC) << ((BC > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member BD = " << BD << " N or " << abs(BD) << ((BD > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member CD = " << CD << " N or " << abs(CD) << ((CD > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member CE = " << CE << " N or " << abs(CE) << ((CE > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DE = " << DE << " N or " << abs(DE) << ((DE > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DF = " << DF << " N or " << abs(DF) << ((DF > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DG = " << DG << " N or " << abs(DG) << ((DG > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member EG = " << EG << " N or " << abs(EG) << ((EG > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member FG = " << FG << " N or " << abs(FG) << ((FG > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member FH = " << FH << " N or " << abs(FH) << ((FH > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member GH = " << GH << " N or " << abs(GH) << ((GH > 0) ? " N (Tension)" : " N (Compression)");
		cout << "\n\n" << setw(40) << "";
	}

	else if (choose == 'b' || choose == 'B')
	{
		system("cls");
		cout << R"(
		 ____   ____   __  _  _____  _  _     ____ __  __ ____ _____     _____ _____  __ __  ____   ____ 
		/ (__` / () \ |  \| ||_   _|| || |__ | ===|\ \/ /| ===|| () )   |_   _|| () )|  |  |(_ (_` (_ (_`
		\____)/__/\__\|_|\__|  |_|  |_||____||____| \__/ |____||_|\_\     |_|  |_|\_\ \___/.__)__).__)__)
)";
		cout << "\n\n" << setw(10) << "";
		cout << "We are about to make a truss analysis on the Cantilever Truss. Fill in the necessay values to get started.\n" << endl;
		cout << setw(45) << "";
		cout << "Force acting on Joint A (upper): ";
		cin >> fAup;
		cout << setw(45) << "";
		cout << "Force acting on Joint A (lower): ";
		cin >> fAlow;
		cout << setw(45) << "";
		cout << "Force acting on Joint B: ";
		cin >> fBb;
		cout << setw(45) << "";
		cout << "Force acting on Joint C: ";
		cin >> fCc;
		cout << setw(45) << "";
		cout << "Force acting on Joint D: ";
		cin >> fDd;
		cout << setw(45) << "";
		cout << "Force acting on Joint E: ";
		cin >> fEe;
		cout << setw(45) << "";
		cout << "Force acting on Joint G: ";
		cin >> fGg;

		if (fAup < 0 || fAlow < 0 || fBb < 0 || fCc < 0 || fDd < 0 || fEe < 0 || fGg < 0)
		{	
			cout << "\n\n\n" << setw(35) << "";
			cout << "You have entered a negative value. Please enter a positive value." << endl;
			goto here;
		}

		system("cls");

		float GHh = 3 * sin(x);

		fHss = (fDd * (1) + fBb * (2) + fAup * (3) + fAlow * (3 * cos(x)) + fCc * (2 * cos(x)) + fEe * (cos(x))) / GHh;
		fGxs = fHss - fGg * (sin(x)) - fDd * (cos(y)) - fBb * (cos(y)) - fAup * (cos(y));
		fGys = fGg * (cos(x)) + fDd * (sin(y)) + fBb * (sin(y)) + fAup * (sin(y)) + fAlow + fCc + fEe;

		wait();

		bEH = -fHss;
		bAB = ((fAup * (sin(y))) + fAlow) / sin(x);
		bAC = (-fAup * (cos(y))) - (bAB * cos(x));

		bBC = fCc;
		bCE = bAC;

		bBE = (-fBb - bBC * (sin(y))) / (sin(y));
		bBD = bAB + bBC * (cos(y)) - bBE * (cos(y));

		bEF = (bCE + bBE * (cos(x)) - bEH) / (sin(x));
		bDE = fEe - (bBE * (sin(x))) - bEF * (cos(x));

		bDF = 0;
		bDG = bBD + (bDE * (sin(x)));

		bFG = bEF;

		printNextLine(5);
		cout << "\n" << setw(40) << "";
		cout << "The support force H is " << fHss << " N.";
		cout << "\n" << setw(40) << "";
		cout << "The Horizontal Hinge force H is " << fGxs << " N.";
		cout << "\n" << setw(40) << "";
		cout << "The Vertical Hinge force H is " << fGys << " N.";
		cout << "\n\n" << setw(30) << "";
		cout << "Force of Member AB = " << bAB << " N or " << abs(bAB) << ((bAB > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member AC = " << bAC << " N or " << abs(bAC) << ((bAC > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member BC = " << bBC << " N or " << abs(bBC) << ((bBC > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member BD = " << bBD << " N or " << abs(bBD) << ((bBD > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of membet BE = " << bBE << " N or " << abs(bBE) << ((bBE > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member CE = " << bCE << " N or " << abs(bCE) << ((bCE > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DE = " << bDE << " N or " << abs(bDE) << ((bDE > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DF = " << bDF << " N or " << abs(bDF) << ((bDF > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member EF = " << bEF << " N or " << abs(bEF) << ((bEF > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member EH = " << bEH << " N or " << abs(bEH) << ((bEH > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member FG = " << bFG << " N or " << abs(bFG) << ((bFG > 0) ? "N (Tension)" : "N (Compression)");
	}

	else if (choose == 'c' || choose == 'C')
	{
		system("cls");
		cout << R"(
			 ____  _  __  _  __  __     _____ _____  __ __  ____   ____ 
			| ===|| ||  \| ||  |/  /   |_   _|| () )|  |  |(_ (_` (_ (_`
			|__|  |_||_|\__||__|\__\     |_|  |_|\_\ \___/.__)__).__)__)
)";
		cout << "\n\n" << setw(10) << "";
		cout << "We are about to make a truss analysis on the Fink Truss. Fill in the necessay values to get started.\n" << endl;
		cout << setw(45) << "";
		cout << "Force acting on Joint A: ";
		cin >> fcA;
		cout << setw(45) << "";
		cout << "Force acting on Joint B: ";
		cin >> fcB;
		cout << setw(45) << "";
		cout << "Force acting on Joint C: ";
		cin >> fcC;
		cout << setw(45) << "";
		cout << "Force acting on Joint D: ";
		cin >> fcD;
		cout << setw(45) << "";
		cout << "Force acting on Joint E: ";
		cin >> fcE;
		cout << setw(45) << "";
		cout << "Force acting on Joint F: ";
		cin >> fcF;
		cout << setw(45) << "";
		cout << "Force acting on Joint G: ";
		cin >> fcG;
		cout << setw(45) << "";
		cout << "Length of each panel: ";
		cin >> LcA;
		cout << setw(45) << "";
		cout << "Height of the truss: ";
		cin >> HcA;

		if (fcA < 0 || fcB < 0 || fcC < 0 || fcD < 0 || fcE < 0 || fcF < 0 || fcG < 0 || LcA <= 0 || HcA <= 0)
		{
			cout << "\n\n\n" << setw(35) << "";
			cout << "You have entered a negative value. Please enter a positive value." << endl;
			goto here;
		}

		system("cls");

		float L2c = pow(LcA, 2);
		float H2c = pow(HcA, 2);
		float hypC = sqrt(L2c + H2c);
		float L2c2 = pow((LcA / 2), 2);
		float hypCs = sqrt(L2c2 + H2c);
		float hypCl = sqrt((4 + L2c) / 4);
		float hypCl2 = sqrt(H2c + ((9 / 4) * L2c));

		fcGh = (fcB * ((LcA) * 3 / 4) + fcC * (LcA)+fcD * (LcA + (LcA / 2)) + fcE * (LcA * 2) + fcF * ((LcA * 2) + (LcA / 4)) + fcG * (LcA * 3)) / (LcA * 3);
		fcAh = fcA + fcB + fcC + fcD + fcE + fcF + fcG - fcGh;

		wait();

		cAB = (fcA - fcAh) / (HcA / hypCl2);
		cAC = -cAB * (((3 * LcA) / 2) / hypCl2);

		cFG = (fcG - fcGh) / (HcA / hypCl2);
		cEG = -cFG * (((3 * LcA) / 2) / hypCl2);

		cCE = ((3 * LcA * fcGh) / 2 - fcF * ((3 * LcA) / 4) - fcG * ((3 * LcA) / 2) - fcE * (LcA / 2)) / HcA;
		cDE = (((3 * fcF * LcA) / 4) + (fcE * (LcA))) / (LcA * (HcA / hypCs));

		cDF = ((-cCE - cDE * ((LcA / 2) / hypCs)) / ((LcA / 2) / (hypCl)));
		cDG = ((-cCE - cDE * ((LcA / 2) / hypCs)) / ((LcA / 2) / (hypCl)));

		cCD = (((3 * fcB * LcA) / 4) + (fcC * (LcA))) / (LcA * (HcA / hypCs));
		cBD = ((-cCE - cCD * ((LcA / 2) / hypCs)) / ((LcA / 2) / (hypCl)));

		cEF = (-cFG - cDF * (((3 * LcA) / 2) / hypCl2));
		cBC = (-cBD - cAB * (((3 * LcA) / 2) / hypCl2));

		printNextLine(5);
		cout << setw(45) << "";
		cout << "\nThe support force G is " << fcGh << " N.";
		cout << setw(45) << "";
		cout << "\nThe Hinge force A is " << fcAh << " N.";
		cout << "\n" << setw(50) << "";
		cout << hypCl2 << endl;

		cout << "\n" << setw(30) << "";
		cout << "Force of Member AB = " << cAB << " N or " << abs(cAB) << ((cAB > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member AC = " << cAC << " N or " << abs(cAC) << ((cAC > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member BC = " << cBC << " N or " << abs(cBC) << ((cBC > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member BD = " << cBD << " N or " << abs(cBD) << ((cBD > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member CD = " << cCD << " N or " << abs(cCD) << ((cCD > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member CE = " << cCE << " N or " << abs(cCE) << ((cCE > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DE = " << cDE << " N or " << abs(cDE) << ((cDE > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DF = " << cDF << " N or " << abs(cDF) << ((cDF > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member DG = " << cDG << " N or " << abs(cDG) << ((cDG > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member EF = " << cEF << " N or " << abs(cEF) << ((cEF > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member EG = " << cEG << " N or " << abs(cEG) << ((cEG > 0) ? "N (Tension)" : "N (Compression)");
		cout << "\n" << setw(30) << "";
		cout << "Force of Member FG = " << cFG << " N or " << abs(cFG) << ((cFG > 0) ? "N (Tension)" : "N (Compression)");
	}

	else
	{	
		cout << "\n" << setw(38) << "";
		cout << "\nYou have entered an invalid letter." << endl;
		goto here;
	}


here:
	cout << "\n\n" << setw(38) << "";
	cout << "Press any key to continue or x to exit...";
	choose = _getch();
	if (tolower(choose) == 'x') {
		return 0;
	}
	else {
		system("cls");
		goto start;
	}

}