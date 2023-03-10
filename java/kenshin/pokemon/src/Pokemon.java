/**
 * Pokemon class.
 *
 * @author tora
 */

public abstract class Pokemon {
    protected int health;
    protected int level;
    protected int energy;

    public Pokemon(int health, int level, int energy) {
        this.health = health;
        this.level = level;
        this.energy = energy;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public int getLevel() {
        return level;
    }


    /**
     * Method to take damage from the target Pokemon.
     *
     * @param damage the damage to take
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println(this.getClass().getSimpleName() + " has fainted.");
        }
    }

    /**
     * Abstract method to attack the target Pokemon.
     *
     * @param target the target Pokemon
     */
    public abstract void attack(Pokemon target);
}
