public class MrMime extends Pokemon {
    private boolean ignored = false;
    private boolean hard = false;
    private boolean usedAnnoy = false;
    private int copycatStacks = 0;

    public MrMime(int level) {
        super(
                120 + (12 * level),
                level,
                80 + (10 * level)
        );
    }

    public MrMime() {
        this(1);
    }

    public void selfSlap() {
        if (this.energy < 10) {
            System.out.println("Not enough energy to body slam.");
            return;
        }

        this.takeDamage(Damage.PHYSICAL(10), this);
        this.ignored = true;
        this.energy -= 10;
    }

    public void mimeWall() {
        if (this.energy < 30) {
            System.out.println("Not enough energy to mime wall.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has hardened.");

        this.reduceStatusEffectDuration();
        this.hard = true;
        this.ignored = false;
        this.energy -= 30;
    }

    public void annoy(Pokemon target) {
        if (this.energy < 30) {
            System.out.println("Not enough energy to annoy.");
            return;
        }

        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has annoyed " + target.getClass().getSimpleName() + ".");
        this.reduceStatusEffectDuration();
        this.usedAnnoy = true;
        this.ignored = false;
    }

    @Override
    public void takeDamage(Damage damage, Pokemon attacker) {
        boolean missed = false;

        if (this.hard) {
            attacker.takeDamage(Damage.ELEMENTAL((int) (damage.getDamage() * 0.7) + copycatStacks), this);
            this.copycatStacks = 0;
            this.hard = false;
        }

        if (this.usedAnnoy) {
            for (int i = 0; i < 3; i++) {
                if (Math.random() < 0.65 + (0.01 * this.level)) {
                    missed = true;
                    break;
                }
            }
        }

        if (!this.ignored) {
            System.out.println("Mr. Mime ignored the attack.");
            return;
        }

        if (missed) {
            System.out.println("The attack missed.");
            return;
        }

        this.copycatStacks += (int) (damage.getDamage() * (0.2 + (0.03 * this.level)));
        this.ignored = true;
        super.takeDamage(damage, this);
    }
}
