public class Wobbuffet extends Pokemon {
    private int nullifiedDamage = 0;
    private boolean usedCounter = false;

    public Wobbuffet(int level) {
        super (
                150 + (8 * level),
                level,
                40 + (3 * level)
        );
    }

    public Wobbuffet() {
        this(1);
    }

    public void slap(Pokemon target) {
        System.out.println(this.getClass().getSimpleName() + " has slapped " + target.getClass().getSimpleName() + ".");
        target.takeDamage(Damage.PHYSICAL(15), this);
        this.energy += 15;
    }

    public void solidify() {
        if (this.energy < 10) {
            System.out.println("Not enough energy to solidify.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has solidified.");
        this.nullifiedDamage += 2;
    }

    public void counter() {
        if (this.energy < 20) {
            System.out.println("Not enough energy to counter.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has countered.");
        this.usedCounter = true;
    }

    @Override
    public void takeDamage(Damage damage, Pokemon source) {
        if (this.usedCounter) {
            System.out.println(this.getClass().getSimpleName() + " has countered " + source.getClass().getSimpleName() + "'s attack.");
            source.takeDamage(Damage.PHYSICAL(10 + (5 * this.level)), this);
            this.usedCounter = false;
            this.energy -= 20;
            return;
        }

        if (this.nullifiedDamage > 0) {
            System.out.println(this.getClass().getSimpleName() + " has nullified " + damage.getDamage() + " damage.");
            this.nullifiedDamage --;
            return;
        }

        if (damage.getType().equals("Physical")) {
            System.out.println("Damage reduced");
            damage.setDamage(damage.getDamage() / 2);
        }

        super.takeDamage(damage, source);
    }
}
