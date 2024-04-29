package main;


/**
 * The Protection class represents a protective effect that can be applied to entities within the game.
 * This class define specific protections provided by items to entities.
 */
public class Protection {
    private Item provider;
    private int duration;
    private final ProtectionType type;

    private int uid;

    static private int previousUid = 0;

    /**
     * This method is the Constructor of the Protection class with 3 parameters
     *
     * @param type the type of the protection (out of the enum class ProtectionType)
     * @param duration the duration of the protection in rounds
     * @param provider the Item that provides the protection
     */
    public Protection(ProtectionType type, int duration, Item provider) {
        this.type = type;
        this.duration = duration;
        this.provider = provider;
        this.uid = previousUid + 1;
        this.previousUid = uid;
    }

    /**
     * This method is the getter of the duration
     *
     * @return duration of the protection
     */
    public int GetDuration() {
        return duration;
    }

    /**
     * This method is the getter of the type
     *
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

    /**
     * This method is the getter of the provider
     *
     * @return the Item that provides the protection
     */
    public Item GetProvider() { return provider; }

    /**
     * This method is the getter of the uid
     *
     * @return the uid of the protection
     */
    public int GetUid() { return uid; }
}
