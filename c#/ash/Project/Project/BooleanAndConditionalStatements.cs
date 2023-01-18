using System;

namespace BooleanAndConditionalStatements
{
    class Program
    {
        static void Main(string[] args)
        {
            // a.
            Console.WriteLine("Enter your age: ");

            int age = int.Parse(Console.ReadLine());

            if (age >= 18)
            {
                Console.WriteLine("Hello, customer!");
            }
            else
            {
                Console.WriteLine("You are not allowed here!");
            }

            // b.
            // Ask the user to input a number from 0 to 23
            Console.Write("Enter a number from 0 to 23: ");
            int num = int.Parse(Console.ReadLine());

            // Check the number and output the appropriate greeting
            if (num >= 0 && num <= 5)
            {
                Console.WriteLine("Good night!");
            }
            else if (num >= 6 && num <= 10)
            {
                Console.WriteLine("Good morning!");
            }
            else if (num >= 11 && num <= 13)
            {
                Console.WriteLine("Good noon!");
            }
            else if (num >= 14 && num <= 17)
            {
                Console.WriteLine("Good afternoon!");
            }
            else if (num >= 18 && num <= 23)
            {
                Console.WriteLine("Good evening!");
            }
            else
            {
                Console.WriteLine("Invalid input. Please enter a number from 0 to 23.");
            }

            // c.
            // Ask the user for the first number
            Console.Write("Enter the first number: ");
            double num1 = double.Parse(Console.ReadLine());

            // Ask the user for the second number
            Console.Write("Enter the second number: ");
            double num2 = double.Parse(Console.ReadLine());

            // Ask the user for the operator
            Console.Write("Enter an operator (+, -, *, /): ");
            char op = char.Parse(Console.ReadLine());

            // Perform the operation and print the result
            switch (op)
            {
                case '+':
                    Console.WriteLine($"{num1} + {num2} = {num1 + num2}");
                    break;
                case '-':
                    Console.WriteLine($"{num1} - {num2} = {num1 - num2}");
                    break;
                case '*':
                    Console.WriteLine($"{num1} * {num2} = {num1 * num2}");
                    break;
                case '/':
                    Console.WriteLine($"{num1} / {num2} = {num1 / num2}");
                    break;
                default:
                    Console.WriteLine("Invalid operator!");
                    break;
            }
        }
    }
}
