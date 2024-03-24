package main;

public class Protection {
    private int duration;
    private final ProtectionType type;

    public Protection(ProtectionType type, int duration) {
        this.type = type;
        this.duration = duration;
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
}
