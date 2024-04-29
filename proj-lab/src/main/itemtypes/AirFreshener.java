package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
import main.roomabilities.PoisonAbility;
/**
 * Represents an Air Freshener item within the game. This item can be placed or dropped in a room to remove any poison abilities
 * present, effectively "refreshing" the air. If the item is a fake, it has no effect when used.
 */
public class AirFreshener extends Item {
    
    /**
     * Returns the name of the item.
     * 
     * @return the name of the item
     */
    @Override
    public String GetName() {
        return "Légfrissítő";
    }

    /**
     * Defines what happens when the Air Freshener is placed in a room by an entity. If the item is genuine, it removes
     * the poison ability from the room, making it safer for entities. The action is logged with a message 
     * that says the air has been refreshed.
     * 
     * @param who the entity that placed the air freshener
     * @param where the room in which the air freshener was placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        // If item is fake, it has no effect
        if (IsFake())
            return;

        // Remove poison ability from the room where it was placed
        Class poisonerClass = PoisonAbility.class;
        where.RemoveAbilityType(poisonerClass);
        System.out.printf("%s refreshes air\n", GetName());
    }
 /**
     * Defines what happens when the Air Freshener is dropped in a room by an entity. Similar to when placed,
     * if the item is genuine, it will remove poison abilities from the room. A message will be printed to indicate
     * the refreshing action.
     * 
     * @param who the entity that dropped the air freshener
     * @param where the room in which the air freshener was dropped
     */
    @Override
    public void Dropped(Entity who, Room where) {
        // If item is fake, it has no effect
        if (IsFake())
            return;

        // Remove poison ability from the room where it was placed
        Class poisonerClass = PoisonAbility.class;
        where.RemoveAbilityType(poisonerClass);
        System.out.printf("%s refreshes air\n", GetName());
    }

}
