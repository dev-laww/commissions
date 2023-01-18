using System;

namespace Loops
{
    class Program
    {
        static void test()
        {
            // a.
            int i = 1;
            while (i <= 20)
            {
                if (i % 2 == 0) Console.WriteLine(i);
                i++;
            }

            // b.
            for (int j = 1; j <= 10; j++)
            {
                if (j == 8) continue;
                Console.WriteLine(j);
            }
            
            // c.
            Console.WriteLine("Enter an integer: ");
            int num = int.Parse(Console.ReadLine());
            int k = 0;
            do
            {
                Console.WriteLine("Hello!");
                k++;
            } while (k < num);
        }
    }
}
