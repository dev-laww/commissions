using System;

namespace Exceptions
{
    class Program
    {
        static void Main(string[] args)
        {   
            // a.
            double num1 = 0.0;
            double num2 = 0.0;

            // Request two double-type inputs from the user

            try
            {
                Console.WriteLine("Enter the first number:");
                num1 = Convert.ToDouble(Console.ReadLine());
                Console.WriteLine("Enter the second number:");
                num2 = Convert.ToDouble(Console.ReadLine());
                // Add the two numbers together and print the result
                Console.WriteLine($"The sum of {num1} and {num2} is {num1 + num2}.");
            }
            catch (FormatException)
            {
                Console.WriteLine("The input is invalid. Please enter a valid number.");
            }

            // b.
            bool repeat = true;
            
            while (repeat)
            {
                try
                {
                    Console.WriteLine("Enter the first number:");
                    num1 = Convert.ToDouble(Console.ReadLine());
                    Console.WriteLine("Enter the second number:");
                    num2 = Convert.ToDouble(Console.ReadLine());
                    // Add the two numbers together and print the result
                    Console.WriteLine($"The sum of {num1} and {num2} is {num1 + num2}.");
                }
                catch (FormatException)
                {
                    Console.WriteLine("The input is invalid. Please enter a valid number.");
                }
                finally
                {
                    // ask the user if he wants to continue
                    Console.WriteLine("Do you want to repeat the process? (Y/N)");
                    repeat = char.Parse(Console.ReadLine().ToLower()) == 'y';
                }
            }

            // c.
            Console.WriteLine("Enter your age:");
            int age = Convert.ToInt32(Console.ReadLine());

            if (age >= 13)
            {
                Console.WriteLine("You are allowed to watch the movie.");
            }
            else
            {
                throw new ArithmeticException("You are not allowed to watch the movie.");
            }
        }
    }
}