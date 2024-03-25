package main.itemtypes;

import main.*;

import java.util.Scanner;

public class Beer extends Item {
    private int durationLeft = 3;
    private Protection providedProtection = null;
    private Entity owner = null;

    /**
     * @return
     */
    @Override
    public String GetName() {
        System.out.println("Beer.GetName");
        return "Szent Söröspohár";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.println("Beer.PickedUp");

        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, durationLeft);
        providedProtection = protection;
        owner = who;
        who.AddProtection(protection);

        /*
        System.out.printf("%s picked up beer with duration:%d.\n", who.GetName(), durationLeft);

        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, durationLeft);
        providedProtection = protection;
        owner = who;
        who.AddProtection(protection);*/
    }

    @Override
    public void Placed(Entity who, Room where) {
        System.out.println("Beer.Placed");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the beer empty? 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("2")){
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }

        /*
        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
            System.out.printf("%s placed down beer and lost protection.\n", who.GetName());
        } else
            System.out.printf("%s placed down beer.\n", who.GetName());

        owner = null;
         */
    }

    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Beer.StartRound");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the beer empty? 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("2")){
            providedProtection.DecreaseDuration();
            durationLeft = providedProtection.GetDuration();
        }
        /*
        if (providedProtection == null)
            return;

        providedProtection.DecreaseDuration();
        durationLeft = providedProtection.GetDuration();
        if (providedProtection.GetDuration() == 0) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
            System.out.printf("%s lost protection from beer.\n", owner.GetName());
        }
        else
            System.out.printf("%s's protection provided by beer now has duration:%d.\n", owner.GetName(), durationLeft);
    */
    }
}
