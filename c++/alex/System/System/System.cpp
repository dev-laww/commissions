#include<iostream>
#include<iomanip>
#include<windows.h>
using namespace std;
const int maxrow = 100;

void gotoxy(int x, int y) {
	COORD co;
	co.X = x;
	co.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), co);
}

struct data {
	int debitcash1; 				//Jan 2
	int creditcapital1;

	int debittaxlicenses1;			//Jan 4
	int creditcash1;

	int debitvideotapes1;			//Jan 5
	int creditaccpayable1;

	int debitfurnfix1;				//Jan 6
	int creditcash2;

	int debitcash2;					//Jan 8
	int creditrentalrevenue1;

	int debitaccreceivable1;		//Jan 9
	int creditrentalrevenue2;

	int debitrentexpense1;			//Jan 11
	int creditcash3;

	int debitutilitiesexpense1;		//Jan 12
	int creditcash4;

	int debitdrawings1;				//Jan 15
	int creditcash5;

	int debitcash3;					//Jan 20
	int creditaccreceivable1;

	int debitaccpayable1;			//Jan 22
	int creditcash6;

	int debitvideotapes2;			//Jan 25
	int creditcash7;
	int creditaccpayable2;

	int debitaccpayable2;			//Jan 28
	int creditcash8;

	int debitcash4;					//Jan 29
	int creditaccreceivable2;

	int debitsalariesexpense1;		//Jan 31
	int creditcash9;

	int cashdebit, cashcredit, cashtotal,
		accreceivablecredit, accreceivabletotal, videotapestotal,
		accpayabledebit, accpayablecredit, accpayabletotal,
		rentalrevenuetotal;

	int totaldebits, totalcredits, netincome, expensetotal;


	//FUNCTIONS
	void edit() {		//Add or Redo Journal Numbers function
		cout << "\t\t\t\t    Add/Edit Entries " << endl;
		cout << "\nDATE\t\tPARTICULARS\t\t\t DEBIT\t\t  CREDIT\n2022\n";
		cout << "\nJan\t2\tCash" "\t\t\tPhp\t";
		cin >> debitcash1;
		cout << "\t\t    J.Dominguez,Capital" "\t\t\tPhp\t";
		cin >> creditcapital1;
		cout << "\t\t\tTo record the \n\t\tinvestment of the owner." << endl;

		cout << "\n\t4\tTaxes & Licenses" "\t\t";
		cin >> debittaxlicenses1;
		cout << "\t\t    Cash" "\t\t\t\t\t";
		cin >> creditcash1;
		cout << "\t\t\tTo record the \n\t\tpayment of registration \n\t\tfees." << endl;

		cout << "\n\t5\tVideo Tapes" "\t\t\t";
		cin >> debitvideotapes1;
		cout << "\t\t    Accounts Payable"   "\t\t\t\t";
		cin >> creditaccpayable1;
		cout << "\t\t\tTo record the \n\t\tacquisition of video rental\n\t\ttapes on account." << endl;

		cout << "\n\t6\tFurniture & Fixture" "\t\t";
		cin >> debitfurnfix1;
		cout << "\t\t    Cash"   "\t\t\t\t\t";
		cin >> creditcash2;
		cout << "\t\tTo record acquisition\n\t\tof furniture and fixture." << endl;

		cout << "\n\t8\tCash" "\t\t\t\t";
		cin >> debitcash2;
		cout << "\t\t    Rental Revenue"   "\t\t\t\t";
		cin >> creditrentalrevenue1;
		cout << "\t\t\tTo record the \n\t\trental revenue." << endl;

		cout << "\n\t9\tAccounts Receivable" "\t\t";
		cin >> debitaccreceivable1;
		cout << "\t\t    Rental Revenue"   "\t\t\t\t";
		cin >> creditrentalrevenue2;
		cout << "\t\t\tTo record the \n\t\trental revenue." << endl;

		cout << "\n\t11\tRent Expense" "\t\t\t";
		cin >> debitrentexpense1;
		cout << "\t\t    Cash"   "\t\t\t\t\t";
		cin >> creditcash3;
		cout << "\t\t\tTo record the \n\t\tpayment of rent." << endl;

		cout << "\n\t12\tUtilities Expense""\t\t";
		cin >> debitutilitiesexpense1;
		cout << "\t\t    Cash"   "\t\t\t\t\t";
		cin >> creditcash4;
		cout << "\t\t\tTo record the \n\t\tpayment of utilities." << endl;

		cout << "\n\t15\tJ. Dominguez, Drawings" "\t\t";
		cin >> debitdrawings1;
		cout << "\t\t    Cash"   "\t\t\t\t\t";
		cin >> creditcash5;
		cout << "\t\t\tTo record withdrawals \n\t\tof the owner." << endl;

		cout << "\n\t20\tCash" "\t\t\t\t";
		cin >> debitcash3;
		cout << "\t\t    Accounts Receivable"   "\t\t\t\t";
		cin >> creditaccreceivable1;
		cout << "\t\t\tTo record the \n\t\tcollection from customers." << endl;

		cout << "\n\t22\tAccounts Payable" "\t\t";
		cin >> debitaccpayable1;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t";
		cin >> creditcash6;
		cout << "\t\t\tTo record the \n\t\tpayment of obligation." << endl;

		cout << "\n\t25\tVideo Tape Supplies" "\t\t";
		cin >> debitvideotapes2;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t";
		cin >> creditcash7;
		cout << "\t\t  Accounts Payable"   "\t\t\t\t";
		cin >> creditaccpayable2;
		cout << "\t\t\tTo record the \n\t\tacquisition of videotapes." << endl;

		cout << "\n\t28\tAccounts Payable" "\t\t";
		cin >> debitaccpayable2;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t";
		cin >> creditcash8;
		cout << "\t\t\tTo record the\n\t\tpaymentof obligation." << endl;

		cout << "\n\t29\tCash" "\t\t\t\t";
		cin >> debitcash4;
		cout << "\t\t   Accounts Receivable" "\t\t\t\t";
		cin >> creditaccreceivable2;
		cout << "\t\t\tTo record the \n\t\tcollection from customers." << endl;

		cout << "\n\t31\tSalaries Expense" "\t\t";
		cin >> debitsalariesexpense1;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t";
		cin >> creditcash9;
		cout << "\t\t\tTo record the \n\t\tpayment of wages." << endl;

		cashdebit = debitcash1 + debitcash2 + debitcash3 + debitcash4;
		cashcredit = creditcash1 + creditcash2 + creditcash3 + creditcash4 +
			creditcash5 + creditcash6 + creditcash7 + creditcash8 + creditcash9;
		if (cashdebit > cashcredit)
			cashtotal = cashdebit - cashcredit;
		if (cashdebit < cashcredit)
			cashtotal = cashcredit - cashdebit;

		accreceivablecredit = creditaccreceivable1 + creditaccreceivable2;
		accreceivabletotal = debitaccreceivable1 - accreceivablecredit;

		videotapestotal = debitvideotapes1 + debitvideotapes2;

		accpayabledebit = debitaccpayable1 + debitaccpayable2;
		accpayablecredit = creditaccpayable1 + creditaccpayable2;
		accpayabletotal = accpayabledebit - accpayablecredit;

		rentalrevenuetotal = creditrentalrevenue1 + creditrentalrevenue2;

		expensetotal = debitrentexpense1 + debitutilitiesexpense1 + debittaxlicenses1 + debitsalariesexpense1;

		totaldebits = cashtotal + videotapestotal + debitfurnfix1 + debitdrawings1 + debitrentexpense1 +
			debitutilitiesexpense1 + debittaxlicenses1 + debitsalariesexpense1;
		totalcredits = creditcapital1 + rentalrevenuetotal;

		netincome = rentalrevenuetotal - expensetotal;
	}

	void journal() {
		cout << "\nDATE\t\tPARTICULARS\t\t\t DEBIT\t\t  CREDIT\n2022\n";
		cout << "\nJan\t2\tCash" "\t\t\tPhp\t" << debitcash1 << endl;
		cout << "\t\t    J.Dominguez,Capital" "\t\t\tPhp\t" << creditcapital1 << endl;
		cout << "\t\t\tTo record the \n\t\tinvestment of the owner." << endl;

		cout << "\n\t4\tTaxes & Licenses" "\t\t" << debittaxlicenses1 << endl;
		cout << "\t\t    Cash" "\t\t\t\t\t" << creditcash1 << endl;
		cout << "\t\t\tTo record the \n\t\tpayment of registration \n\t\tfees." << endl;

		cout << "\n\t5\tVideo Tapes" "\t\t\t" << debitvideotapes1 << endl;
		cout << "\t\t    Accounts Payable"   "\t\t\t\t" << creditaccpayable1 << endl;
		cout << "\t\t\tTo record the \n\t\tacquisition of video rental\n\t\ttapes on account." << endl;

		cout << "\n\t6\tFurniture & Fixture" "\t\t" << debitfurnfix1 << endl;
		cout << "\t\t    Cash"   "\t\t\t\t\t" << creditcash2 << endl;
		cout << "\t\tTo record acquisition\n\t\tof furniture and fixture." << endl;

		cout << "\n\t8\tCash" "\t\t\t\t" << debitcash2 << endl;
		cout << "\t\t    Rental Revenue"   "\t\t\t\t" << creditrentalrevenue1 << endl;
		cout << "\t\t\tTo record the \n\t\trental revenue." << endl;

		cout << "\n\t9\tAccounts Receivable" "\t\t" << debitaccreceivable1 << endl;
		cout << "\t\t    Rental Revenue"   "\t\t\t\t" << creditrentalrevenue2 << endl;
		cout << "\t\t\tTo record the \n\t\trental revenue." << endl;

		cout << "\n\t11\tRent Expense" "\t\t\t" << debitrentexpense1 << endl;
		cout << "\t\t    Cash"   "\t\t\t\t\t" << creditcash3 << endl;
		cout << "\t\t\tTo record the \n\t\tpayment of rent." << endl;

		cout << "\n\t12\tUtilities Expense""\t\t" << debitutilitiesexpense1 << endl;
		cout << "\t\t    Cash"   "\t\t\t\t\t" << creditcash4 << endl;
		cout << "\t\t\tTo record the \n\t\tpayment of utilities." << endl;

		cout << "\n\t15\tJ. Dominguez, Drawings" "\t\t" << debitdrawings1 << endl;
		cout << "\t\t    Cash"   "\t\t\t\t\t" << creditcash5 << endl;
		cout << "\t\t\tTo record withdrawals \n\t\tof the owner." << endl;

		cout << "\n\t20\tCash" "\t\t\t\t" << debitcash3 << endl;
		cout << "\t\t    Accounts Receivable"   "\t\t\t\t" << creditaccreceivable1 << endl;
		cout << "\t\t\tTo record the \n\t\tcollection from customers." << endl;

		cout << "\n\t22\tAccounts Payable" "\t\t" << debitaccpayable1 << endl;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t" << creditcash6 << endl;
		cout << "\t\t\tTo record the \n\t\tpayment of obligation." << endl;

		cout << "\n\t25\tVideo Tape Supplies" "\t\t" << debitvideotapes2 << endl;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t" << creditcash7 << endl;
		cout << "\t\t  Accounts Payable"   "\t\t\t\t" << creditaccpayable2 << endl;
		cout << "\t\t\tTo record the \n\t\tacquisition of videotapes." << endl;

		cout << "\n\t28\tAccounts Payable" "\t\t" << debitaccpayable2 << endl;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t" << creditcash8 << endl;
		cout << "\t\t\tTo record the\n\t\tpaymentof obligation." << endl;

		cout << "\n\t29\tCash" "\t\t\t\t" << debitcash4 << endl;
		cout << "\t\t   Accounts Receivable" "\t\t\t\t" << creditaccreceivable2 << endl;
		cout << "\t\t\tTo record the \n\t\tcollection from customers." << endl;

		cout << "\n\t31\tSalaries Expense" "\t\t" << debitsalariesexpense1 << endl;
		cout << "\t\t   Cash"   "\t\t\t\t\t\t" << creditcash9 << endl;
		cout << "\t\t\tTo record the \n\t\tpayment of wages." << endl;
	}

	void ledger() {		//Display Ledger function
		gotoxy(13, 2); cout << "CASH" << endl;
		gotoxy(7, 3); cout << "----------------" << endl;
		gotoxy(7, 4); cout << "Debit  |  Credit" << endl;
		gotoxy(7, 5); cout << "----------------" << endl;
		gotoxy(7, 6); cout << debitcash1;
		gotoxy(7, 7); cout << debitcash2;
		gotoxy(7, 8); cout << debitcash3;
		gotoxy(7, 9); cout << debitcash4;
		gotoxy(17, 6); cout << creditcash1;
		gotoxy(17, 7); cout << creditcash2;
		gotoxy(17, 8); cout << creditcash3;
		gotoxy(17, 9); cout << creditcash4;
		gotoxy(17, 10); cout << creditcash5;
		gotoxy(17, 11); cout << creditcash6;
		gotoxy(17, 12); cout << creditcash7;
		gotoxy(17, 13); cout << creditcash8;
		gotoxy(17, 14); cout << creditcash9;
		gotoxy(7, 15); cout << "------------------" << endl;
		gotoxy(7, 16); cout << cashdebit;
		gotoxy(17, 16); cout << cashcredit;
		gotoxy(7, 17); cout << "------------------" << endl;
		gotoxy(7, 18); cout << cashtotal;

		gotoxy(32, 2); cout << "ACCOUNTS RECEIVABLE" << endl;
		gotoxy(33, 3); cout << "----------------" << endl;
		gotoxy(33, 4); cout << "Debit  |  Credit" << endl;
		gotoxy(33, 5); cout << "----------------" << endl;
		gotoxy(33, 6); cout << debitaccreceivable1;
		gotoxy(43, 6); cout << creditaccreceivable1;
		gotoxy(43, 7); cout << creditaccreceivable2;
		gotoxy(33, 8); cout << "----------------" << endl;
		gotoxy(33, 9); cout << debitaccreceivable1;
		gotoxy(43, 9); cout << accreceivablecredit;
		gotoxy(33, 10); cout << "----------------" << endl;
		gotoxy(33, 11); cout << accreceivabletotal;

		gotoxy(59, 2); cout << "VIDEO RENTAL TAPES" << endl;
		gotoxy(60, 3); cout << "----------------" << endl;
		gotoxy(60, 4); cout << "Debit  |  Credit" << endl;
		gotoxy(60, 5); cout << "----------------" << endl;
		gotoxy(60, 6); cout << debitvideotapes1;
		gotoxy(60, 7); cout << debitvideotapes2;
		gotoxy(60, 8); cout << "----------------" << endl;
		gotoxy(60, 9); cout << videotapestotal;

		gotoxy(32, 15); cout << "FURNITURE & FIXTURE" << endl;
		gotoxy(33, 16); cout << "----------------" << endl;
		gotoxy(33, 17); cout << "Debit  |  Credit" << endl;
		gotoxy(33, 18); cout << "----------------" << endl;
		gotoxy(33, 19); cout << debitfurnfix1;

		gotoxy(60, 15); cout << "ACCOUNTS PAYABLE" << endl;
		gotoxy(60, 16); cout << "----------------" << endl;
		gotoxy(60, 17); cout << "Debit  |  Credit" << endl;
		gotoxy(60, 18); cout << "----------------" << endl;
		gotoxy(60, 19); cout << debitaccpayable1;
		gotoxy(60, 20); cout << debitaccpayable2;
		gotoxy(70, 19); cout << creditaccpayable1;
		gotoxy(70, 20); cout << creditaccpayable2;
		gotoxy(60, 21); cout << "----------------" << endl;
		gotoxy(60, 22); cout << accpayabledebit;
		gotoxy(70, 22); cout << accpayablecredit;
		gotoxy(60, 23); cout << "----------------" << endl;
		gotoxy(60, 24); cout << accpayabletotal;

		gotoxy(32, 28); cout << "J DOMINGUEZ, CAPITAL" << endl;
		gotoxy(33, 29); cout << "----------------" << endl;
		gotoxy(33, 30); cout << "Debit  |  Credit" << endl;
		gotoxy(33, 31); cout << "----------------" << endl;
		gotoxy(43, 32); cout << creditcapital1;

		gotoxy(57, 28); cout << "J DOMINGUEZ, DRAWINGS" << endl;
		gotoxy(60, 29); cout << "----------------" << endl;
		gotoxy(60, 30); cout << "Debit  |  Credit" << endl;
		gotoxy(60, 31); cout << "----------------" << endl;
		gotoxy(60, 32); cout << debitdrawings1;

		gotoxy(34, 41); cout << "RENTAL REVENUE" << endl;
		gotoxy(33, 42); cout << "----------------" << endl;
		gotoxy(33, 43); cout << "Debit  |  Credit" << endl;
		gotoxy(33, 44); cout << "----------------" << endl;
		gotoxy(43, 45); cout << creditrentalrevenue1;
		gotoxy(43, 46); cout << creditrentalrevenue2;
		gotoxy(33, 47); cout << "----------------" << endl;
		gotoxy(43, 48); cout << rentalrevenuetotal;

		gotoxy(59, 41); cout << "TAXES AND LICENCES" << endl;
		gotoxy(60, 42); cout << "----------------" << endl;
		gotoxy(60, 43); cout << "Debit  |  Credit" << endl;
		gotoxy(60, 44); cout << "----------------" << endl;
		gotoxy(60, 45); cout << debittaxlicenses1;

		gotoxy(35, 55); cout << "RENT EXPENSE" << endl;
		gotoxy(33, 56); cout << "----------------" << endl;
		gotoxy(33, 57); cout << "Debit  |  Credit" << endl;
		gotoxy(33, 58); cout << "----------------" << endl;
		gotoxy(33, 59); cout << debitrentexpense1;

		gotoxy(60, 55); cout << "UTILITIES EXPENSE" << endl;
		gotoxy(60, 56); cout << "----------------" << endl;
		gotoxy(60, 57); cout << "Debit  |  Credit" << endl;
		gotoxy(60, 58); cout << "----------------" << endl;
		gotoxy(60, 59); cout << debitutilitiesexpense1;

		gotoxy(46, 65); cout << "SALARIES EXPENSE" << endl;
		gotoxy(46, 66); cout << "----------------" << endl;
		gotoxy(46, 67); cout << "Debit  |  Credit" << endl;
		gotoxy(46, 68); cout << "----------------" << endl;
		gotoxy(46, 69); cout << debitsalariesexpense1;
	}

	void trialbalance() {
		cout << "\t\t\t\tGold and Silver Mine Video" << endl;
		cout << "\t\t\t\tTrial Balance" << endl;
		cout << "\t\t\t\tAs of January 31, 2022" << endl;
		cout << endl;
		cout << "\tPARTICULARS" "\t\t\t" "DEBIT\t\tCREDIT" << endl;
		cout << "Cash" "\t\t\t\t\t" << cashtotal << endl;
		cout << "Video Rental Tapes" "\t\t\t" << videotapestotal << endl;
		cout << "Furniture and Fixtures" "\t\t\t" << debitfurnfix1 << endl;
		cout << "J. Dominguez, Capital" "\t\t\t\t\t" << creditcapital1 << endl;
		cout << "J. Dominguez, Drawings" "\t\t\t" << debitdrawings1 << endl;
		cout << "Rental Revenue" "\t\t\t\t\t\t" << rentalrevenuetotal << endl;
		cout << "Rent Expense" "\t\t\t\t" << debitrentexpense1 << endl;
		cout << "Utilities Expense" "\t\t\t" << debitutilitiesexpense1 << endl;
		cout << "Taxes and Licenses" "\t\t\t" << debittaxlicenses1 << endl;
		cout << "Salaries and Wages Expense" "\t\t" << debitsalariesexpense1 << endl;
		cout << endl;
		cout << endl;
		cout << "TOTAL""\t\t\t\t\t" << totaldebits << "\t\t" << totalcredits << endl;
	}

	void finperformance() {
		cout << "\t\t\tGold and Silver Mine Video" << endl;
		cout << "\t\t\tStatement of Financial Performance" << endl;
		cout << "\t\t\tAs of January 31, 2022" << endl;
		cout << endl;
		cout << "REVENUE:" << endl;
		cout << "\tRental Revenue" "\t\t\t\t\t" << rentalrevenuetotal << endl;
		cout << "EXPENSES:" << endl;
		cout << "\tRent Expense" "\t\t\t\t" << debitrentexpense1 << endl;
		cout << "\tUtilities Expense" "\t\t\t" << debitutilitiesexpense1 << endl;
		cout << "\tTaxes and Licenses" "\t\t\t" << debittaxlicenses1 << endl;
		cout << "\tSalaries and Wages Expense" "\t\t" << debitsalariesexpense1 << "\t" << expensetotal << endl;
		cout << "\nNET INCOME" "\t\t\t\t\t" << netincome << endl;
		cout << "\n\nFinancial Performance presented...";
	}
}d;

int main() {
	int choice, x;
	system("cls");
	cout << "Menu" << endl;
	cout << "[1] Add/Redo Journal Values\n[2] Show Current Journal\n[3] T Table Ledger" << endl;
	cout << "[4] Trial Balance\n[5] Statement of Financial Performance\n[6] Exit";
	cout << "\nEnter your choice: ";
	cin >> choice;
	switch (choice) {
	case 1:
		system("cls");
		d.edit();
		cout << endl;
		do {
			cout << "[1]<<go back to menu:";
			cin >> x;
			if (x == 1) {
				main();
			}
		} while (x != 1);
		break;
	case 2:
		system("cls");
		d.journal();
		cout << endl;
		do {
			cout << "[1]<<go back to menu:";
			cin >> x;
			if (x == 1) {
				main();
			}
		} while (x != 1);
		break;
	case 3:
		system("cls");
		d.ledger();
		do {
			cout << "\n[1]<<go back to menu:";
			cin >> x;
			if (x == 1) {
				main();
			}
		} while (x != 1);
		break;
	case 4:
		system("cls");
		d.trialbalance();
		cout << endl;
		do {
			cout << "[1]<<go back to menu:";
			cin >> x;
			if (x == 1) {
				main();
			}
		} while (x != 1);
		break;
	case 5:
		system("cls");
		d.finperformance();
		cout << endl;
		do {
			cout << "[1]<<go back to menu:";
			cin >> x;
			if (x == 1) {
				main();
			}
		} while (x != 1);
		break;
	default:
		exit;
		break;
	}

	return 0;
}