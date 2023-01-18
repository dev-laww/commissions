#include <stdio.h>

int main(){
  // Create a program in C that will accept 5 odd numbers from 1 - 99 and then display the sum, difference, product and average of the numbers. If the user enters an even number, the program should display an error message and ask the user to re-enter the number. The program should also display an error message if the number is not between 1 and 99. 

  int num1, num2, num3, num4, num5;
  int sum, difference, product;
  float average;

  // another way of getting input and validating it
  // printf("Enter 5 odd numbers between 1 and 99: ");
  // scanf("%d %d %d %d %d", &num1, &num2, &num3, &num4, &num5);
  // while (num1 % 2 == 0 || num1 < 1 || num1 > 99){
  //   printf("Error! Please enter an odd number between 1 and 99: ");
  //   scanf("%d", &num1);
  // }
  // while (num2 % 2 == 0 || num2 < 1 || num2 > 99){
  //   printf("Error! Please enter an odd number between 1 and 99: ");
  //   scanf("%d", &num2);
  // }
  // while (num3 % 2 == 0 || num3 < 1 || num3 > 99){
  //   printf("Error! Please enter an odd number between 1 and 99: ");
  //   scanf("%d", &num3);
  // }
  // while (num4 % 2 == 0 || num4 < 1 || num4 > 99){
  //   printf("Error! Please enter an odd number between 1 and 99: ");
  //   scanf("%d", &num4);
  // }
  // while (num5 % 2 == 0 || num5 < 1 || num5 > 99){
  //   printf("Error! Please enter an odd number between 1 and 99: ");
  //   scanf("%d", &num5);
  // }

  printf("Enter number 1: ");
  scanf("%d", &num1);
  // validate input
  // while the number is even or less than 1 or greater than 99
  // ask the user to re-enter the number
  while(num1 % 2 == 0 || num1 < 1 || num1 > 99){
    printf("Error: Number must be odd and between 1 and 99. Please re-enter number 1: ");
    scanf("%d", &num1);
  }
  printf("Enter number 2: ");
  scanf("%d", &num2);
  while(num2 % 2 == 0 || num2 < 1 || num2 > 99){
    printf("Error: Number must be odd and between 1 and 99. Please re-enter number 2: ");
    scanf("%d", &num2);
  }
  printf("Enter number 3: ");
  scanf("%d", &num3);
  while(num3 % 2 == 0 || num3 < 1 || num3 > 99){
    printf("Error: Number must be odd and between 1 and 99. Please re-enter number 3: ");
    scanf("%d", &num3);
  }
  printf("Enter number 4: ");
  scanf("%d", &num4);
  while(num4 % 2 == 0 || num4 < 1 || num4 > 99){
    printf("Error: Number must be odd and between 1 and 99. Please re-enter number 4: ");
    scanf("%d", &num4);
  }
  printf("Enter number 5: ");
  scanf("%d", &num5);
  while(num5 % 2 == 0 || num5 < 1 || num5 > 99){
    printf("Error: Number must be odd and between 1 and 99. Please re-enter number 5: ");
    scanf("%d", &num5);
  }
  // Check if the number is odd

  sum = num1 + num2 + num3 + num4 + num5;
  difference = num1 - num2 - num3 - num4 - num5;
  product = num1 * num2 * num3 * num4 * num5;
  average = sum / 5;

  // Printing the elements from the user
  printf("%d %d %d %d %d\n", num1, num2, num3, num4, num5);
  
  // Printing the sum, difference, product and average
  printf("The sum of the numbers is %d\n", sum);
  printf("The difference of the numbers is %d\n", difference);
  printf("The product of the numbers is %d\n", product);
  printf("The average of the numbers is %.2f\n", average);
  return 0;
}