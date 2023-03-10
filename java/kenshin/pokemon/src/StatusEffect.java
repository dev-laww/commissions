public class StatusEffect {
    private final String name;
    private int duration;

    public StatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void reduceDuration() {
        duration--;
    }

    public static StatusEffect SLEEP = new StatusEffect("Sleep", 1);
    public static StatusEffect ELECTROCUTED = new StatusEffect("Electrocuted", 3);
    public static StatusEffect FEAR = new StatusEffect("Fear", 3);
}