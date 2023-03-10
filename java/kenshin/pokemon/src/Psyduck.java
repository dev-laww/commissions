public class Psyduck extends Pokemon {
    private int headAche = 0;
    private double damageReduction = 0.0;

    public Psyduck(int level) {
        super(
                100 + (10 * level),
                level,
                70 + (2 * level)
        );
    }

    public Psyduck() {
        this(1);
    }

    public void meditate() {
        int energy = this.energy * 2;
        this.energy = Math.min(energy, this.maxEnergy);
        System.out.println(this.getClass().getSimpleName() + " has meditated.");
        this.damageReduction = 0.2;
        this.reduceStatusEffectDuration();
    }

    public void psyBlast(Pokemon target) {
        int damage = 30;

        if (this.energy < 30) {
            System.out.println("Not enough energy to psy blast.");
            return;
        }

        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        if (target.getHP() < 0.5 * target.maxHealth) {
            damage *= 2;
        }

        if (Math.random() > 0.5) {
            damage += 30 + (5 * this.level);
        }

        target.takeDamage(Damage.ELEMENTAL(damage), this);
        target.applyStatusEffect(new StatusEffect("Psyblast", 1));
        this.reduceStatusEffectDuration();
        this.energy -= 30;
        this.stackHeadAche(30);

        System.out.println(this.getClass().getSimpleName() + " has psy blasted " + target.getClass().getSimpleName() + ".");
    }

    public void wipeOut(Pokemon[] targets) {
        int damage = this.energy + this.headAche;
        this.headAche = 0;
        this.energy = 0;

        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        for (Pokemon target : targets) {
            for (StatusEffect statusEffect : target.statusEffects) {
                if (statusEffect.getName().equals("Psyblast")) {
                    target.takeDamage(Damage.ELEMENTAL(damage), this);
                }
            }
        }
    }

    private void stackHeadAche(int value) {
        this.headAche += (value / 2);
    }

    @Override
    public void takeDamage(Damage damage, Pokemon attacker) {
        if (damage.getType().equals("Physical")) {
            int bonusDamage = (int) (damage.getDamage() * 0.4);
            bonusDamage -= (int) (bonusDamage * this.damageReduction);
            damage.setDamage(damage.getDamage() + bonusDamage);
        } else {
            damage.setDamage(0);
            System.out.println(this.getClass().getSimpleName() + " has taken no damage.");
        }

        super.takeDamage(damage, attacker);
    }
}
