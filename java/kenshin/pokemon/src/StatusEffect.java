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
}