using System;
using System.IO;

namespace Files
{
    class Program
    {
        static void Main(string[] args)
        {
            // Create a file called "hello.txt" and write "Hello, world!" to it
            File.WriteAllText("hello.txt", "Hello, world!");

            // Read the contents of the "hello.txt" file and print it to the console
            Console.WriteLine(File.ReadAllText("hello.txt"));
        }
    }
}