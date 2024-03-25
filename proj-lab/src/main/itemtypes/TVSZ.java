package main.itemtypes;

import main.*;

import java.util.Scanner;

public class TVSZ extends Item {
    private int usesLeft = 3;
    // When in an entity's inventory providedProtection is the object which is in the entity's activeProtections list.
//    private Protection providedProtection = null;
    private Entity owner = null;

    /**
     * This method returns the name of the TVSZ
     * @return A string representing the name of this item.
     */
    @Override
    public String GetName() {
        System.out.println("TVSZ.GetName");
        return "TVSZ Denevérbőrre Nyomtatott Példánya";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.println("TVSZ.PickedUp");
        owner = who;

        /*
        System.out.printf("%s picked up tvsz with duration:%d.\n", who.GetName(), usesLeft);
        owner = who;
        // Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, usesLeft);
        // providedProtection = protection;
        // who.AddProtection(protection);
        // TODO: this
            */
    }

    @Override
    public void Placed(Entity who, Room where) {
        System.out.println("TVSZ.Placed");
        owner = null;

        /*
        System.out.printf("%s placed down tvsz.\n", who.GetName());
        owner = null;
         */
    }


    /**
     * The method is to control when the TVSZ is used (a freshly one is usable 3 times and the game remembers how many times it was used)
     */
    public void Use() {
        System.out.println("TVSZ.Use");

        usesLeft--;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Does the TVSZ have any more uses? 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("1"))  return;
        owner = null;

        /*
        usesLeft--;
        if (usesLeft > 0)
            System.out.printf("%s's protection provided by tvsz now has uses left:%d.\n", owner.GetName(), usesLeft);
        else {
            System.out.printf("%s broke tvsz with last use and lost protection.\n", owner.GetName());
            owner = null;
        }
         */
    }

}
