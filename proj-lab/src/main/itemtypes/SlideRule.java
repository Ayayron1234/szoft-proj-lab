package main.itemtypes;

import main.*;

/**
 * Represents a Slide Rule item within the game that serves as a victory condition.
 * When this item is picked up by an entity, it triggers the end of the game with a
 * victory. The class is linked to the game's state to facilitate this functionality.
 */
public class SlideRule extends Item {

    private Game game;

    /**
     * Constructs a SlideRule item with a reference to the game it is a part of.
     * This allows the Slide Rule to signal the game to end when it is picked up.
     *
     * @param game The game instance that this Slide Rule can influence.
     */
    public SlideRule(Game game){
        this.game = game;
    }

    /**
     * Returns the name of the sliderule.
     *
     * @return A string representing the name of the sliderule.
     */
    @Override
    public String GetName() {
        return "Logarléc";
    }

    /**
     * Executes actions when the Slide Rule is picked up by an entity. This includes
     * printing a message indicating that the Slide Rule has been picked up and triggering
     * the game's end with a victory condition.
     *
     * @param who The entity picking up the Slide Rule.
     * @param where The room from which the Slide Rule is picked up.
     */
    @Override
    public void PickedUp(Entity who, Room where) {
<<<<<<< Updated upstream
        if(IsFake()){
            //System.out.printf("%s picked up a fake sliderule from room #%d so Students won the game.\n", who.GetName(), where.GetRoomNumber());
            return;
        }
        //System.out.printf("%s picked up the sliderule from room #%d so Students won the game.\n", who.GetName(), where.GetRoomNumber());
        if(game != null) game.End(true);
=======
<<<<<<< Updated upstream
        System.out.println("SlideRule.PickedUp");
        new Game().End(true);

        /*
        System.out.printf("%s picked up the sliderule from room #%d so Students won the game.\n", who.GetName(), where.GetRoomNumber());
=======

        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }
        //System.out.printf("%s picked up the sliderule from room #%d so Students won the game.\n", who.GetName(), where.GetRoomNumber());
>>>>>>> Stashed changes
        if(game != null) game.End(true);
         */
>>>>>>> Stashed changes
    }
}
