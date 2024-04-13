package main;

public class Protection {
    private Item provider;
    private int duration;
    private final ProtectionType type;

    public Protection(ProtectionType type, int duration, Item provider) {
        this.type = type;
        this.duration = duration;
        this.provider = provider;
    }

    public int GetDuration() {
        return duration;
    }

    public ProtectionType GetType() {
        return type;
    }

    public void DecreaseDuration() {
        duration -= 1;
    }

    public Item GetProvider() { return provider; }
}
