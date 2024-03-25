package main;

import main.actions.Poisoner;
import main.actions.SoulDrainer;
import main.actions.Stunner;
import main.itemtypes.Beer;
import main.itemtypes.Camembert;
import main.itemtypes.SlideRule;
import main.itemtypes.TVSZ;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        script();
    }

    /**
     * A hallgató egy mérgező szobában van egy kör elején.
     * A szoba megmérgezi a benne lévő entitásokat.
     * A hallgató tarsolyában van egy működő maszk, ezért védett a méreg ellen.
     * A hallgatóra nincs hatással a mérgező szoba.
     */
    public static void ScenarioBlockPoison() {
        Room room = new Room(2);
        Poisoner poisoner = new Poisoner();
        System.out.println("new Poisoner");
        Student student = new Student("player");

        student.ApplyAction(poisoner);
    }

    /**
     * A hallgató egy szobában tartózkodik egy oktatóval.
     * Az oktató elveszi a lelkét a szobában tartózkodó hallgatóknak.
     * A hallgató tarsolyában van TVSZ, vagy Szent Söröspohár, emiatt védett a lélek elszívás ellen.
     * A hallgató megmenekül, nem esik ki a játékból.
     */
    public static void ScenarioBlockSoulDrain() {
        Room room = new Room(2);
        Student student = new Student("player");
        Teacher teacher = new Teacher();

        System.out.println("new SoulDrainer");
        SoulDrainer drainer = new SoulDrainer();

        room.GetEntities();

        student.ApplyAction(drainer);
    }

    /**
     * Az oktató a kör elején egy szobában van más entitásokkal.
     * Megpróbálja elvenni az entitások lelkét.
     * Az entitás hallgató, és nincs védelme lelkének elvételével szemben.
     * A hallgató kiesik a játékból.
     */
    public static void ScenarioDrainSoul() {
        Teacher teacher = new Teacher();
        Room room = new Room(0);

        teacher.DrainSouls();
    }

    /**
     * Az elátkozott szoba értesül egy kör kezdetéről.
     * A szoba kiválaszt néhány (akár 0) még nem eltűntetett ajtót.
     * A kiválasztott ajtókat eltünteti, de számon tartja mint egy rejtett ajtó.
     */
    public static void ScenarioHideDoor () {
        Room room = new Room(0);
        room.ActivateAbilities();
    }

    /**
     * Az utolsó hallgatóval egy szobába kerül egy tanár.
     * A tanár elszívja a hallgató lelkét.
     * Nincs a hallgatónál a lélek elszívását blokkoló tárgy vagy éppen  nincs lélek elszívást blokkoló hatás rajta.
     * Megnézzük hány még játékban lévő hallgató maradt.
     * Ha ez a szám nullára csökkent akkor a játék a hallgatók (játékosok) vereségével véget ért.
     */
    public static void ScenarioLoseGame() {
        Teacher teacher = new Teacher();
        //Room room = new Room(2);
        SoulDrainer drainer = new SoulDrainer();
        System.out.println("new SoulDrainer");
        Student student = new Student("player");

        student.ApplyAction(drainer);
    }

    /**
     * Kör elején két egymással szomszédos szoba egyesül.
     * Az új szoba az egyesített szobák tulajdonságait és szomszédait megörökli.
     * Az új szoba befogadóképessége a nagyobb szoba befogadóképességével lesz azonos.
     */
    public static void ScenarioMergeRooms() {
        Room a = new Room(0);
        Room b = new Room(0);
        Room.MergeRoom(a, b);
    }

    /**
     * Az entitás megnézi, hogy milyen tárgyak vannak az őt tartalmazó szobában.
     * Az entitás kiválaszt egy elérhető tárgyat, amit az entitás típusa fel tud venni.
     * Az entitás tarsolyában még van elegendő hely.
     * A tárgy bekerül az entitás tarsolyába és értesül erről.
     */
    public static void ScenarioPickupItem () {
        Student student = new Student("");
        Item item = new TVSZ();
        student.PickUpItem(item);
    }

    /**
     * Az entitás megnézi, hogy milyen tárgyak vannak a tarsolyában.
     * Az entitás kiválaszt egy tárgyat.
     * A tárgy kikerül az entitás tarsolyából és bekerül a szobába amiben az entitás áll.
     * A tárgy értesül róla, hogy lerakták.
     */
    public static void ScenarioPlaceItem () {
        Student student = new Student("");
        Item item = new TVSZ();
        student.PlaceItem(item);
    }

    /**
     * Vonatkozik ez bármely entitásra amely az adott szobában tartózkodik a kör elején.
     * A szoba megmérgezi a benne lévő entitásokat.
     * Amennyiben a hatás ellen nem tudnak védekezni eszméletüket vesztik.
     * Az eszméletüket vesztett entitások elejtik az összes a tarsolyukban lévő tárgyat.
     * Az eszméletüket vesztett entitások kimaradnak az adott körből.
     */
    public static void ScenarioPoisonEntities () {
        Room room = new Room(0);
        room.ActivateAbilities();
    }

    /**
     * Az entitás leteszi a tarsolyából (“kibontja” és ezzel elhasználja) a Camembert tárgyat.
     * A szoba ennek hatására mérgezővé válik három kör erejéig.
     */
    public static void ScenarioPoisonateRoom () {
        Student student = new Student("player");

        Camembert camembert = new Camembert();
        student.PlaceItem(camembert);

        camembert.StartRound(new TimerEvent(0,0,0));
    }

    /**
     * Az elátkozott szoba értesül egy kör kezdetéről.
     * A szoba kiválaszt néhány (akár 0) már eltűntetett ajtót.
     * A kiválasztott ajtók újra előtűnnek.
     */
    public static void ScenarioRevealDoor() {
        Room room = new Room(0);
        room.ActivateAbilities();
    }

    /**
     * Kör elején egy szoba kettéválik.
     * A két létrejövő szoba szomszédos.
     * A szobák megöröklik az eredeti
     */
    public static void ScenarioSplitRooms() {
        Room baseRoom = new Room(0);
        baseRoom.SplitRoom();
    }

    /**
     * Egy adott hallgató vagy oktató lépése kezdődik el.
     */
    public static void ScenarioStartTurn() {
        Timer timer = new Timer();
        Entity entity = new Student("");
        timer.StartTurn(entity, new TimerEvent(0, 0, 0));
    }

    /**
     * Véget ér egy kör anélkül, hogy vége lenne a játéknak.
     * A megfelelő tárgyak és entitások értesülnek a kör elejéről.
     * Elkezdődnek a hallgatók és oktatók lépései.
     */
    public static void ScenarioStartRound() {
        Timer timer = new Timer();
        timer.StartRound(new TimerEvent(0, 0, 0));
    }

    /**
     * Az entitás (oktató, vagy hallgató) belép egy általa kiválasztott szobába, és elhagyja azt a szobát, amiben eddig tartózkodott.
     */
    public static void ScenarioStep() {
        Entity entity = new Student("");
        Room neighbourRoom = new Room(2);
        entity.Step(neighbourRoom);
    }

    /**
     * A hallgatóval egy szobába lép egy tanár.
     * Megpróbálja elszívni a hallgató lelkét.
     * Ha rendelkezik még Nedves Táblatörlő Ronggyal a hallgató akkor megdobja vele az egy szobában tartózkodó tanárt, ezzel megbénítva őt.
     */
    public static void ScenarioStunTeacher() {
        Entity teacher = new Teacher();
        Entity student1 = new Student("");
        System.out.println("new Stunner");
        Stunner stunner = new Stunner(2);

        teacher.ApplyAction(stunner);
    }

    /**
     * A hallgató lehelyezi a Tranzisztor pozitív pólusát egy szobában.
     * A hallgató lehelyezi a Tranzisztor negatív pólusát egy másik szobában.
     * Ha a hallgató használja valamely pólust az adott szobákból a másik pólust tartalmazó szobába kerül a használat hatására.
     */
    public static void ScenarioTeleport() {
        Entity entity = new Student("");
        Room room1 = new Room(1);
        entity.Teleport(room1);
    }

    /**
     * Egy hallgató belép egy szobába, amiben megtalálható a Logarléc
     * A hallgató felveszi
     * Megnyerik a játékot a hallgatók
     * A játéknak vége.
     */
    public static void ScenarioWinGame() {
        Entity entity = new Student("");
        Room room1 = new Room(1);
        Item item1 = new SlideRule(new Game());

        entity.PickUpItem(item1);
    }

    // Block Poison, Block Soul Drain, Drain Soul, Hide Door, Lose Game, Merge Rooms, Pickup Item, Place Item, Poison Entities, Poisonate Room, Reveal Door, Split Rooms, Start Round, Start Turn, Step, Stun Teacher, Teleport, Win Game

    /**
     * This method is to choose a script out of the ones we created
     * If we write one that doesn't match the type there's an error
     * If we write one that doesn't match the listed numbers we write it to the user....
     */
    public static void script(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose a sequence's number:\n" +
                "The sequences that can be chosen:\n\n" +
                "1: Block Poison\n" +
                "2: Block Soul Drain\n" +
                "3: Drain Soul\n" +
                "4: Hide Door\n" +
                "5: Lose Game\n" +
                "6: Merge Rooms\n" +
                "7: Pickup Item\n" +
                "8: Place Item\n" +
                "9: Poison Entities\n" +
                "10: Poisonate Room\n" +
                "11: Reveal Door\n" +
                "12: Start Round\n" +
                "13: Start Turn\n" +
                "14: Split Rooms\n" +
                "15: Step\n" +
                "16: Stun Teacher\n" +
                "17: Teleport\n" +
                "18: Win Game\n" +
                "Choose one:");

        int numberOfScript = scanner.nextInt();
        switch (numberOfScript) {
            case 1: ScenarioBlockPoison(); break;
            case 2: ScenarioBlockSoulDrain(); break;
            case 3: ScenarioDrainSoul(); break;
            case 4: ScenarioHideDoor(); break;
            case 5: ScenarioLoseGame(); break;
            case 6: ScenarioMergeRooms(); break;
            case 7: ScenarioPickupItem(); break;
            case 8: ScenarioPlaceItem(); break;
            case 9: ScenarioPoisonEntities(); break;
            case 10: ScenarioPoisonateRoom(); break;
            case 11: ScenarioRevealDoor(); break;
            case 12: ScenarioStartRound(); break;
            case 13: ScenarioStartTurn(); break;
            case 14: ScenarioSplitRooms(); break;
            case 15: ScenarioStep(); break;
            case 16: ScenarioStunTeacher(); break;
            case 17: ScenarioTeleport(); break;
            case 18: ScenarioWinGame(); break;
            default:
                System.out.println("There was an error! That script number is invalid!");
                break;
        }

        scanner.nextLine();
        scanner.nextLine();
        script();
    }


}