using System;

namespace Enums
{
    enum Months
    {   
        // a.
        // January
        // b.
        January = 1,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December
    }

    enum Weekdays
    {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    class Program
    {
        static void Main(string[] args)
        {   
            // a, b.
            // Define an int called monthIndex that gets the index of the month in the enum
            int monthIndex = (int)Months.June;

            // Print the monthIndex
            Console.WriteLine(monthIndex);

            // c.
            // Define an int called dayIndex that gets the index of the weekday in the enum
            int dayIndex = (int)Weekdays.Sunday;

            // Use an if-else statement to print "Weekday" or "Weekend" depending on the value of dayIndex
            if (dayIndex < 5)
            {
                Console.WriteLine("Weekday");
            }
            else
            {
                Console.WriteLine("Weekend");
            }
        }
    }
}
