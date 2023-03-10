public class Haunter extends Pokemon {
    public Haunter(int level) {
        super(
                50 + 5 * level,
                level,
                90 + 7 * level
        );
    }

    public Haunter() {
        this(1);
    }

    public void instillFear(Pokemon target) {
        if (this.energy < 20) {
            System.out.println("Not enough energy to instill fear.");
            return;
        }

        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        this.heal(10);
        for (StatusEffect statusEffect : target.getStatusEffects()) {
            if (statusEffect == null) continue;
            if (statusEffect.getName().equals("Fear")) {
                target.takeDamage(Damage.ELEMENTAL(20), this);
            }
        }

        System.out.println(this.getClass().getSimpleName() + " has instilled fear in " + target.getClass().getSimpleName() + ".");

        target.applyStatusEffect(StatusEffect.FEAR);
        target.takeDamage(Damage.ELEMENTAL(20), this);
        this.heal(10);
        this.energy -= 20;
    }

    public void consumFear(Pokemon[] targets) {
        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        int damage = 40 + (2 * this.level);
        int fearCount = 0;

        System.out.println(this.getClass().getSimpleName() + " has consumed fear.");

        for (Pokemon target : targets) {
            for (StatusEffect statusEffect : target.getStatusEffects()) {
                if (statusEffect == null) continue;
                if (!statusEffect.getName().equals("Fear")) continue;

                target.takeDamage(Damage.ELEMENTAL(damage), this);
                fearCount++;
                System.out.println(this.getClass().getSimpleName() + " has consumed fear from " + target.getClass().getSimpleName() + ".");
            }
        }

        int healAndEnergyValue = 20 * fearCount + (this.level * 2);
        this.heal(healAndEnergyValue);
        this.energy += healAndEnergyValue;
    }

    @Override
    public void takeDamage(Damage damage, Pokemon attacker) {
        if (!damage.getType().equals("Elemental")) {
            damage.setDamage((int) (damage.getDamage() * 0.2));
        } else {
            int damageBonus = (int) (damage.getDamage() * 0.2);
            damage.setDamage(damage.getDamage() + damageBonus);
        }

        int healValue = 10 + (this.level * 2);
        this.heal(healValue);
        super.takeDamage(damage, this);

    }
}
