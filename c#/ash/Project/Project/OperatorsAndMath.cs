using System;

namespace OperatorsAndMath
{
    class Program
    {
        static void test()
        {   
            // a.
            Console.WriteLine("Enter a number: ");
            double num1 = Convert.ToDouble(Console.ReadLine());

            Console.WriteLine("Enter another number: ");
            double num2 = Convert.ToDouble(Console.ReadLine());

            Console.WriteLine("Choose an operation: \n1) Add \n2) Subtract \n3) Multiply \n4) Divide");
            int choice = Convert.ToInt32(Console.ReadLine());

            double result = 0;
            switch (choice)
            {
                case 1:
                    result = num1 + num2;
                    break;
                case 2:
                    result = num1 - num2;
                    break;
                case 3:
                    result = num1 * num2;
                    break;
                case 4:
                    result = num1 / num2;
                    break;
            }

            Console.WriteLine("The result is " + result);

            // b.
            Console.WriteLine("Enter an integer: ");
            int num = Convert.ToInt32(Console.ReadLine());

            result = num % 2;

            Console.WriteLine("The result is " + result);

            // c.
            Console.WriteLine("Enter a negative number: ");
            double number = Convert.ToDouble(Console.ReadLine());

            result = Math.Sqrt(Math.Abs(number));

            Console.WriteLine("The result is " + result);

        }
    }
}
