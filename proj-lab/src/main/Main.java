package main;

import main.itemtypes.Beer;
import main.itemtypes.TVSZ;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.Start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a Script's number:\n" +
                "The scripts that can be chosen:\n" +
                "1: Student stepping between rooms\n" +
                "2: Room is on full capacity\n" +
                "3: Student picks up, and places items\n" +
                "4: \n" +
                "Choose one:\n");
        int numberOfScript = scanner.nextInt();
        script(numberOfScript);
    }

    public static void script(int numberOfScript){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            switch (numberOfScript) {
                case 1:
                    System.out.println("You entered to the first script.");
                    Scenario1(scanner);
                    break;
                case 2:
                    System.out.println("You entered to the second script.");
                    Scenario2(scanner);
                    break;
                case 3:
                    System.out.println("You entered to the third script.");
                    Scenario3(scanner);
                    break;
                case 4:
                    System.out.println("You entered to the fourth script.");
                    Scenario4(scanner);
                    break;
                case 5:
                    System.out.println("You entered to the fifth script.");
                    break;
                default:
                    System.out.println("There was an error! That script number is invalid!");
            }
        }
    }

    public static void Scenario1(Scanner scanner){
        Room room1 = new Room(2);
        Room room2 = new Room(2);
        Room room3 = new Room(2);

        room1.AddNeighbour(room2);
        room2.AddNeighbour(room1);
        room2.AddNeighbour(room3);

        Student student = new Student("player");

        student.Teleport(room1);
        System.out.println("3 rooms are given in the following structure: 1 <-> 2 -> 3\n" +
                "you are now in room 1, you may now step into a neighbouring room, choose one:");
        while (scanner.hasNextInt()) {
            int numberOfRoomToStep = scanner.nextInt();
            switch (numberOfRoomToStep) {
                case 1:
                    if (student.Step(room1)) {
                        System.out.println("You are now in room 1");
                    } else {
                        System.out.println("You can't step into that room");
                    }
                    break;
                case 2:
                    if (student.Step(room2)) {
                        System.out.println("You are now in room 2");
                    } else {
                        System.out.println("You can't step into that room");
                    }
                    break;
                case 3:
                    if (student.Step(room3)) {
                        System.out.println("You are now in room 3");
                    } else {
                        System.out.println("You can't step into that room");
                    }
                    break;
                default:
                    System.out.println("Exiting script!");
                    break;
            }
        }
    }

    public static void Scenario2(Scanner scanner) {
        Room room1 = new Room(1);
        Room room2 = new Room(1);
        Room room3 = new Room(1);

        room1.AddNeighbour(room2);
        room2.AddNeighbour(room3);
        Student student = new Student("player");
        Teacher teacher = new Teacher();
        student.Teleport(room1);
        teacher.Teleport(room2);
        System.out.println("3 rooms are given in the following structure: 1 -> 2 -> 3\n" +
                "you are now in room 1, you may now step into a neighbouring room, choose one:");
        int numberOfRoomToStep = scanner.nextInt();
        switch (numberOfRoomToStep) {
            case 1:
                if (student.Step(room1)) {
                    System.out.println("You are now in room 1");
                } else {
                    if(room1.GetCapacity() == room1.GetEntities().size()) System.out.println("You can't step into that room because it's full");
                    else System.out.println("You can't step into that room");
                }
                break;
            case 2:
                if (student.Step(room2)) {
                    System.out.println("You are now in room 2");
                } else {
                    if(room2.GetCapacity() == room2.GetEntities().size()) System.out.println("You can't step into that room because it's full");
                    else System.out.println("You can't step into that room");
                }
                break;
            case 3:
                if (student.Step(room3)) {
                    System.out.println("You are now in room 3");
                } else {
                    if(room3.GetCapacity() == room3.GetEntities().size()) System.out.println("You can't step into that room because it's full");
                    else System.out.println("You can't step into that room");
                }
                break;
            default:
                System.out.println("Exiting script!");
                break;
        }
        teacher.Step(room3);
        numberOfRoomToStep = scanner.nextInt();
        switch (numberOfRoomToStep) {
            case 1:
                if (student.Step(room1)) {
                    System.out.println("You are now in room 1");
                } else {
                    if(room1.GetCapacity() == room1.GetEntities().size()) System.out.println("You can't step into that room because it's full");
                    else System.out.println("You can't step into that room");
                }
                break;
            case 2:
                if (student.Step(room2)) {
                    System.out.println("You are now in room 2");
                } else {
                    if(room2.GetCapacity() == room2.GetEntities().size()) System.out.println("You can't step into that room because it's full");
                    else System.out.println("You can't step into that room");
                }
                break;
            case 3:
                if (student.Step(room3)) {
                    System.out.println("You are now in room 3");
                } else {
                    if(room3.GetCapacity() == room3.GetEntities().size()) System.out.println("You can't step into that room because it's full");
                    else System.out.println("You can't step into that room");
                }
                break;
            default:
                System.out.println("Exiting script!");
                break;
        }
    }

    public static void Scenario3(Scanner scanner) {
    Room room1 = new Room(2);
    Room room2 = new Room(2);
    room1.AddNeighbour(room2);
    room2.AddNeighbour(room1);

    // Create items and add them to rooms
    Item beer = new Beer();
    Item tvsz = new TVSZ();
    room1.PlaceItem(beer);
    

    Student student = new Student("player");
    student.Teleport(room1);

    System.out.println("There are two rooms connected to each other. Room 1 has a Beer, and Room 2 has a TVSZ.\n" +
            "You are currently in Room 1. Choose an action:\n" +
            "1: Move to Room 2\n" +
            "2: Pick up Beer\n" +
            "3: Move to Room 1 (if in Room 2)\n" +
            "4: Place Beer in Room 2 (if holding)\n" +
            "5: Exit");


    while (scanner.hasNextInt()) {
        int action = scanner.nextInt();
        switch (action) {
            case 1:
            if(student.GetContainingRoom()!=room2 && student.GetContainingRoom().GetNeighbours().contains(room2)){
                student.Step(room2);
                System.out.println("Moved to Room 2.");
            }else if(student.GetContainingRoom()==room2){
                System.out.println("You already in Room 2.");
            }else
                System.out.println("The Room you want to Step in is not a neighbour");

                break;
            case 2:
                if(student.GetItems().contains(beer)) {
                    System.out.println("You already have the Beer.");
                } else if(student.GetContainingRoom().GetItems().contains(beer)) {
                    student.PickUpItem(beer);
                    student.GetContainingRoom().PickUpItem(beer);
                    System.out.println("Picked up Beer.");
                }else
                    System.out.println("The Room don't contain the Beer");
                break;
            case 3:
            if(student.GetContainingRoom()!=room1 && student.GetContainingRoom().GetNeighbours().contains(room1)){
                student.Step(room1);
                System.out.println("Moved to Room 1.");
            }else if(student.GetContainingRoom()==room1){
                System.out.println("You already in Room 1.");
            }else
                System.out.println("The Room you want to Step in is not a neighbour");

                break;
            case 4:
                if(student.GetItems().contains(beer)) {
                    student.GetContainingRoom().PlaceItem(beer);
                    student.PlaceItem(beer);
                    System.out.println("Placed Beer in containing room");
                } else {
                    System.out.println("You don't have Beer to place.");
                }
                break;
            case 5:
                System.out.println("Exiting Scenario 3.");
                return;
            default:
                System.out.println("Invalid action. Try again.");
                break;
        }
    }
}

    public static void Scenario4(Scanner scanner) {
        Game game = new Game();
    }
}