#include<iostream>
#include<iomanip>

using namespace std;

int main() {
	char luh;
	float density;
	float mass;
	float volume;
	float gravity = 9.8;
	int given;
	float d, m, g, v, w, mho;
	int type, t2;
	float Fb, Fb2, Fb3, moa, mfo;

	system("cls");
	system("color 78");
	cout << "\t\t\t  1. Volume and Mass" << endl;
	cout << "\t\t\t  2. Volume and Density" << endl;
	cout << "\t\t\t  3. Density and Mass" << endl;
	cout << "\t\t\t  What are the given variables? : ";
	cin >> given;

	if (given > 3, given <= 0) {
		cout << "\t\t\t\t  Uh-oh! It seems like you have enter an invalid input! " << endl;
		goto end;
	}

	switch (given) {
	case 1:
		cout << "\t\t\t\t __________________________________________" << endl;
		cout << "\n\t\t\t\t Enter the given Volume : ";
		cin >> volume;
		cout << "\t\t\t\t __________________________________________" << endl;
		cout << "\n\t\t\t\t Enter the given Mass : ";
		cin >> mass;
		cout << "\t\t\t\t __________________________________________" << endl;
		d = mass / volume;

		cout << "\n\t\t\t\t The total density of an object is " << d << endl;
		system("pause");

		goto solving;
		break;

	case 2:
		cout << "\t\t\t\t __________________________________________" << endl;
		cout << "\t\t\t\t Enter the given Volume : ";
		cin >> volume;
		cout << "\t\t\t\t __________________________________________" << endl;
		cout << "\t\t\t\t Enter the given Density : ";
		cin >> density;
		cout << "\t\t\t\t __________________________________________" << endl;
		mass = density * volume;

		cout << "\n\t\t\t\t The total Mass of an object is " << mass << endl;
		system("pause");
		goto solving;

	case 3:
		cout << "\t\t\t\t __________________________________________" << endl;
		cout << "\t\t\t\t Enter the given Mass : ";
		cin >> mass;
		cout << "\t\t\t\t __________________________________________" << endl;
		cout << "\t\t\t\t Enter the given Density : ";
		cin >> density;
		cout << "\t\t\t\t __________________________________________" << endl;
		v = mass / density;

		cout << "\n\t\t\t\t The total Volume of an object is " << v << endl;
		system("pause");
		goto c3;
		break;

	}

solving:
	system("cls");
	system("color 80");
	w = mass * gravity;
	cout << "\n\t\t\t\t   ___________________________";
	cout << "\n\t\t\t\t  |1. Water                   |" << endl;
	cout << "\t\t\t\t  |2. Alcohol (Ethanol)       |" << endl;
	cout << "\t\t\t\t  |3. Fuel Oil                |" << endl;
	cout << "\t\t\t\t  |___________________________|" << endl;
	cout << "\n\t\t\t\t Please select the Fluid Type: ";
	cin >> type;


	switch (type) {
	case 1:
		cout << "\t\t\t Selected Fluid : Water " << endl;
		cout << "\t\t\t Selected Fluid Density : 1000 kg/m3" << endl;
		w = mass * gravity;
		cout << "\t\t\t The weight of an object is : " << w << endl;
		cout << "\t\t\t__________________________________________________" << endl;
		cout << " \n\n\t\t\t The volume of water displaced by the object is " << volume << endl;
		cout << "\t\t\t__________________________________________________" << endl;
		mho = volume * 1000;
		cout << " \n\n\t\t\t The mass of water displaced by the object is " << mho << endl;
		cout << "\t\t\t ________________________________________________" << endl;
		Fb = mho * gravity;
		cout << "\n\t\t\t The Force of Bouyancy is " << Fb << endl;

		if (Fb >= w) {
			cout << "\t\t\t The object will float! " << endl;
			system("pause");
			goto end;
		}
		else {
			cout << "\t\t\t The object will sink!" << endl;
			system("pause");
			goto end;
		}
		break;

	case 2:
		cout << "\t\t\t Selected Fluid : Alcohol (Ethanol) " << endl;
		cout << "\t\t\t Selected Fluid Density : 785.10 kg/m3" << endl;
		w = mass * gravity;
		cout << "\t\t\t The weight of an object is : " << w << endl;
		cout << "\t\t\t__________________________________________________" << endl;
		cout << " \n\n\t\t\t The volume of water displaced by the object is " << volume << endl;
		cout << "\t\t\t__________________________________________________" << endl;
		moa = volume * 785.10;
		cout << " \n\n\t\t\t The mass of water displaced by the object is " << moa << endl;
		Fb2 = moa * gravity;
		cout << "\t\t\t________________________________________________" << endl;
		cout << "\n\t\t\t The Force of Bouyancy is " << Fb2 << endl;

		if (Fb2 >= w) {
			cout << "\t\t\t The object will float! " << endl;
			system("pause");
			goto end;
		}
		else {
			cout << "\t\t\t The object will sink!" << endl;
			system("pause");
			goto end;
		}
		break;

	case 3:
		cout << "\t\t\t Selected Fluid : Fuel Oil " << endl;
		cout << "\t\t\t Selected Fluid Density : 890kg/m3" << endl;
		w = mass * gravity;
		cout << "\t\t\t The weight of an object is : " << w << endl;
		cout << "\t\t\t __________________________________________________" << endl;
		cout << " \n\n\t\t\t The volume of water displaced by the object is " << volume << endl;
		cout << "\t\t\t__________________________________________________" << endl;
		mfo = volume * 890;
		cout << " \n\n\t\t\t The mass of water displaced by the object is " << mfo << endl;
		cout << "\t\t\t________________________________________________" << endl;
		Fb3 = mfo * gravity;
		cout << "\n\t\t\t The Force of Bouyancy is " << Fb3 << endl;

		if (Fb3 >= w) {
			cout << "\t\t\t The object will float! " << endl;
			system("pause");
			goto end;
		}
		else {
			cout << "\t\t\t The object will sink!" << endl;
			system("pause");
			goto end;

			break;
		}

	c3:
		system("cls");
		system("color 80");
		w = mass * gravity;
		cout << "\n\t\t\t\t   ___________________________";
		cout << "\n\t\t\t\t  |1. Water                   |" << endl;
		cout << "\t\t\t\t  |2. Alcohol (Ethanol)       |" << endl;
		cout << "\t\t\t\t  |3. Fuel Oil                |" << endl;
		cout << "\t\t\t\t  |___________________________|" << endl;
		cout << "\n\t\t\t\t Please select the Fluid Type: ";
		cin >> t2;

		switch (t2) {

		case 1:
			cout << "\t\t\t Selected Fluid : Water " << endl;
			cout << "\t\t\t Selected Fluid Density : 1000 kg/m3" << endl;
			w = mass * gravity;
			cout << "\t\t\t The weight of an object is : " << w << endl;
			cout << "\t\t\t__________________________________________________" << endl;
			cout << " \n\n\t\t\t The volume of water displaced by the object is " << v << endl;
			cout << "\t\t\t__________________________________________________" << endl;
			mho = v * 1000;
			cout << " \n\n\t\t\t The mass of water displaced by the object is " << mho << endl;
			cout << "\t\t\t ________________________________________________" << endl;
			Fb = mho * gravity;
			cout << "\n\t\t\t The Force of Bouyancy is " << Fb << endl;

			if (Fb >= w) {
				cout << "\t\t\t The object will float! " << endl;
				system("pause");
				goto end;
			}
			else {
				cout << "\t\t\t The object will sink!" << endl;
				system("pause");
				goto end;
			}

			break;

		case 2:
			cout << "\t\t\t Selected Fluid : Alcohol (Ethanol) " << endl;
			cout << "\t\t\t Selected Fluid Density : 785.10 kg/m3" << endl;
			w = mass * gravity;
			cout << "\t\t\t The weight of an object is : " << w << endl;
			cout << "\t\t\t__________________________________________________" << endl;
			cout << " \n\n\t\t\t The volume of water displaced by the object is " << v << endl;
			cout << "\t\t\t__________________________________________________" << endl;
			moa = v * 785.10;
			cout << " \n\n\t\t\t The mass of water displaced by the object is " << moa << endl;
			Fb2 = moa * gravity;
			cout << "\t\t\t________________________________________________" << endl;
			cout << "\n\t\t\t The Force of Bouyancy is " << Fb2 << endl;

			if (Fb2 >= w) {
				cout << "\t\t\t The object will float! " << endl;
				system("pause");
				goto end;
			}
			else {
				cout << "\t\t\t The object will sink!" << endl;
				system("pause");
				goto end;
			}

			break;

		case 3:
			cout << "\t\t\t Selected Fluid : Fuel Oil " << endl;
			cout << "\t\t\t Selected Fluid Density : 890kg/m3" << endl;
			w = mass * gravity;
			cout << "\t\t\t The weight of an object is : " << w << endl;
			cout << "\t\t\t __________________________________________________" << endl;
			cout << " \n\n\t\t\t The volume of water displaced by the object is " << v << endl;
			cout << "\t\t\t__________________________________________________" << endl;
			mfo = v * 890;
			cout << " \n\n\t\t\t The mass of water displaced by the object is " << mfo << endl;
			cout << "\t\t\t________________________________________________" << endl;
			Fb3 = mfo * gravity;
			cout << "\n\t\t\t The Force of Bouyancy is " << Fb3 << endl;

			if (Fb3 >= w) {
				cout << "\t\t\t The object will float! " << endl;
				system("pause");
				goto end;
			}
			else {
				cout << "\t\t\t The object will sink!" << endl;
				system("pause");
				goto end;

				break;
			}
		}

	end:
		cout << "Press any key to try again | Press 'x' to terminate the program" << endl;
		cin >> luh;

		if (luh != 'x') {
			return main();
		}
		else {
			cout << " Thenk you and have an awesome day ahead!";
			return 0;
		}
	}
}
