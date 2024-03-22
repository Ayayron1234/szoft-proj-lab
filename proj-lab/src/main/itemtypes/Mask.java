package main.itemtypes;

import main.Item;

public class Mask extends Item {
    private int usesLeft = 3;

    @Override
    public String GetName() {
        return "FFP2-es Maszk";
    }
}
