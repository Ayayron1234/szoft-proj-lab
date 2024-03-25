package main;

public class Protection {
    private final ProtectionType type;

    /**
     * This method is the Constructor of the Protection class
     * @param type the type of the protection (out of the enum class ProtectionType)
     * @param duration the duration of the protection in rounds
     */
    public Protection(ProtectionType type, int duration) {
        this.type = type;
    }

    /**
     * This method is the getter of the duration
     * @return duration of the protection
     */
    public int GetDuration() {
        System.out.println("Protection.GetDuration");
        return 0;
    }

    /**
     * This method is the getter of the type
     * @return type of the protection (from the enum class ProtectionType)
     */
    public ProtectionType GetType() {
        System.out.println("Protection.GetType");
        return type;
    }

    /**
     * This method is to decrease the duration of the protection
     */
    public void DecreaseDuration() {
        System.out.println("Protection.DecreaseDuration");
    }
}
