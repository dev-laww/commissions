#include <stdio.h>

#define LABOR 0.35
#define TAX 0.085

float getLength();
float getWidth();
float getCost();
float getDiscount();
float getArea(float length, float width);
float getCarpetCost(float area, float cost);
float applyDiscount(float carpetCost, float discount);
float getLabor(float carpetCost);
float getTotal(float subTotal, float tax);
void printMeasurements(float length, float width, float area);
void printReceipt(float length, float width, float cost, float discount);


float getLength()
{
  float length;
  printf("Enter the length of the room in feet: ");
  scanf("%f", &length);
  return length;
}

float getWidth()
{
  float width;
  printf("Enter the width of the room in feet: ");
  scanf("%f", &width);
  return width;
}

float getCost()
{ 
  float cost;
  printf("Enter the cost of carpet per square foot: ");
  scanf("%f", &cost);
  return cost;
}

float getDiscount()
{
  float discount;
  printf("Enter the discount percentage: ");
  scanf("%f", &discount);
  return discount / 100;
}

float getArea(float length, float width)
{
  return length * width;
}

float getCarpetCost(float area, float cost)
{
  return area * cost;
}

float applyDiscount(float cost, float discount)
{
  return cost - (cost * discount);
}

float getLabor(float cost)
{
  return cost * LABOR;
}

float getInstalledTotal(float cost, float labor)
{
  return cost + labor;
}

float getTotal(float subTotal, float tax)
{
  return subTotal + tax;
}

void printMeasurements(float length, float width, float area)
{
  printf("\n                Measurements                    \n");
  printf("Length                                 %9.2lf feet\n", length);
  printf("Width                                  %9.2lf feet\n", width);
  printf("Area                                   %9.2lf square feet\n", area);
}

void printReceipt(float length, float width, float cost, float discount)
{
  float area, carpetCost, labor, installedTotal, subTotal, tax, total;
  area = getArea(length, width);
  carpetCost = getCarpetCost(area, cost);
  labor = getLabor(carpetCost);
  installedTotal = getInstalledTotal(carpetCost, labor);
  subTotal = applyDiscount(installedTotal, discount);
  tax = subTotal * TAX;
  total = getTotal(subTotal, tax);

  printf("\n                 Charges                        \n");
  printf("DESCRIPTION            COST/SQ.FT           CHARGE\n");
  printf("-----------            ----------           -----------\n");
  printf("Carpet                 $%7.2lf                $%7.2lf\n", cost, carpetCost);
  printf("Labor                  $%7.2lf                $%7.2lf\n", LABOR, labor);
  printf("                                     ------------------\n");
  printf("INSTALLED PRICE                                $%7.2lf\n", installedTotal);
  printf("Discount                %7.2lf%%               $%7.2lf\n", discount * 100,carpetCost * discount);
  printf("                                     ------------------\n");
  printf("Subtotal                                       $%7.2lf\n", subTotal);
  printf("Tax                                            $%7.2lf\n", tax);
  printf("TOTAL                                          $%7.2lf\n", total);
}

int main(void)
{
  float length, width, discount, cost;

  length = getLength();
  width = getWidth();
  discount = getDiscount();
  cost = getCost();

  printMeasurements(length, width, getArea(length, width));
  printReceipt(length, width, cost, discount);

  return 0;
}