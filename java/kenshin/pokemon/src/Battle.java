public class Battle {
    public static void main(String[] args) {
        MrMime mrMime = new MrMime();
        Snorlax snorlax = new Snorlax();
        Haunter haunter = new Haunter();
        Pokemon[] pokemon = {mrMime, snorlax, haunter};

        System.out.println("MrMime's HP: " + mrMime.getHP());
        System.out.println("Snorlax's HP: " + snorlax.getHP());
        System.out.println("Haunter's HP: " + haunter.getHP());

        while (mrMime.getHP() > 0 && snorlax.getHP() > 0 && haunter.getHP() > 0) {
            haunter.instillFear(snorlax);
            mrMime.annoy(snorlax);
            snorlax.dozeOff();
            mrMime.selfSlap();
            snorlax.bodySlam(mrMime);
            mrMime.mimeWall();
            haunter.instillFear(mrMime);
            snorlax.bodySlam(mrMime);
            mrMime.annoy(snorlax);
            snorlax.bodySlam(mrMime);
            haunter.consumFear(pokemon);
        }

        System.out.println("MrMime's HP: " + mrMime.getHP());
        System.out.println("Snorlax's HP: " + snorlax.getHP());
        System.out.println("Haunter's HP: " + haunter.getHP());
    }
}
