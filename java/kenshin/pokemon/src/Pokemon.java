/**
 * Pokemon class.
 *
 * @author tora
 */

public abstract class Pokemon {
    protected int health;
    protected int level;
    protected int energy;
    protected int maxHealth;
    protected int maxEnergy;
    protected StatusEffect[] statusEffects = new StatusEffect[20];

    public Pokemon(int health, int level, int energy) {
        this.health = health;
        this.level = level;
        this.energy = energy;
        this.maxHealth = health;
        this.maxEnergy = energy;
    }

    public int getHP() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public int getLevel() {
        return level;
    }

    public StatusEffect[] getStatusEffects() {
        return statusEffects;
    }


    /**
     * Method to take damage from the target Pokemon.
     *
     * @param damage the damage to take
     */
    public void takeDamage(Damage damage, Pokemon attacker) {
        int damageValue = damage.getDamage();
        int damage_bonus = 0;

        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect == null) continue;

            switch (statusEffect.getName()) {
                case "Burn", "Poison":
                    damage_bonus += (int) (0.05 * damageValue);
                    break;
                case "Freeze":
                    damage_bonus += (int) (0.2 * damageValue);
                    break;
                case "Paralysis":
                    if (Math.random() < 0.25) {
                        damage_bonus += (int) (0.25 * damageValue);
                    }
                    break;
                case "Sleep":
                    damage_bonus += (int) (0.3 * damageValue);
                    break;
                case "Electrocuted":
                    damage_bonus += (int) (0.1 * damageValue);
            }
        }

        switch (damage.getType()) {
            case "Electric":
                this.applyStatusEffect(StatusEffect.ELECTROCUTED);
        }

        this.reduceStatusEffectDuration();

        if (damage.isCritical) {
            damage_bonus += (int) (0.3 * damageValue);
        }

        health -= damageValue + damage_bonus;
        if (health <= 0) {
            health = 0;
            System.out.println(this.getClass().getSimpleName() + " has fainted.");
        }
    }

    public void applyStatusEffect(StatusEffect effect) {
        System.out.println(this.getClass().getSimpleName() + " has been inflicted with " + effect.getName() + ".");
        for (int i = 0; i < statusEffects.length; i++) {
            if (statusEffects[i] != null) {
                return;
            }
            statusEffects[i] = effect;
        }
    }

    public void reduceStatusEffectDuration() {
        for (int i = 0; i < statusEffects.length; i++) {
            if (statusEffects[i] == null) continue;

            statusEffects[i].reduceDuration();

            if (statusEffects[i].getDuration() == 0) {
                statusEffects[i] = null;
            }
        }
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }

        System.out.println(this.getClass().getSimpleName() + " has healed " + amount + " HP.");
    }

    public boolean ableToAttack() {
        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect == null) continue;

            switch (statusEffect.getName()) {
                case "Burn", "Freeze", "Paralysis", "Poison", "Sleep":
                    return false;
            }
        }
        return true;
    }

    public void levelUp() {
        this.level++;
        this.maxHealth += 0.1 * this.maxHealth;
        this.maxEnergy += 0.1 * this.maxEnergy;
        this.health = this.maxHealth;
        this.energy = this.maxEnergy;
    }
}
