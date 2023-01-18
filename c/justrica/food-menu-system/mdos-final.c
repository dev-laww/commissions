#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <conio.h>
#include <time.h>
#include <windows.h>
#include <stdbool.h>

char *menu[100];
int quantityArr[100];
int priceArr[100];
int counter = 0;

void deleteOrder(int choice)
{
    // delete the order from user input position
    for (int i = choice - 1; i < counter; i++)
    {
        menu[i] = menu[i + 1];
        quantityArr[i] = quantityArr[i + 1];
        priceArr[i] = priceArr[i + 1];
    }
    counter--;
}

void setcolor(int ForgC)
{
    WORD wColor;
    HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_SCREEN_BUFFER_INFO csbi;

    if (GetConsoleScreenBufferInfo(hStdOut, &csbi))
    {
        wColor = (csbi.wAttributes & 0xB0) + (ForgC & 0x0B);
        //	SetConsoleTextAttributes(hStdOut,wColor);
        SetConsoleTextAttribute(hStdOut, wColor);
    }
}

void printOrder()
{
    printf("\n\t==============================================================================\n");
    printf("\t                                Order List\n");
    printf("\t==============================================================================\n");
    printf("\t  %-5s %-30s %18s %18s", "No.", "Item", "Quantity", "Price");
    float total = 0;
    for (int i = 0; i < counter; i++)
    {
        printf("\n\t  %-5d %-30s %18d %18d", i + 1, menu[i], quantityArr[i], priceArr[i]);
        total += priceArr[i];
    }
    printf("\n\t==============================================================================\n");
    printf("\t  %-30s %18s       %18.2f", "Total", "", total);
    printf("\n\t==============================================================================\n");
    printf("\n\t  Press any key to continue...");
    getch();
    system("cls");
}

float getDiscount(float total)
{
    return total * 0.20;
}

float applyDiscount(float total)
{
    return total - getDiscount(total);
}

void printReceipt(boolean withDiscount, char *paymentMethod, float money, float total)
{
    printf("\n\t========================================================================\n");
    printf("\t                          Customer Receipt\n");
    printf("\t========================================================================\n");
    printf("\t  %-30s %18s %18s", "Item", "Quantity", "Price");
    for (int i = 0; i < counter; i++)
        printf("\n\t  %-30s %18d %18d", menu[i], quantityArr[i], priceArr[i]);

    printf("\n\t========================================================================\n");
    printf("\n\t  %-36s %32s\n\n", "Selected Payment Method:", paymentMethod);
    printf("\t  %-36s %32.2f", "Sub Total", total);
    if (withDiscount)
    {
        printf("\n\t  %-36s %32.2f", "Discount", getDiscount(total));
        printf("\n\t  %69s", "-------------");
        printf("\n\t  %-36s %32.2f", "Total", applyDiscount(total));
    }
    else
    {
        printf("\n\t  %-36s %32.2f\n", "Discount", 0);
        printf("\n\t  %69s", "-------------");
        printf("\n\t  %-36s %32.2f", "Total", total);
    }

    printf("\n\t  %-36s %32.2f\n", "Amount Tendered:", money);
    if (withDiscount)
        printf("\t  %-36s %32.2f\n", "Change:", money - applyDiscount(total));
    else
        printf("\t  %-36s %32.2f\n", "Change:", money - total);
    printf("\n\t========================================================================\n");
    printf("\t                   Thank you for dining with us!\n");
    printf("\t========================================================================\n");
    printf("\n\t  Press any key to continue...");
    getch();
}

void setOrders(char *order, int quantity, int price)
{
    menu[counter] = order;
    quantityArr[counter] = quantity;
    priceArr[counter] = price;
    counter++;
}

float getTotal()
{
    float total = 0;
    for (int i = 0; i < counter; i++)
        total += priceArr[i];
    return total;
}

void getPaymentMode(int total)
{
    int modeOfPayment, money, eWallet;
    char apply;
    char *paymentMethod;
    system("cls");
    printf("\t======================================================\n");
    printf("\t                 Select mode Of Payment\n");
    printf("\t======================================================\n\n");
    printf("\t   [1] CASH\n");
    printf("\t   [2] E-WALLET\n");
    printf("\t   [3] CREDIT CARD\n");
    printf("\n\t======================================================\n");
    printf("\t   Select Payment Method: ");
    while (TRUE)
    {
        scanf("%d", &modeOfPayment);
        switch (modeOfPayment)
        {
        case 1:
            printf("\n\t   Cash Payment");
            paymentMethod = "Cash";
            break;
        case 2:
            while (TRUE)
            {
                printf("\n\t   E-Wallet Payment");
                printf("\n\t   E-Wallets Available:");
                printf("\n\t   [1] GCash");
                printf("\n\t   [2] PayMaya");
                printf("\n\t   [3] Coins.ph");
                printf("\n\t   Select E-Wallet: ");
                scanf("%d", &eWallet);
                switch (eWallet)
                {
                case 1:
                    printf("\t   GCash");
                    printf("\n\t   Enter Account Name: ");
                    scanf("%s");
                    printf("\t   Enter Mobile Number: ");
                    scanf("%s");
                    paymentMethod = "GCash";
                    break;
                case 2:
                    printf("\n\t   PayMaya");
                    printf("\n\t   Enter Account Name: ");
                    scanf("%s");
                    printf("\t   Enter Mobile Number: ");
                    scanf("%s");
                    paymentMethod = "PayMaya";
                    break;
                case 3:
                    printf("\n\t   Coins.ph");
                    printf("\n\t   Enter Account Name: ");
                    scanf("%s");
                    printf("\t   Enter Mobile Number: ");
                    scanf("%s");
                    paymentMethod = "Coins.ph";
                    break;
                default:
                    printf("\t   Invalid Input");
                }

                if (eWallet == 1 || eWallet == 2 || eWallet == 3)
                    break;
            }
            break;

        case 3:
            printf("\n\t   Credit Card Payment");
            printf("\n\t   Enter Card Number: ");
            scanf("%d");
            printf("\t   Enter CVV: ");
            scanf("%d");
            paymentMethod = "Credit Card";
            break;
        }

        if (modeOfPayment == 1 || modeOfPayment == 2 || modeOfPayment == 3)
            break;
        else
            printf("\n\t   Invalid Input");
    }

    printf("\n\t   Enter Amount: ");
    scanf("%d", &money);

    while (money < total)
    {
        printf("\n\t   Insufficient Amount");
        printf("\n\t   Enter Amount: ");
        scanf("%d", &money);
    }

    while (TRUE)
    {
        system("cls");
        printf("\n\t   Apply Discount? (Y/N): ");
        scanf("%c");
        scanf("%c", &apply);
        switch (tolower(apply))
        {
        case 'y':
            printReceipt(TRUE, paymentMethod, money, total);
            break;
        case 'n':
            printReceipt(FALSE, paymentMethod, money, total);
            break;
        default:
            printf("\n\t   Invalid");
        }

        if (tolower(apply) == 'y' || tolower(apply) == 'n')
            break;
    }
}

void printGreeting()
{
    system("cls"); // FOR CLEARING SCREEN
    setcolor(6);
    printf(" -------------------------------------------------------------------------\n");
    printf("|                                                                         |\n");
    printf("|                                                                         |\n");
    printf("|  OOOOOO   OOOOOO OOOOOO OOOOOO OOOOOO OOOOOO O      O OOOOOOO  OOOOOO   |\n");
    printf("|  O        O    O O      O        O      O    O O    O O        O        |\n");
    printf("|  O  OOOOO OOOOOO OOOOO  OOOOO    O      O    O  O   O O  OOOOO OOOOOO   |\n");
    printf("|  O    O   O  O   O      O        O      O    O   O  O O    O        O   |\n");
    printf("|  OOOOOO   O   O  OOOOOO OOOOOO   O    OOOOOO O    O O OOOOOO   OOOOOO   |\n");
    printf("|                                                                         |\n");
    printf(" -------------------------------------------------------------------------\n");
    printf("\t\t*************************************************\n");
    printf("\t\t*                                               *\n");
    printf("\t\t*         -----------------------------         *\n");
    printf("\t\t*     WELCOME TO MENU DRIVE ORDERING SYSTEM     *\n");
    printf("\t\t*         -----------------------------         *\n");
    printf("\t\t*                                               *\n");
    printf("\t\t*                                               *\n");
    printf("\t\t*                                               *\n");
    printf("\t\t*************************************************\n\n\n");

    time_t t;
    time(&t);

    printf("\n");
    for (int i = 0; i < 80; i++)
        printf("-");

    printf("\nCurrent date and time : %s", ctime(&t));

    for (int i = 0; i < 80; i++)
        printf("-");
    printf("\n");

    for (int i = 0; i <= 5; i++)
    {
        printf("\r   Loading... %d%%", i * 20);
        Sleep(1000);
    }
}

void printMainMenu()
{

    time_t t;
    time(&t);
    system("cls");

    printf(" ======================================================\n");
    printf("                MENU DRIVE ORDERING SYSTEM\n ");
    printf("======================================================\n\n");

    printf("\t\tEnter [A] -> Main Dish\n");
    printf("\t\tEnter [B] -> Beverage\n");
    printf("\t\tEnter [C] -> Dessert\n");
    printf("\t\tEnter [D] -> Order List\n");
    printf("\t\tEnter [E] -> Customer Receipt\n");
    printf("\t\tEnter [F] -> Delete Order\n\n");
    printf("\t\tEnter [G] -> Exit\n\n");

    printf("======================================================\n");

    printf("\t\t %s", ctime(&t));
    printf("======================================================\n\n");
}

void processsMainDish(int choice, int quantity)
{
    switch (choice)
    {
    case 1:
        setOrders("Kare-kare", quantity, 210 * quantity);
        break;

    case 2:
        setOrders("Sinigang na Baboy", quantity, 195 * quantity);
        break;

    case 3:
        setOrders("Calos", quantity, 155 * quantity);
        break;

    case 4:
        setOrders("Dinuguan", quantity, 155 * quantity);
        break;

    case 5:
        setOrders("Bopis", quantity, 105 * quantity);
        break;

    case 6:
        setOrders("Pork/Pritchon Sisig", quantity, 125 * quantity);
        break;

    case 7:
        setOrders("Pork Adobo", quantity, 145 * quantity);
        break;

    case 8:
        setOrders("Humba", quantity, 195 * quantity);
        break;

    case 9:
        setOrders("Lechon Paksiw", quantity, 205 * quantity);
        break;

    case 10:
        setOrders("Crispy Pata", quantity, 250 * quantity);
        break;

    case 11:
        setOrders("Crispy Crablets", quantity, 139 * quantity);
        break;

    case 12:
        setOrders("Bistig Bangus", quantity, 220 * quantity);
        break;

    case 13:
        setOrders("Tortang", quantity, 99 * quantity);
        break;

    case 14:
        setOrders("Embutidong Bangus", quantity, 119 * quantity);
        break;

    case 15:
        setOrders("Krabby Patty", quantity, 129 * quantity);
        break;

    case 16:
        setOrders("Chicken Asado", quantity, 145 * quantity);
        break;

    case 17:
        setOrders("Chicken Sisig", quantity, 125 * quantity);
        break;

    case 18:
        setOrders("Gatang Laing", quantity, 89 * quantity);
        break;

    case 19:
        setOrders("Pakbet Kapampangan", quantity, 99 * quantity);
        break;

    case 20:
        setOrders("Gatang Sigarilyas", quantity, 95 * quantity);
        break;

    case 21:
        setOrders("Gatang Sitaw at Kalabasa", quantity, 85 * quantity);
        break;

    case 22:
        setOrders("Tofu Sisig", quantity, 99 * quantity);
        break;

    case 23:
        setOrders("Pancit Puti", quantity, 120 * quantity);
        break;

    case 24:
        setOrders("Pinoy Spaghetti", quantity, 110 * quantity);
        break;
    }
}

void printMainDish()
{
    system("cls");
    setcolor(6);
    printf("\t=======================================================\n");
    printf("\t                     Main Dish Menu   \n");
    printf("\t=======================================================\n\n");

    printf("\t\t\t\t---BEEF---\n");
    printf("\t   [1] KARE-KARE                             - Php.210 \n");
    printf("\t   [2] CALDERETA                             - Php.195 \n");
    printf("\t   [3] CALOS                                 - Php.155 \n");

    printf("\n\t\t\t\t---PORK---\n");
    printf("\t   [4] DINUGUAN			       	     - Php.155 \n");
    printf("\t   [5] BOPIS                                 - Php.105 \n");
    printf("\t   [6] PORK/PRITCHON SISIG                   - Php.125 \n");
    printf("\t   [7] PORK BINAGOONGAN                      - Php.145 \n");
    printf("\t   [8] HUMBA                                 - Php.195 \n");
    printf("\t   [9] LECHON PAKSIW                         - Php.205 \n");
    printf("\t   [10] CRISPY PATA                          - Php.250 \n");

    printf("\n\t\t\t       ---SEAFOOD---\n");
    printf("\t   [11] CRISPY CRABLETS                      - Php.139 \n");
    printf("\t   [12] BISTIG BANGUS                        - Php.220 \n");
    printf("\t   [13] TORTANG BANGUS                       - Php.99 \n");
    printf("\t   [14] EMBOTIDONG BANGUS                    - Php.119 \n");
    printf("\t   [15] KRABBY PATTY                         - Php.129 \n");

    printf("\n\t\t\t       ---CHICKEN---\n");
    printf("\t   [16] CHICKEN ASADO                        - Php.145 \n");
    printf("\t   [17] CHICKEN SISIG                        - Php.125 \n");

    printf("\n\t\t\t      ---VEGETABLE---\n");
    printf("\t   [18] GATANG LAING                         - Php.89 \n");
    printf("\t   [19] PAKBET KAPAMPANGAN                   - Php.99 \n");
    printf("\t   [20] GATANG SIGARILYAS                    - Php.95 \n");
    printf("\t   [21] GATANG SITAW AT KALABASA             - Php.85 \n");
    printf("\t   [22] TOFU SISIG                           - Php.99 \n");

    printf("\n\t\t\t       ---NOODLES---\n");
    printf("\t   [23] PANCIT PUTI                          - Php.120 \n");
    printf("\t   [24] PINOY SPAGHETTI                      - Php.110 \n");

    printf("\t   [0] Back To Main-Menu\n");
    printf("\t=======================================================\n");
}

void processBeverage(int choice, int quantity)
{
    switch (choice)
    {
    case 1:
        setOrders("Coke", quantity, 35 * quantity);
        break;

    case 2:
        setOrders("Sprite", quantity, 35 * quantity);
        break;

    case 3:
        setOrders("Sarsi", quantity, 35 * quantity);
        break;

    case 4:
        setOrders("Pepsi Light", quantity, 35 * quantity);
        break;

    case 5:
        setOrders("Pepsi Max", quantity, 40 * quantity);
        break;

    case 6:
        setOrders("Minty Lemonade", quantity, 55 * quantity);
        break;

    case 7:
        setOrders("Del Monte Pineapple Juice", quantity, 35 * quantity);
        break;

    case 8:
        setOrders("Rootbeer", quantity, 35 * quantity);
        break;

    case 9:
        setOrders("Nestie Juice - Iced Tea", quantity, 35 * quantity);
        break;

    case 10:
        setOrders("Orange Sparkling", quantity, 50 * quantity);
        break;

    case 11:
        setOrders("Santol yakult", quantity, 45 * quantity);
        break;
    }
}

void printBeverage()
{
    system("cls");
    setcolor(6);
    printf("\t=======================================================\n");
    printf("\t                     Beverage Menu   \n");
    printf("\t=======================================================\n\n");

    printf("\t   [1] COCA-COLA                           - Php.35 \n");
    printf("\t   [2] SPRITE                              - Php.35 \n");
    printf("\t   [3] SARSI                               - Php.35 \n");
    printf("\t   [4] PEPSI LIGHT                         - Php.35 \n");
    printf("\t   [5] PEPSI MAX                           - Php.40 \n");
    printf("\t   [6] MINTY LEMONADE                      - Php.55 \n");
    printf("\t   [7] DEL MONTE PINEAPPLE JUICE           - Php.35 \n");
    printf("\t   [8] ROOT BEER                           - Php.35 \n");
    printf("\t   [9] NESTIE JUICE - ICED TEA             - Php.35 \n");
    printf("\t   [10] ORANGE SPARKLING                   - Php.50 \n");
    printf("\t   [11] SANTOL YAKULT                      - Php.45 \n");

    printf("\t   [0] Back To Main-Menu\n");
    printf("\t=======================================================\n");
}

void processDessert(int choice, int quantity)
{
    switch (choice)
    {
    case 1:
        setOrders("Halo-Halo", quantity, 75 * quantity);
        break;
    case 2:
        setOrders("Mais Con Yelo", quantity, 55 * quantity);
        break;
    case 3:
        setOrders("Taho", quantity, 45 * quantity);
        break;
    case 4:
        setOrders("Ube Jam", quantity, 50 * quantity);
        break;
    case 5:
        setOrders("Buko Pandan", quantity, 45 * quantity);
        break;
    case 6:
        setOrders("Fruit Salad", quantity, 99 * quantity);
        break;
    case 7:
        setOrders("Banana Split", quantity, 85 * quantity);
        break;
    case 8:
        setOrders("Ice Cream Sundae", quantity, 55 * quantity);
        break;
    case 9:
        setOrders("Leche Flan", quantity, 59 * quantity);
        break;
    case 10:
        setOrders("Minatamis na Saging", quantity, 50 * quantity);
        break;
    case 11:
        setOrders("Brownies 5pcs", quantity, 125 * quantity);
        break;
    case 12:
        setOrders("Red Velvet Cake / Slice", quantity, 100 * quantity);
        break;
    case 13:
        setOrders("Churros 6pcs", quantity, 85 * quantity);
        break;
    case 14:
        setOrders("Cassava Cake", quantity, 60 * quantity);
        break;
    case 15:
        setOrders("Bibingka", quantity, 50 * quantity);
        break;
    case 16:
        setOrders("Biko (Sticky Rice Cake)", quantity, 60 * quantity);
        break;
    }
}

void printDessert()
{
    system("cls");
    setcolor(6);
    printf("\t=======================================================\n");
    printf("\t                     Dessert Menu   \n");
    printf("\t=======================================================\n\n");
    printf("\t   [1] HALO-HALO                        - Php.75 \n");
    printf("\t   [2] MAIS CON YELO                    - Php.55 \n");
    printf("\t   [3] TAHO                             - Php.45 \n");
    printf("\t   [4] UBE JAM                          - Php.50 \n");
    printf("\t   [5] BUKO PANDAN                      - Php.45 \n");
    printf("\t   [6] FRUIT SALAD                      - Php.99 \n");
    printf("\t   [7] BANANA SPLIT                     - Php.85 \n");
    printf("\t   [8] ICE CREAM SUNDAE                 - Php.55 \n");
    printf("\t   [9] LECHE FLAN                       - Php.59 \n");
    printf("\t   [10] MINATAMIS NA SAGING             - Php.50 \n");
    printf("\t   [11] BROWNIES (5 PIECES)             - Php.125 \n");
    printf("\t   [12] RED VELVET CAKE/SLICE           - Php.100 \n");
    printf("\t   [13] CHURROS (6 PIECES)              - Php.85 \n");
    printf("\t   [14] CASSAVA CAKE                    - Php.60 \n");
    printf("\t   [15] BIBINGKA                        - Php.50 \n");
    printf("\t   [16] BIKO (STICKY RICE CAKE)         - Php.60 \n");

    printf("\t   [0] Back To Main-Menu\n");
    printf("\t=======================================================\n");
}

int main()
{
    int total = 0, run = 0;
    float money = 0;
    int menuChoice, modeOfPayment, eWallet, quantity;
    char choice;
    printGreeting();

    while (TRUE)
    {
        printMainMenu();
        printf("\t      Please Enter Your Choice: ");
        scanf("%c", &choice);
        printf("\n\n");

        if (run > 1)
            system("cls");

        switch (tolower(choice))
        {
        case 'a':
            while (TRUE)
            {

                printMainDish();
                printf("\n\t      Please Enter Your Choice: ");
                scanf("%d", &menuChoice);
                if (menuChoice == 0)
                    break;

                while (menuChoice < 0 || menuChoice > 24)
                {
                    system("cls");
                    printMainDish();
                    printf("\n\t      Invalid Choice.\n");
                    printf("\t      Please Enter Your Choice: ");
                    scanf("%d", &menuChoice);
                }

                printf("\t      Please Enter Quantity: ");
                scanf("%d", &quantity);

                while (quantity <= 0)
                {
                    system("cls");
                    printf("\t Quantity must be greater than 0.\n");
                    printf("\t Please Enter Quantity: ");
                    scanf("%d", &quantity);
                }
                processsMainDish(menuChoice, quantity);
            }
            break;
        case 'b':
            while (TRUE)
            {

                printBeverage();

                printf("\n\t      Please Enter Your Choice: ");
                scanf("%d", &menuChoice);
                if (menuChoice == 0)
                    break;

                while (menuChoice < 0 || menuChoice > 11)
                {
                    system("cls");
                    printf("\n\t      Invalid Choice.\n");
                    printf("\t      Please Enter Your Choice: ");
                    scanf("%d", &menuChoice);
                }

                printf("\t      Please Enter Quantity: ");
                scanf("%d", &quantity);

                while (quantity <= 0)
                {
                    system("cls");
                    printBeverage();
                    printf("\t Quantity must be greater than 0.\n");
                    printf("\t Please Enter Quantity: ");
                    scanf("%d", &quantity);
                }

                processBeverage(menuChoice, quantity);
            }

            break;
        case 'c':
            while (TRUE)
            {

                printDessert();

                printf("\n\t      Please Enter Your Choice: ");
                scanf("%d", &menuChoice);
                if (menuChoice == 0)
                    break;

                while (menuChoice < 0 || menuChoice > 16)
                {
                    system("cls");
                    printDessert();
                    printf("\n\t      Invalid Choice.\n");
                    printf("\t      Please Enter Your Choice: ");
                    scanf("%d", &menuChoice);
                }

                printf("\t      Please Enter Quantity: ");
                scanf("%d", &quantity);

                while (quantity <= 0)
                {
                    system("cls");
                    printf("\t Quantity must be greater than 0.\n");
                    printf("\t Please Enter Quantity: ");
                    scanf("%d", &quantity);
                }
                processDessert(menuChoice, quantity);
            }
            break;
        case 'd':
            printOrder();
            break;
        case 'e':
            printOrder();
            getPaymentMode(getTotal());
            break;
        case 'f':
            printf("\t      Delete Order\n");
            printOrder();
            printf("\t      Please Enter Your Choice: ");
            scanf("%d", &menuChoice);
            while (menuChoice < 0 || menuChoice > counter)
            {
                system("cls");
                printOrder();
                printf("\n\t      Invalid Choice.\n");
                printf("\t      Please Enter Your Choice: ");
                scanf("%d", &menuChoice);
            }
            deleteOrder(menuChoice);
            printOrder();
            break;
        default:
            printf("\n\n                  Invalid Choice!");
        }

        run++;

        if (tolower(choice) == 'e' || tolower(choice) == 'g')
        {
            system("cls");
            printf("\n\n\t===============MDOS==================          \n");
            printf("   \n         Thanks for choosing our restaurant :))\n\n");
            printf("             We hope to see you again!  \n");
            printf("\t====================================  \n");
            getch();
            exit(0);
            break;
        }
    }
    return 0;
}
