using System;

namespace InputAndDatatypes
{
    class Program
    {
        static void Main(string[] args)
        {   
            // a.
            // Request the user's name
            Console.Write("Enter your name: ");
            string name = Console.ReadLine();

            // Request the user's course
            Console.Write("Enter your course: ");
            string course = Console.ReadLine();

            // Request the user's year
            Console.Write("Enter your year: ");
            int year = Convert.ToInt32(Console.ReadLine());

            // Request the user's block
            Console.Write("Enter your block: ");
            int block = Convert.ToInt32(Console.ReadLine());

            // Request the user's desired grade
            Console.Write("Enter your desired grade: ");
            double desiredGrade = Convert.ToDouble(Console.ReadLine());

            // Print the information that the user has supplied
            Console.WriteLine("Name: " + name);
            Console.WriteLine("Course: " + course);
            Console.WriteLine("Year: " + year);
            Console.WriteLine("Block: " + block);
            Console.WriteLine("Desired grade: " + desiredGrade);

            // b.
            // Ask the user for their name
            Console.WriteLine("What is your name?");
            name = Console.ReadLine();

            // Ask the user for their course
            Console.WriteLine("What is your course?");
            course = Console.ReadLine();

            // Ask the user for the price of the merchandise they purchased
            Console.WriteLine("What is the price of the merchandise you purchased?");
            double price = Convert.ToDouble(Console.ReadLine());

            // Ask the user for the amount of money they have
            Console.WriteLine("How much money do you have?");
            double money = Convert.ToDouble(Console.ReadLine());

            // Calculate the change the user is owed
            double change = money - price;

            // Output a receipt-like summary of the inputs
            Console.WriteLine("Receipt");
            Console.WriteLine("-------------------");
            Console.WriteLine("Name: " + name);
            Console.WriteLine("Course: " + course);
            Console.WriteLine("Price of merchandise: $" + price);
            Console.WriteLine("Money provided: $" + money);
            Console.WriteLine("Change owed: $" + change);
            Console.WriteLine("-------------------");
            Console.WriteLine("Thank you for shopping with us!");

            // c.
            // Ask the user for their name
            Console.WriteLine("What is your name?");
            name = Console.ReadLine();

            // Ask the user for their grade in Math
            Console.WriteLine("What is your grade in Math?");
            double mathGrade = Convert.ToDouble(Console.ReadLine());

            // Ask the user for their grade in Science
            Console.WriteLine("What is your grade in Science?");
            double scienceGrade = Convert.ToDouble(Console.ReadLine());

            // Ask the user for their grade in English
            Console.WriteLine("What is your grade in English?");
            double englishGrade = Convert.ToDouble(Console.ReadLine());

            // Calculate the average of the user's grades
            double average = (mathGrade + scienceGrade + englishGrade) / 3;

            // Output the user's name and average grade
            Console.WriteLine(name + " your average is " + average);
        }
    }
}