using System;

namespace TypeCasting
{
	class Program
	{
        static void test()
        {
            // a.
            // Ask the user for an odd number
            Console.WriteLine("Enter an odd number:");
            int oddNumber = Convert.ToInt32(Console.ReadLine());

            // Convert the odd number to a double using implicit casting
            double oddNumberAsDouble = oddNumber;

            // Divide the double by 2 and output the result
            Console.WriteLine(oddNumberAsDouble / 2);

            // b. 
            // Ask the user for a number
            Console.WriteLine("Enter a number:");
            double number = Convert.ToDouble(Console.ReadLine());

            // Convert the number to an integer using explicit casting
            int numberAsInt = (int) number;

            // Output the original value (as a double) and the converted value (as an integer) in the console
            Console.WriteLine(number + " is type-casted into " + numberAsInt);

            // c.
            // Define two double variables
            double a = 5.3;
            double b = 1.7;

            // Convert the double variables to int variables using explicit casting
            int aAsInt = (int)a;
            int bAsInt = (int)b;

            // Output the sum of the double variables and the sum of the int variables
            Console.WriteLine(a + b);
            Console.WriteLine(aAsInt + bAsInt);

        }
    }
}
