using System;

namespace Interfaces
{
    interface Walk
    {
        void walk();
    }

    interface Run
    {
        void run();
    }

    class MainCharacter : Walk, Run
    {
        public string name;

        public MainCharacter(string characterName)
        {
            name = characterName;
        }

        public void walk()
        {
            Console.WriteLine(name + " walked.");
        }

        public void run()
        {
            Console.WriteLine(name + " ran.");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // Define a MainCharacter object
            MainCharacter aether = new MainCharacter("Aether");

            // Print the output of the walk and run methods
            aether.walk();
            aether.run();
        }
    }
}
