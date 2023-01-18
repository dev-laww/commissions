using System;
using System.Linq;

namespace Arrays
{
    class Program
    {
        static void Main(string[] args)
        {
            // a.
            // Store the Teletubbies names in an array
            string[] teletubbies = { "Tinky-Winky", "Dipsy", "Laa-laa", "Po" };

            // Print the Teletubbies names using a foreach loop
            foreach (string name in teletubbies)
            {
                Console.WriteLine(name);
            }

            // b.
            // Create an array to store the strings.
            string[] strings = new string[5];

            // Prompt the user for input and store the strings in the array.
            Console.WriteLine("Enter 5 strings: ");
            for (int i = 0; i < strings.Length; i++)
            {
                strings[i] = Console.ReadLine();
            }

            // Sort the strings in the array alphabetically.
            Array.Sort(strings);

            // Print the strings in alphabetical order.
            Console.WriteLine("The strings in alphabetical order are: ");
            foreach (string s in strings)
            {
                Console.WriteLine(s);
            }

            // c.
            // Create an array to store the integers.
            int[] numbers = new int[5];

            // Prompt the user for input and store the integers in the array.
            Console.WriteLine("Enter 5 integers: ");
            for (int i = 0; i < numbers.Length; i++)
            {
                numbers[i] = int.Parse(Console.ReadLine());
            }

            // Use LINQ to find the smallest, largest, and sum of the values in the array.
            int smallest = numbers.Min();
            int largest = numbers.Max();
            int sum = numbers.Sum();

            // Print the results.
            Console.WriteLine("The smallest number is: " + smallest);
            Console.WriteLine("The largest number is: " + largest);
            Console.WriteLine("The sum of all numbers is: " + sum);
        }
    }
}
