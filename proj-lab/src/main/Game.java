package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Manages the state and logic of the game, including rounds, entities (students and teachers),
 * and rooms. This class is responsible for initializing the game environment, starting the game,
 * and handling the progression of rounds until the game ends either through victory or defeat.
 */
public class Game {
    Timer               timer = new Timer();
    int                 roundsLeft = 4;
    int                 roundNumber = 0;

    ArrayList<Room>     rooms = new ArrayList<>();
    ArrayList<Entity>   entities = new ArrayList<>();

    private int         nextUidEntity = 0;
    private int         nextUidRoom = 0;
    private int         nextUidItem = 0;

    public Game() { }

    public Game(int roundsLeft) {
        this.roundsLeft = roundsLeft;
    }

    /**
     * Removes an entity from the game and unsubscribes it from the timer. If no entities remain,
     * the game ends in defeat.
     *
     * @param entity The entity to remove from the game.
     */
    public void RemoveEntity(Entity entity) {
        entities.remove(entity);
        timer.Unsubscribe(entity);

        if (entities.isEmpty())
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
        return (T)obj;
    }

    public <T> T GetDeserializedObjectReference(String id) {
        if (deserializedObjects.containsKey(id))
            return (T)deserializedObjects.get(id);

        throw new RuntimeException("Object is not deserialized yet. ");
    }


    public Entity CreateEntity(String typeString, int uid) {
        if (typeString.equals(Student.class.getName()))
            return new Student(uid, this);

        if (typeString.equals(Teacher.class.getName()))
            return new Teacher(uid, this);

        if (typeString.equals(Cleaner.class.getName()))
            return new Cleaner(uid, this);

        throw new RuntimeException("Entity type not recognized. ");
    }

    public Room CreateRoom(int uid, int capacity) {
        if (uid >= nextUidRoom)
            nextUidRoom = uid + 1;

        Room room = new Room(uid, capacity);

        rooms.add(room);
        timer.Subscribe(room);

        return room;
    }

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
    }

    /**
     * Creates a new student entity, adds it to the game, and subscribes it to the timer.
     *
     * @param name The name of the student.
     * @return The created student entity.
     */
    public Student CreateStudent(String name) {
        Student student = CreateStudent(nextUidEntity);
        ++nextUidEntity;

        student.SetUniqueName(name);
        entities.add(student);
        timer.Subscribe(student);

        return student;
    }

    /**
     * Creates a new teacher entity, adds it to the game, and subscribes it to the timer.
     *
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

    /**
     * Creates a new teacher entity, adds it to the game, and subscribes it to the timer.
     *
     * @return The created teacher entity.
     */
    public Teacher CreateTeacher() {
        Teacher teacher = CreateTeacher(nextUidEntity);
        ++nextUidEntity;

        return teacher;
    }

    /**
     * Initializes the rooms in the game.
     */
    private void InitRooms() {
        // TODO: implement room generation
    }

    /**
     * Initializes the student entities in the game and assigns them to rooms.
     *
     * @param count The number of students to create and initialize.
     */
    private void InitStudents(int count) {
        // TODO: decide in which room to spawn the student in
    }

    /**
     * Initializes the teacher entities in the game and assigns them to rooms.
     *
     * @param count The number of teachers to create and initialize.
     */
    private void InitTeachers(int count) {
        // TODO: decide in which room to spawn the teacher in
    }

    /**
     * Starts the game by initializing the game environment and entering the main game loop.
     * Continues until all rounds are completed or the game ends.
     */
    public void Start() {
        while(roundsLeft > 0) {
            MainLoop();
        }

        End(false);
    }

    /**
     * Ends the game with a specified outcome.
     *
     * @param victory True if the game ends in victory, false otherwise.
     */
    public void End(boolean victory){
        if (victory) {
            System.out.println("victory");
        }
        else {
            System.out.println("game over");
        }
    }

    /**
     * Executes a single round of the game. This includes starting the round, executing turns for
     * each entity, and then ending  the round. It processes all entities' actions, increments the round number, and decrements the rounds left.
     **/
    private void MainLoop() {
        TimerEvent timerEvent = new TimerEvent(roundNumber, roundsLeft, 0);

        timer.StartRound(timerEvent);

        for (Entity entity : entities) {
            timer.StartTurn(entity, timerEvent);
            timer.EndTurn(entity, timerEvent);
            timerEvent.IncreseTurnCouner();
        }

        timer.EndRound(timerEvent);

        ++roundNumber;
        --roundsLeft;
    }
}
