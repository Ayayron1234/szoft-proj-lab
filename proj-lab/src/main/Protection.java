package main;

public class Protection {
    private final int duration;
    private final ProtectionType type;

    Protection(ProtectionType type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public int GetDuration() {
        return duration;
    }

    public ProtectionType GetType() {
        return type;
    }
}
