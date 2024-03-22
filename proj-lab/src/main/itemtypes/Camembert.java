package main.itemtypes;

import main.Item;

public class Camembert extends Item {
    private int usesLeft = 3;

    @Override
    public String GetName() {
        return "Dobozolt Káposztás Camembert";
    }


}
