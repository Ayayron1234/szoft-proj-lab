package main.itemtypes;

import main.Item;

public class Duster extends Item {
    private int usesLeft = 2;

    @Override
    public String GetName() {
        return "Nedves Táblatörlő Rongy";
    }
}
