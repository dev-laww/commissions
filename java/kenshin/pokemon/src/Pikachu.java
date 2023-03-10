public class Pikachu extends Pokemon {
    private double bonusDodgeChance = 0.0;

    public Pikachu(int level) {
        super(
                100 + (10 * level),
                level,
                70 + (12 * level)
        );
    }

    public Pikachu() {
        this(1);
    }

    public void thunderBolt(Pokemon target) {
        if (this.energy < 35) {
            System.out.println("Not enough energy to thunder bolt.");
            return;
        }

        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has thunder bolted " + target.getClass().getSimpleName() + ".");
        this.reduceStatusEffectDuration();

        int damage = 30 + (2 * this.level);
        target.takeDamage(Damage.ELEMENTAL(damage), this);
        target.applyStatusEffect(StatusEffect.ELECTROCUTED);
        this.energy -= 35;
    }

    public void tackle(Pokemon target) {
        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has tackled " + target.getClass().getSimpleName() + ".");
        this.reduceStatusEffectDuration();

        target.takeDamage(Damage.PHYSICAL(20), this);
        this.energy += 20;
    }

    public void electroSprint() {
        if (this.energy < 25) {
            System.out.println("Not enough energy to electro sprint.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has electro sprinted.");
        this.reduceStatusEffectDuration();

        this.energy -= 25;
        this.bonusDodgeChance += 0.2;
    }

    @Override
    public void takeDamage(Damage damage, Pokemon attacker) {
        double dodgeChance = (damage.getType().equals("Elemental") ? 0.3 + (0.02 * this.level) : 0.4 + (0.02 * this.level)) + this.bonusDodgeChance;

        if (Math.random() < dodgeChance) {
            System.out.println(this.getClass().getSimpleName() + " has dodged the attack.");
            this.bonusDodgeChance = 0.0;
            attacker.takeDamage(Damage.PHYSICAL(30), this);
            return;
        }

        super.takeDamage(damage, attacker);

        if (!(this.health < 0)) return;

        if (Math.random() > 0.6) {
            this.health = 20;
            System.out.println(this.getClass().getSimpleName() + " is courageous.");
        }
    }
}
