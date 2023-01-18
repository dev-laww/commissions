using System;

namespace ClassObjectsAndFields
{   
    // a.
    // Define the Dog class.
    class Dog
    {
        // Define the fields.
        public int legs = 4;
        public string sound = "Woof!";
        public string breed = "Golden Retriever";
    }

    // b.
    // Define the Cat class.
    class Cat
    {
        // Define the name field.
        public string name;

        // Define the Meow() method.
        public void Meow()
        {
            // Output the "catName says meow!" message.
            Console.WriteLine(name + " says meow!");
        }
    }

    // c.
    class Rat
    {
        public string name;

        public void canCook()
        { 
            Console.WriteLine((name == "Remy") ? $"Yes, {name} can cook!" : $"No, {name} cannot cook!");
        }
    }

    // Define the Program class.
    class Program
    {
        static void Main(string[] args)
        {
            // a.
            // Create an object of the Dog class.
            Dog dog = new Dog();

            // Print the values of the dog's fields.
            Console.WriteLine($"A dog has {dog.legs} legs.");
            Console.WriteLine($"A dog says {dog.sound}.");
            Console.WriteLine($"A dog breed I love is a {dog.breed}.");

            // b.
            // Create an object of the Cat class.
            Cat cat = new Cat();

            // Set the name of the cat.
            cat.name = "Cheetos";

            // Call the Meow() method.
            cat.Meow();

            // c.
            // Initialize a Rat object and define the name of the rat as "Jerry"
            Rat rat = new Rat();
            rat.name = "Jerry";

            // Call the canCook method on the Rat object
            rat.canCook();

            // Change the name of the rat to "Remy"
            rat.name = "Remy";

            // Call the canCook method on the Rat object again
            rat.canCook();
        }
    }
}
