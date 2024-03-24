package main.itemtypes;

import main.*;

public class SlideRule extends Item {

    private Game game;

    public SlideRule(Game game){
        this.game = game;
    }

    @Override
    public String GetName() {
        return "Logarléc";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.printf("%s picked up the sliderule from room #%d so Students won the game.\n", who.GetName(), where.GetRoomNumber());
        if(game != null) game.End(true);
    }
}
