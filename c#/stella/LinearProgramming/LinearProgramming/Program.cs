using System;
using System.IO;

namespace LinearProgramming
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            // variables to store the values of x, y and z
            double x, y, z;
            // variable to store the maximum profit
            double maxProfit = 0;
            // variable to keep track of the number of iterations where the constraints is not satisfied
            double notSatisfied = 0;
            // variable to keep track of the number of iterations where the constraints is satisfied
            double satisfied = 0;

            // read the input from a file
            double[]? inputValues = ReadFile();

            // check if the inputValues is not null
            if (inputValues == null)
            {
                Console.WriteLine("Error file format, try checking the input file\n");
                return;
            }

            double xMax = inputValues[0];
            double yMax = inputValues[1];
            double zMax = inputValues[2];
            string line;

            Console.WriteLine("Values: " + string.Join(", ", inputValues) + "\n");

            using (StreamWriter writer = new StreamWriter("output.txt"))
            {
                // nested loops to iterate through all possible values of x, y and z
                for (int i = 0; i <= xMax; i++)
                {
                    for (int j = 0; j <= yMax; j++)
                    {
                        for (double k = 0; k <= zMax; k++)
                        {
                            // calculate the value of the products
                            double product1 = 3 * i + 4 * j + 5 * k;
                            double product2 = 5 * i + 3 * j + 5 * k;
                            double product3 = 4 * i + 3 * j + 5 * k;

                            // check if the constraints are satisfied
                            if (product1 <= xMax && product2 <= yMax && product3 <= zMax)
                            {
                                // calculate the current profit
                                double currentProfit = 80 * i + 70 * j + 100 * k;
                                // check if the current profit is greater than the maxProfit
                                if (currentProfit > maxProfit)
                                {
                                    maxProfit = currentProfit;
                                    x = i;
                                    y = j;
                                    z = k;
                                    line = string.Format("A profit of {0} is achieved when x, y and z are {1}, {2}, {3} respectively", maxProfit, x, y, z);
                                    Console.WriteLine(line);
                                    writer.WriteLine(line);
                                }
                                // increment the counter for the number of iterations where the constraint is satisfied
                                satisfied++;

                                continue;
                            }

                            // increment the counter for the number of iterations where the constraint is not satisfied
                            notSatisfied++;
                        }
                    }
                }

                // write the result to a file
                line = string.Format("Number of iterations where the constraint is satisfied: {0}", satisfied);
                Console.WriteLine(line);
                writer.WriteLine(line);
                line = string.Format("Number of iterations where the constraint is not satisfied: {0}", notSatisfied);
                writer.WriteLine(line);
                Console.WriteLine(line);
            }
            Console.WriteLine("\nProgram finished, all the outputs are on the output.txt");
        }

        private static double[]? ReadFile()
        {
            Console.WriteLine("Reading the input file. . .");
            // read the file
            string[] input = File.ReadAllLines("input.txt");

            // check if the file contains 3 lines
            if (input.Length != 3) return null;

            Console.WriteLine(string.Join(", ", input) + "\n");

            for (int i = 0; i < input.Length; i++)
            {   
                // remove all non-digit character
                input[i] = new string(input[i].Where(c => char.IsDigit(c)).ToArray());
            }
            try
            {   
                // will throw an error if the lines are 
                return Array.ConvertAll(input, double.Parse);
            }
            catch (Exception e)
            {
                return null;
            }
        }
    }
}