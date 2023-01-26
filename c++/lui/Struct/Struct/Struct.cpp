#include <iostream>
#include <iomanip>
#include <fstream>

using namespace std;

const int SIZE = 10;

struct datePurchase {
    int month;
    int day;
    int year;
};

struct ShoeRec {
    string StockNumber;
    string ShoeType;
    string ShoeBrand;
    datePurchase DatePurchase;
    int shoeQuantity;
    double shoeCost;
    double TotalValue;
};


void inputShoe(ShoeRec getshoe[]);
void compVal(ShoeRec tValue[]);

int main() {
    ShoeRec shoe[SIZE];
    inputShoe(shoe);
    compVal(shoe);

    ofstream file("shoelnventory.txt");
    file << setw(15) << "StockNumber" << setw(15) << "ShoeType" << setw(15) << "ShoeBrand" << setw(15) << "DatePurchase" << setw(15) << "shoeQuantity" << setw(15) << fixed << setprecision(2) << "shoeCost" << setw(15) << fixed << setprecision(2) << "TotalValue" << endl;
    for (int i = 0; i < SIZE; i++) {
        file << setw(15) << shoe[i].StockNumber << setw(15) << shoe[i].ShoeType << setw(15) << shoe[i].ShoeBrand << setw(7) << shoe[i].DatePurchase.month << '-' << shoe[i].DatePurchase.day << '-' << shoe[i].DatePurchase.year << setw(15) << shoe[i].shoeQuantity << setw(15) << fixed << setprecision(2) << shoe[i].shoeCost << setw(15) << fixed << setprecision(2) << shoe[i].TotalValue << endl;
    }
    file.close();
    return 0;
}

void inputShoe(ShoeRec getshoe[]) {
    for (int i = 0; i < 1; i++) {
        try {
            cout << "Enter the StockNumber: ";
            cin >> getshoe[i].StockNumber;
            if (getshoe[i].StockNumber.length() != 8)
                throw getshoe[i].StockNumber;

            cout << "Enter the ShoeType: ";
            cin >> getshoe[i].ShoeType;

            cout << "Enter the ShoeBrand: ";
            cin >> getshoe[i].ShoeBrand;

            cout << "Enter the month of purchase: ";
            cin >> getshoe[i].DatePurchase.month;
            if (getshoe[i].DatePurchase.month < 1 || getshoe[i].DatePurchase.month > 12)
                throw getshoe[i].DatePurchase.month;

            cout << "Enter the day of purchase: ";
            cin >> getshoe[i].DatePurchase.day;
            if (getshoe[i].DatePurchase.day < 1 || getshoe[i].DatePurchase.day > 31)
                throw getshoe[i].DatePurchase.day;

            cout << "Enter the year of purchase: ";
            cin >> getshoe[i].DatePurchase.year;
            if (getshoe[i].DatePurchase.year < 2000 || getshoe[i].DatePurchase.year > 2021)
                throw getshoe[i].DatePurchase.year;

            cout << "Enter the shoeQuantity: ";
            cin >> getshoe[i].shoeQuantity;

            if (getshoe[i].shoeQuantity < 1 || getshoe[i].shoeQuantity > 20)
                throw getshoe[i].shoeQuantity;

           

            cout << "Enter the shoeCost: ";
            cin >> getshoe[i].shoeCost;

            cout << endl;

        }
        catch (string e) {
            cout << "Error: StockNumber must be 8 characters long. Please re-enter." << endl;
            i--;
        }
        catch (int e) {
            if (e == getshoe[i].DatePurchase.month)
                cout << "Error: Month must be between 1-12. Please re-enter." << endl;
            else if (e == getshoe[i].DatePurchase.day)
                cout << "Error: Day must be between 1-31. Please re-enter." << endl;
            else if (e == getshoe[i].DatePurchase.year)
                cout << "Error: Year must be between 2000-2021. Please re-enter." << endl;
            else if (e == getshoe[i].shoeQuantity)
                cout << "Error: shoeQuantity must be between 1-20. Please re-enter." << endl;
            i--;
        }
    }
}

void compVal(ShoeRec tValue[]) {
    for (int i = 0; i < SIZE; i++) {
        tValue[i].TotalValue = tValue[i].shoeCost * tValue[i].shoeQuantity;
    }
}