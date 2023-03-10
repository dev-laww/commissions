public class BattleSetB {
    public static void main(String[] args) {
        Pikachu pikachu = new Pikachu();
        Wobbuffet wobbuffet = new Wobbuffet();

        System.out.println("Pikachu's HP: " + pikachu.getHP());
        System.out.println("Wobbuffet's HP: " + wobbuffet.getHP());

        while (pikachu.getHP() > 0 && wobbuffet.getHP() > 0) {
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.counter();
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.solidify();
            pikachu.tackle(wobbuffet);
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.counter();
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.solidify();
            pikachu.tackle(wobbuffet);
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.counter();
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.solidify();
            pikachu.tackle(wobbuffet);
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.counter();
            pikachu.thunderBolt(wobbuffet);
            wobbuffet.solidify();
            pikachu.tackle(wobbuffet);
        }

        System.out.println("Pikachu's HP: " + pikachu.getHP());
        System.out.println("Wobbuffet's HP: " + wobbuffet.getHP());
    }
}
