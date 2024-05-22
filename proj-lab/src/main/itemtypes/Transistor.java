package main.itemtypes;


import main.Entity;
import main.Item;
import main.Room;
import main.Item;
import com.google.gson.JsonObject;
import main.*;

/**
 * Represents a Transistor item within the game that serves as a teleport if both of the pieces are placed down.
 * You can teleport between the rooms where the tranzistors pairs are placed.
 * When this item is picked up by an entity, and it's unpaired it automatically creates
 * its pair and places down in the same room where it was picked up.
 */
public class Transistor extends Item {
    Transistor pair = null;
    Room location = null;

    /**
     * Returns the name of the transistor.
     *
     * @return A string representing the name of the transistor.
     */
    @Override
    public String GetName() {
        return "Tranzisztor";
    }

    /**
     * Pick up the Transistor. If it is unpaired, create it's pair and place it in the room.
     * @param who   the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }

        location = null;

        if (pair == null) {
            Transistor pairedTransistor = new Transistor();
            pairedTransistor.pair = this;
            pairedTransistor.location = where;
            pair = pairedTransistor;
            where.PlaceItem(pair);
        }
    }


    /**
     * Place the Transistor.
     * If it has a pair, teleport the entity placing down the Transistor into the room where the pair is.
     * @param who   the entity who placed the item
     * @param where the room where the item was placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        if (IsFake())
            return;
        location = where;

        // Teleport entity to the paired transistor's location and pick up paired transistor
//        who.Teleport(pair.location);
//        System.out.printf("%s is teleported to room#%d", who.GetName(), pair.location.GetRoomNumber());
//        who.PickUpItem(pair);

        //System.out.printf("%s placed \"%s\"\n", who.GetName(), GetName());

        if (pair == null || pair.location == null) {
            //System.out.println("VALAMI NULL");
            return;
        }

        // Teleport entity to the paired transistor's location and pick up paired transistor
        who.Teleport(pair.location);
        System.out.printf("%s is teleported to %s\n", who.GetName(), pair.location.toString());
        who.PickUpItem(pair);
    }

    /**
     * @param who   the entity who dropped the item
     * @param where the room where the item was dropped
     */
    @Override
    public void Dropped(Entity who, Room where) {
        if (IsFake())
            return;
        location = where;
    }

    @Override
    public void DeserializeSpecificItems(Game game, JsonObject json) {
        if(json.has("pair")) {
            Transistor pMos = game.GetOrCreateDeserializedObjectReference(
                    json.get("pair").getAsString(),
                    () -> (Transistor)game.CreateItem("Transistor")
            );
            pair = pMos;
            pair.location = pMos.location;
        }
    }

    @Override
    public void SetLocationIfNeeded(Room location) {
        this.location = location;
    }
}
