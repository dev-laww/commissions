using System;

namespace AccessModifiersAndProperties
{   
    // a.
    class Ball
    {
        private string color = "red";

        public void whatColor()
        {
            Console.WriteLine("The color of the ball is " + color);
        }
    }

    // c.
    class SecretClass
    {
        private string secret = "Shhh! Do not tell anyone!";

        public string SecretNoMore
        {
            get { return secret; }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // a.
            // Define a Ball object
            Ball ball = new Ball();

            // Call the whatColor method on the Ball object
            ball.whatColor();

            // b.
            // Console.WriteLine("The color of the ball is " + ball.color);

            // c.
            // Define a SecretClass object
            SecretClass secretObj = new SecretClass();

            // Print the SecretNoMore string
            Console.WriteLine(secretObj.SecretNoMore);
        }
    }
}
