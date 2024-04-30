package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;
import main.actions.SoulDrainer;

import java.util.Random;

/**
 * Represents a protective Beer item that can provide protection against souldraining for a limited duration.
 * Once picked up by an entity,
 * it grants a protection effect that decreases in duration each round until it expires.
 * But it has a side effect, everytime it's used the entity who used it become drunk and drop an item from the inventory.
 */
public class Beer extends Item {
    private int         durationLeft = 3;
    private Protection  providedProtection = null;
    private Entity      owner = null;

<<<<<<< Updated upstream
=======
    /**
<<<<<<< Updated upstream
     * @return
=======
     * Returns the name of the beer.
     *
     * @return A string representing the name of the beer.
>>>>>>> Stashed changes
     */
>>>>>>> Stashed changes
    @Override
    public String GetName() {
        return "Szent Söröspohár";
    }

    /**
     * When an entity picks up a Beer item they are granted soul drain protection for a certain amount of time.
     * @param who   the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    @Override
    public void PickedUp(Entity who, Room where) {
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
        System.out.println("Beer.PickedUp");

        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, durationLeft);
        providedProtection = protection;
        who.AddProtection(protection);
=======

        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }
>>>>>>> Stashed changes
        if(!IsFake()) {
            owner = who;
            Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, durationLeft, this);
            providedProtection = protection;
            who.AddProtection(protection);
        }
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    /**
     * Placing the beer removes the soul drain protection the entity has.
     * @param who   the entity who placed the item
     * @param where the room where the item was placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        if(IsFake()) {
            return;
        }

<<<<<<< Updated upstream
        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }

        owner = null;
=======
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the beer empty? 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")){
            who.RemoveProtection(providedProtection);
        }
>>>>>>> Stashed changes
    }

    /**
     * Each round the Beer item reduces the duration of it's protection being active.
     * If it reaches 0 remove the protection from the owner.
     * @param data Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
     */
    @Override
    public void StartRound(TimerEvent data) {
        if (IsFake())
            return;

<<<<<<< Updated upstream
        if (providedProtection == null)
            return;

        providedProtection.DecreaseDuration();
        durationLeft = providedProtection.GetDuration();
        if (providedProtection.GetDuration() == 0) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }
    }

    @Override
    public void Use(Entity entity) {
        if(IsFake()) return;
        if (entity.GetItems().isEmpty())
            throw new RuntimeException("Entity's inventory should contain at least a beer. ");

        System.out.printf("%s blocks soul drain with %s\n", entity.GetName(), GetName());

        // Entity drops a random item from their inventory
        int indexOfDroppedItem = new Random().nextInt(0, entity.GetItems().size() - 1);
        entity.DropItem(entity.GetItems().get(indexOfDroppedItem));
    }

    @Override
    public void Deserialize(JsonObject json) {
        
=======
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the beer empty? 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")){
            new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, 3).DecreaseDuration();
            new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, 3).GetDuration();
        }
>>>>>>> Stashed changes
    }
<<<<<<< Updated upstream
=======

    /**
     * When the Beer item is used it negates a soul drain action aimed towards it's owner.
     * @param entity The entity that using the item.
     */
    @Override
    public void Use(Entity entity) {
        if(IsFake()) return;
        if (entity.GetItems().isEmpty())
            throw new RuntimeException("Entity's inventory should contain at least a beer. ");

        System.out.printf("%s blocks soul drain with \"%s\"\n", entity.GetName(), GetName());

        // Entity drops a random item from their inventory
        if (entity.GetItems().size() > 0) {
            int indexOfDroppedItem = new Random().nextInt(0, entity.GetItems().size());
            entity.DropItem(entity.GetItems().get(indexOfDroppedItem));
        }
    }

    /**
     * Deserialize a json object into a Beer object
     * @param json
     */
    @Override
    public void DeserializeSpecificItems(Game game, JsonObject json) {
        if(json.has("durationLeft")) {
            durationLeft = json.get("durationLeft").getAsInt();
        }

        if(json.has("providedProtection")) {
            Protection protection = game.GetDeserializedObjectReference(json.get("providedProtection").getAsString());
            providedProtection = protection;
            providedProtection.SetProvider(this);
        }
    }

    @Override
    public void SetOwnerIfNeeded(Entity owner) {
        this.owner = owner;
    }
>>>>>>> Stashed changes
}
