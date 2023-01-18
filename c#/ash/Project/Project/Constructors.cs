using System;

namespace Constructors
{
    class Human
    {
        public string name;
        public double heightCm;
        public double weightKg;

        public Human(string humanName, double humanHeight, double humanWeight)
        {
            name = humanName;
            heightCm = humanHeight;
            weightKg = humanWeight;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // Define two Human objects and initialize their name, height, and weight
            Human Mark = new Human("Mark Fischbach", 177.0, 79.2);
            Human Ethan = new Human("Ethan Nestor", 172.72, 65.2);

            // Print the name, height, and weight of the two Human objects
            Console.WriteLine(Mark.name + ":");
            Console.WriteLine("Height: " + Mark.heightCm + "cm");
            Console.WriteLine("Weight: " + Mark.weightKg + "kg");
            Console.WriteLine(Ethan.name + ":");
            Console.WriteLine("Height: " + Ethan.heightCm + "cm");
            Console.WriteLine("Weight: " + Ethan.weightKg + "kg");
        }
    }
}
