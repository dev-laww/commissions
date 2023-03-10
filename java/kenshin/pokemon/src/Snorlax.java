/**
 * Snorlax
 *
 * @author tora
 */

public class Snorlax extends Pokemon {
    private int endureCounter = 3;
    private boolean hard = true;
    private int bonusDamage = 0;
    private Pokemon lastAttacker;

    public Snorlax(int level) {
        super(
                (int) (200 + 0.1 * level * 200),  // health
                level,  // level
                60 + 2 * level  // energy
        );
    }

    public Snorlax() {
        this(1);
    }

    public void dozeOff() {
        if (this.energy < 50) {
            System.out.println("Not enough energy to doze off.");
            return;
        }

        this.energy -= 50;
        int damage_taken = this.maxHealth - this.health;
        int heal = (int) (damage_taken * 0.3);
        this.bonusDamage += 20;
        this.reduceStatusEffectDuration();
        this.heal(heal);
    }

    public void bodySlam(Pokemon target) {
        if (this.energy < 25) {
            System.out.println("Not enough energy to body slam.");
            return;
        }

        if (this.health < this.maxHealth * 0.5) {
            System.out.println("Not enough health to body slam.");
            return;
        }

        if (!this.ableToAttack()) {
            System.out.println("Not able to attack.");
            return;
        }

        System.out.println(this.getClass().getSimpleName() + " has body slammed " + target.getClass().getSimpleName() + ".");

        int damage =(int) (0.4 * this.maxHealth) + (this.level * 5) + this.bonusDamage;
        target.takeDamage(Damage.PHYSICAL(damage), this);
        target.applyStatusEffect(StatusEffect.SLEEP);

        this.reduceStatusEffectDuration();
        this.energy -= 25;
    }

    @Override
    public void takeDamage(Damage damage, Pokemon attacker) {
        if (this.endureCounter > 0) {
            this.endureCounter--;
            System.out.println(this.getClass().getSimpleName() + " has endured the attack.");
            return;
        }
        
        if (this.lastAttacker == attacker) {
            double grudgeBonus = 0.2;
            int grudgeDamage = (int) (damage.getDamage() * grudgeBonus);
            int grudgeBonusDamage = (int) (this.level * 0.02);

            this.takeDamage(Damage.PHYSICAL(grudgeDamage + grudgeBonusDamage), this);
        }

        if (this.hard) {
            damage.setDamage((int) (damage.getDamage() * 0.5));
            this.hard = false;
        }

        this.lastAttacker = attacker;
        super.takeDamage(damage, this);
    }
}
