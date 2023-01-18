using System;

namespace Methods
{
    class Program
    {   
        // a.
        // Define the Hello() method.
        static void Hello(string name = "world")
        {
            // Output the "Hello, <name>!" message.
            Console.WriteLine($"Hello, {name}!");
        }

        // b.
        // Define the Sing() method.
        static void Sing(string artist, string song, string verse)
        {
            // Output the message.
            Console.WriteLine($"\"{verse}\", {song} by {artist}");
        }

        // c.
        // Define the Add() method for ints.
        static int Add(int x, int y)
        {
            // Return the sum of the two numbers.
            return x + y;
        }

        // Define the Add() method for doubles.
        static double Add(double x, double y)
        {
            // Return the sum of the two numbers.
            return x + y;
        }

        static void test()
        {   
            // a.
            // Call the Hello() method with and without a name argument.
            Hello();
            Hello("Troye Sivan");

            // b.
            // Call the Sing() method with named arguments.
            Sing(song: "The Night is Still Young", artist: "Nicki Minaj", verse: "How dare we sit quietly, and watch the world pass us by");

            // c.
            // Call the Add() methods with int and double inputs.
            Console.WriteLine(Add(6, 23));
            Console.WriteLine(Add(6.23, 3.02));
        }
    }
}
