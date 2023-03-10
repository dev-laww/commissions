public class Damage {
    private int damage;
    public boolean isCritical;
    private String type;

    public Damage(int damage,  String type) {
        this.damage = damage;
        this.isCritical = Math.random() < 0.0625;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public static Damage PHYSICAL(int damage) {
        return new Damage(damage, "Physical");
    }

    public static Damage ELEMENTAL(int damage) {
        return new Damage(damage, "Elemental");
    }
}
