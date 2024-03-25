package main;

public class Protection {
    private int duration;
    private final ProtectionType type;

    /**
     * This method is the Constructor of the Protection class
     * @param type the type of the protection (out of the enum class ProtectionType)
     * @param duration the duration of the protection in rounds
     */
    public Protection(ProtectionType type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    /**
     * This method is the getter of the duration
     * @return duration of the protection
     */
    public int GetDuration() {
        return duration;
    }

    /**
     * This method is the getter of the type
     * @return type of the protection (from the enum class ProtectionType)
     */
    public ProtectionType GetType() {
        return type;
    }

    /**
     * This method is to decrease the duration of the protection
     */
    public void DecreaseDuration() {
        duration -= 1;
    }
}
