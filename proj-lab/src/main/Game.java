package main;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    Timer               timer = new Timer();
    int                 roundsLeft = 4;
    int                 roundNumber = 0;

    ArrayList<Room>     rooms = new ArrayList<>();
    ArrayList<Entity>   entities = new ArrayList<>();

    private int         nextUidEntity = 0;
    private int         nextUidRoom = 0;
    private int         nextUidItem = 0;

    public Game(int roundsLeft) {
        this.roundsLeft = roundsLeft;
    }

    public void RemoveEntity(Entity entity) {
        entities.remove(entity);
        timer.Unsubscribe(entity);

        if (entities.isEmpty())
            End(false);
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

    public Student CreateStudent(int uid) {
        if (uid >= nextUidEntity)
            nextUidEntity = uid + 1;

        Student student = new Student(uid, this);

        entities.add(student);
        timer.Subscribe(student);

        return student;
    }

    public Student CreateStudent(String name) {
        Student student = CreateStudent(nextUidEntity);
        ++nextUidEntity;

        student.SetUniqueName(name);
        entities.add(student);
        timer.Subscribe(student);

        return student;
    }

    public Teacher CreateTeacher(int uid) {
        if (uid >= nextUidEntity)
            nextUidEntity = uid + 1;

        Teacher teacher = new Teacher(uid, this);

        entities.add(teacher);
        timer.Subscribe(teacher);

        return teacher;
    }

    public Teacher CreateTeacher() {
        Teacher teacher = CreateTeacher(nextUidEntity);
        ++nextUidEntity;

        return teacher;
    }

    private void InitRooms() {
        // TODO: implement room generation
    }

    private void InitStudents(int count) {
        // TODO: decide in which room to spawn the student in
    }

    private void InitTeachers(int count) {
        // TODO: decide in which room to spawn the teacher in
    }

    public void Start() {
        while(roundsLeft > 0) {
            MainLoop();
        }

        End(false);
    }

    public void End(boolean victory){
        if (victory) {
            System.out.println("victory");
        }
        else {
            System.out.println("game over");
        }
    }

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
