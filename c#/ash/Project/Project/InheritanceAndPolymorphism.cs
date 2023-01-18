using System;

namespace Polymorpihsm
{
    class Polygon
    {
        public virtual void Side()
        {
            Console.WriteLine("A polygon can have any number of sides!");
        }
    }

    class Triangle : Polygon
    {
        public override void Side()
        {
            Console.WriteLine("A triangle has 3 sides!");
        }
    }

    class Rectangle : Polygon
    {
        public override void Side()
        {
            Console.WriteLine("A rectangle has 4 sides!");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // Define a Polygon object, a Triangle object, and a Rectangle object
            Polygon polygon = new Polygon();
            Triangle triangle = new Triangle();
            Rectangle rectangle = new Rectangle();

            // Print the output of the Side methods from the three classes
            polygon.Side();
            triangle.Side();
            rectangle.Side();
        }
    }
}
