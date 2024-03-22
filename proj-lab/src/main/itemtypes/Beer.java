package main.itemtypes;

import main.Item;

public class Beer extends Item {
    private int usesLeft = 3;

    @Override
    public String GetName() {
        return "Szent Söröspohár";
    }
}
