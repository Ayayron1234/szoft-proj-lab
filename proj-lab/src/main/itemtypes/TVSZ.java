package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;

public class TVSZ extends Item {
    private int usesLeft = 3;

    @Override
    public String GetName() {
        return "TVSZ Denevérbőrre Nyomtatott Példánya";
    }

    public void Use() {

    }

}
