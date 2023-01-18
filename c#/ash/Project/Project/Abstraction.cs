using System;

namespace Abstraction
{
    abstract class MysteryBox
    {
        public abstract void Rarity();

        public void OpenBox()
        {
            Console.WriteLine("The mystery box has been opened!");
        }
    }

    class RareBox : MysteryBox
    {
        public override void Rarity()
        {
            Console.WriteLine("Rarity: Rare");
        }
    }

    class LegendaryBox : MysteryBox
    {
        public override void Rarity()
        {
            Console.WriteLine("Rarity: Legendary");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // Define a RareBox object and a LegendaryBox object
            RareBox rareBox = new RareBox();
            LegendaryBox legendaryBox = new LegendaryBox();

            // Print the Rarity and OpenBox methods from the RareBox object
            rareBox.Rarity();
            rareBox.OpenBox();

            // Print the Rarity and OpenBox methods from the LegendaryBox object
            legendaryBox.Rarity();
            legendaryBox.OpenBox();
        }
    }
}
