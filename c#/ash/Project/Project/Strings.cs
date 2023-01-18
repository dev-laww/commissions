using System;

namespace Strings
{
    class Program
    {
        static void test()
        {   
            // a.
            Console.WriteLine("Enter first name: ");
            string firstName = Console.ReadLine();

            Console.WriteLine("Enter last name: ");
            string lastName = Console.ReadLine();

            Console.WriteLine(firstName + " " + lastName + " can't come to the phone right now. Why? Oh, 'coz she's dead!");

            // b.
            Console.WriteLine("\"Hello World!\"");

            // c.
            string greeting = "Hello, world";

            // Use the IndexOf() method to find the starting index of the word "world"
            int startIndex = greeting.IndexOf("world");

            // Use the Substring() method to extract the word "world" from the greeting
            // This method takes the starting index and the length of the substring we want to extract
            string world = greeting.Substring(startIndex, 5);

            // Add an exclamation mark to the end of the word "world"
            world += "!";

            // Print the resulting string to the console
            Console.WriteLine(world);
        }
    }
}