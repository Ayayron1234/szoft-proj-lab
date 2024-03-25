package main.itemtypes;

import main.*;
import main.actions.SoulDrainer;

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
        who.AddProtection(protection);
    }

    @Override
    public void Placed(Entity who, Room where) {
        System.out.println("Beer.Placed");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the beer empty? 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")){
            who.RemoveProtection(providedProtection);
        }
    }

    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Beer.StartRound");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the beer empty? 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")){
            new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, 3).DecreaseDuration();
            new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, 3).GetDuration();
        }
    }
}
