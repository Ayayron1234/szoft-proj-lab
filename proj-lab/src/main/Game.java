package main;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
=======
<<<<<<< Updated upstream
import main.roomabilities.PoisonAbility;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
=======
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.itemtypes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;
>>>>>>> Stashed changes
>>>>>>> Stashed changes

/**
 * Manages the state and logic of the game, including rounds, entities (students and teachers),
 * and rooms. This class is responsible for initializing the game environment, starting the game,
 * and handling the progression of rounds until the game ends either through victory or defeat.
 */
public class Game {
<<<<<<< Updated upstream
    Timer               timer = new Timer();
    int                 roundsLeft = 4;
    int                 roundNumber = 0;
=======
<<<<<<< Updated upstream
=======
    Timer               timer = new Timer();
    int                 roundsLeft = 4;
    int                 roundNumber = 0;
    boolean             shouldExit = false;
>>>>>>> Stashed changes

    ArrayList<Room>     rooms = new ArrayList<>();
    ArrayList<Entity>   entities = new ArrayList<>();

    private int         nextUidEntity = 0;
    private int         nextUidRoom = 0;
    private int         nextUidItem = 0;
<<<<<<< Updated upstream

    public Game() { }

    public Game(int roundsLeft) {
        this.roundsLeft = roundsLeft;
    }
=======
    ArrayList<Entity>   mainLoopEntities = new ArrayList<>();
>>>>>>> Stashed changes

    Scanner scanner = new Scanner(System.in);

    public Game() { }

    /**
     * Constructor of the Game class with 1 parameter
     *
     * @param roundsLeft the max number of rounds that will be played in this game.
     */
    public Game(int roundsLeft) {
        this.roundsLeft = roundsLeft;
    }

>>>>>>> Stashed changes
    /**
     * Removes an entity from the game and unsubscribes it from the timer. If no entities remain,
     * the game ends in defeat.
     *
     * @param entity The entity to remove from the game.
     */
    public void RemoveEntity(Entity entity) {
<<<<<<< Updated upstream
        entities.remove(entity);
        timer.Unsubscribe(entity);

        if (entities.isEmpty())
=======
        System.out.println("Game.RemoveEntity");

<<<<<<< Updated upstream
        System.out.println("Was this the last student in the game?\n 1-yes 2-no");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equals("1")) {
>>>>>>> Stashed changes
            End(false);
    }

    private HashMap<String, Object> deserializedObjects = new HashMap<>();

    public <T> T GetOrCreateDeserializedObjectReference(String id, Callable<T> objectFactory) {
        if (deserializedObjects.containsKey(id))
            return (T)deserializedObjects.get(id);

        Object obj;
        try {
            obj = objectFactory.call();
            deserializedObjects.put(id, obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
<<<<<<< Updated upstream
        return (T)obj;
    }

=======
=======
        boolean _hasstudent = false;
        if(entities.isEmpty()) return;
        for (Entity e : entities) {
            if(e.getClass().getSimpleName().equals("Student")) {
                _hasstudent = true;
            }
        }

        if (!_hasstudent) {
            shouldExit = true;
            End(false);
        }
    }

    private HashMap<String, Object> deserializedObjects = new HashMap<>();

    public <T> T GetOrCreateDeserializedObjectReference(String id, Callable<T> objectFactory) {
        if (deserializedObjects.containsKey(id))
            return (T)deserializedObjects.get(id);

        Object obj;
        try {
            obj = objectFactory.call();
            deserializedObjects.put(id, obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T)obj;
    }

    Scanner GetScanner() {
        return scanner;
    }

>>>>>>> Stashed changes
    public <T> T GetDeserializedObjectReference(String id) {
        if (deserializedObjects.containsKey(id))
            return (T)deserializedObjects.get(id);

        throw new RuntimeException("Object is not deserialized yet. ");
    }

<<<<<<< Updated upstream

    public Entity CreateEntity(String typeString, int uid) {
        if (typeString.equals(Student.class.getName()))
            return new Student(uid, this);

        if (typeString.equals(Teacher.class.getName()))
            return new Teacher(uid, this);

        if (typeString.equals(Cleaner.class.getName()))
            return new Cleaner(uid, this);
=======
    public void Exit() {
        shouldExit = true;
    }

    /**
     * You can create a single Entity of any type with it.
     *
     * @param typeString is the class type of the newly created Entity
     * @param uid int, the unique identifier of the newly created Entity
     * @return the newly created Entity
     */
    public Entity CreateEntity(String typeString, int uid) {
        if (typeString.equals(GetSimplifiedClassName(Student.class)))
            return CreateStudent(uid);

        if (typeString.equals(GetSimplifiedClassName(Teacher.class)))
            return CreateTeacher(uid);

        if (typeString.equals(GetSimplifiedClassName(Cleaner.class)))
            return CreateCleaner(uid);
>>>>>>> Stashed changes

        throw new RuntimeException("Entity type not recognized. ");
    }

<<<<<<< Updated upstream
=======
    private String GetSimplifiedClassName(Class clazz) {
        String[] name = clazz.getName().split("\\.");
        return name[name.length - 1];
    }

    /**
     * You can create a single Item of any type with it.
     *
     * @param typeString is the class type of the newly created Item
     * @return the newly created Item
     */
    public Item CreateItem(String typeString) {
        Item item = CreateItemImpl(typeString);
        timer.Subscribe(item);
        return item;
    }

    private Item CreateItemImpl(String typeString) {
        if (typeString.equals(GetSimplifiedClassName(AirFreshener.class)))
            return new AirFreshener();

        if (typeString.equals(GetSimplifiedClassName(Beer.class)))
            return new Beer();

        if (typeString.equals(GetSimplifiedClassName(Camembert.class)))
            return new Camembert();

        if (typeString.equals(GetSimplifiedClassName(Duster.class)))
            return new Duster();

        if (typeString.equals(GetSimplifiedClassName(Mask.class)))
            return new Mask();

        if (typeString.equals(GetSimplifiedClassName(SlideRule.class)))
            return new SlideRule(this);

        if (typeString.equals(GetSimplifiedClassName(Transistor.class)))
            return new Transistor();

        if (typeString.equals(GetSimplifiedClassName(TVSZ.class)))
            return new TVSZ();

        throw new RuntimeException(String.format("Item type: %s not recognized. ", typeString));
    }

    /**
     * You can create a single Room with it.
     *
     * @param uid int, the unique identifier of the newly created Room
     * @param capacity int, the maximum capacity of the newly created Room
     * @return the newly created Room
     */
>>>>>>> Stashed changes
    public Room CreateRoom(int uid, int capacity) {
        if (uid >= nextUidRoom)
            nextUidRoom = uid + 1;

        Room room = new Room(uid, capacity);

        rooms.add(room);
        timer.Subscribe(room);

        return room;
    }

<<<<<<< Updated upstream
=======
    /**
     * You can create a single Room with it.
     * (Without you have to worried about adding a unique uid parameter)
     *
     * @param capacity int, the maximum capacity of the newly created Room
     * @return the newly created Room
     */
>>>>>>> Stashed changes
    public Room CreateRoom(int capacity) {
        Room room = CreateRoom(nextUidRoom, capacity);
        ++nextUidRoom;

        return room;
    }

    public Item HandleItem(Item item, int uid) {
        if (uid >= nextUidItem)
            nextUidItem = uid + 1;

        item.SetUID(uid);
        timer.Subscribe(item);

        return item;
    }

    public Item HandleItem(Item item) {
        HandleItem(item, nextUidItem);
        ++nextUidItem;

        return item;
    }

    /**
     * Creates a new student entity, adds it to the game, and subscribes it to the timer.
     *
     * @param uid The unique identifier of the student entity.
     * @return The created student entity.
     */
    public Student CreateStudent(int uid) {
        if (uid >= nextUidEntity)
            nextUidEntity = uid + 1;

        Student student = new Student(uid, this);

        entities.add(student);
        timer.Subscribe(student);

        return student;
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    /**
     * Creates a new student entity, adds it to the game, and subscribes it to the timer.
     *
     * @param name The name of the student.
     * @return The created student entity.
     */
    public Student CreateStudent(String name) {
<<<<<<< Updated upstream
        Student student = CreateStudent(nextUidEntity);
        ++nextUidEntity;

        student.SetUniqueName(name);
        entities.add(student);
        timer.Subscribe(student);

=======
        System.out.println("Game.CreateStudent");
        Student student = new Student(name, this);
>>>>>>> Stashed changes
        return student;
    }

    /**
     * Creates a new teacher entity, adds it to the game, and subscribes it to the timer.
     *
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
     * @param uid The unique identifier of the teacher entity.
     * @return The created teacher entity.
     */
    public Teacher CreateTeacher(int uid) {
        if (uid >= nextUidEntity)
            nextUidEntity = uid + 1;

        Teacher teacher = new Teacher(uid, this);

        entities.add(teacher);
        timer.Subscribe(teacher);

        return teacher;
    }

<<<<<<< Updated upstream
    /**
     * Creates a new teacher entity, adds it to the game, and subscribes it to the timer.
     *
     * @return The created teacher entity.
     */
    public Teacher CreateTeacher() {
        Teacher teacher = CreateTeacher(nextUidEntity);
        ++nextUidEntity;

=======
    public Cleaner CreateCleaner(int uid) {
        if (uid >= nextUidEntity)
            nextUidEntity = uid + 1;

        Cleaner cleaner = new Cleaner(uid, this);

        entities.add(cleaner);
        timer.Subscribe(cleaner);

        return cleaner;
    }

    /**
     * Creates a new teacher entity, adds it to the game, and subscribes it to the timer.
     *
>>>>>>> Stashed changes
     * @return The created teacher entity.
     */
    public Teacher CreateTeacher() {
        System.out.println("Game.CreateTeacher");
        Teacher teacher = new Teacher(this);
>>>>>>> Stashed changes
        return teacher;
    }

    /**
     * Initializes the rooms in the game.
     */
    private void InitRooms() {
<<<<<<< Updated upstream
        // TODO: implement room generation
=======
        System.out.println("Game.InitRooms");
>>>>>>> Stashed changes
    }

    /**
     * Initializes the student entities in the game and assigns them to rooms.
     *
     * @param count The number of students to create and initialize.
     */
    private void InitStudents(int count) {
<<<<<<< Updated upstream
        // TODO: decide in which room to spawn the student in
=======
        System.out.println("Game.InitStudents");
>>>>>>> Stashed changes
    }

    /**
     * Initializes the teacher entities in the game and assigns them to rooms.
     *
     * @param count The number of teachers to create and initialize.
     */
    private void InitTeachers(int count) {
<<<<<<< Updated upstream
        // TODO: decide in which room to spawn the teacher in
=======
        System.out.println("Game.InitTeachers");
>>>>>>> Stashed changes
    }

    /**
     * Starts the game by initializing the game environment and entering the main game loop.
     * Continues until all rounds are completed or the game ends.
     */
    public void Start() {
<<<<<<< Updated upstream
        while(roundsLeft > 0) {
            MainLoop();
        }

        End(false);
=======
<<<<<<< Updated upstream
        System.out.println("Game.Start");
=======
        while(roundsLeft > 0 && !shouldExit) {
            MainLoop();
        }

        if (!shouldExit)
            End(false);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    /**
     * Ends the game with a specified outcome.
     *
     * @param victory True if the game ends in victory, false otherwise.
     */
    public void End(boolean victory){
<<<<<<< Updated upstream
        if (victory) {
            System.out.println("victory");
        }
        else {
            System.out.println("game over");
        }
=======
<<<<<<< Updated upstream
        System.out.printf("Game.End\n");
=======
        if (victory) {
            System.out.printf("victory\n");
        }
        else {
            System.out.printf("game over\n");
        }
        shouldExit = true;
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    /**
     * Executes a single round of the game. This includes starting the round, executing turns for
     * each entity, and then ending  the round. It processes all entities' actions, increments the round number, and decrements the rounds left.
     **/
    private void MainLoop() {
<<<<<<< Updated upstream
        TimerEvent timerEvent = new TimerEvent(roundNumber, roundsLeft, 0);
=======
        System.out.println("Game.MainLoop");
        TimerEvent timerEvent = new TimerEvent(0, 0, 0);
>>>>>>> Stashed changes

        Timer timer = new Timer();
        timer.StartRound(timerEvent);

<<<<<<< Updated upstream
        for (Entity entity : entities) {
            timer.StartTurn(entity, timerEvent);
            timer.EndTurn(entity, timerEvent);
            timerEvent.IncreseTurnCouner();
=======
<<<<<<< Updated upstream
        System.out.println("For entity in entities");

        Student student = new Student("");

        timer.StartTurn(student, timerEvent);
        timer.EndTurn(student, timerEvent);
        timerEvent.IncreaseTurnCounter();
=======
        mainLoopEntities.clear();
        mainLoopEntities.addAll(entities);

        if(!entities.isEmpty()) {
            for (Entity entity : mainLoopEntities) {
                if (shouldExit)
                    return;

                timer.StartTurn(entity, timerEvent);
                timer.EndTurn(entity, timerEvent);
                timerEvent.IncreseTurnCouner();
            }
>>>>>>> Stashed changes
        }
>>>>>>> Stashed changes

        timer.EndRound(timerEvent);
    }

    public static Game Deserialize(JsonObject json) {
        Game game = new Game();

        JsonObject gameJson = json.get("game").getAsJsonObject();
        game.roundsLeft = gameJson.get("roundsLeft").getAsInt();

        // Deserialize protections
        if (json.has("protections")) {
            JsonArray protectionsArray = json.get("protections").getAsJsonArray();
            for (int i = 0; i < protectionsArray.size(); ++i) {
                JsonObject protectionJson = protectionsArray.get(i).getAsJsonObject();
                Protection protection = Protection.Deserialize(game, protectionJson);
                game.deserializedObjects.put(protectionJson.get("id").getAsString(), protection);
            }
        }

        // Deserialize items
        if (json.has("items")) {
            JsonArray itemsArrayJson = json.get("items").getAsJsonArray();
            for (int i = 0; i < itemsArrayJson.size(); ++i) {
                JsonObject itemJson = itemsArrayJson.get(i).getAsJsonObject();
                Item item = Item.Deserialize(game, itemJson);
                game.deserializedObjects.put(itemJson.get("id").getAsString(), item);
            }
        }

        // Deserialize entities
        JsonArray entitiesArrayJson = json.get("entities").getAsJsonArray();
        for (int i = 0; i < entitiesArrayJson.size(); ++i) {
            JsonObject entityJson = entitiesArrayJson.get(i).getAsJsonObject();
            Entity entity = Entity.Deserialize(game, entityJson);
            game.deserializedObjects.put(entityJson.get("id").getAsString(), entity);
        }

        // Deserialize rooms
        JsonArray roomsArrayJson = json.get("rooms").getAsJsonArray();
        for (int i = 0; i < roomsArrayJson.size(); ++i) {
            JsonObject roomJson = roomsArrayJson.get(i).getAsJsonObject();
            Room room = Room.Deserialize(game, roomJson);
            game.deserializedObjects.put(roomJson.get("id").getAsString(), room);
        }

        return game;
    }
}
