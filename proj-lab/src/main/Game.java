package main;

import main.roomabilities.PoisonAbility;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    int roundsLeft = 4;
    int roundNumber = 0;

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Entity> entities = new ArrayList<>();

    Timer timer = new Timer();

    public void RemoveEntity(Entity entity) {
        entities.remove(entity);
        timer.Unsubscribe(entity);

        if (entities.isEmpty())
            End(false);
    }

    public Student CreateStudent(String name) {
        Student student = new Student(name, this);
        entities.add(student);
        timer.Subscribe(student);
        return student;
    }

    public Teacher CreateTeacher() {
        Teacher teacher = new Teacher(this);
        entities.add(teacher);
        timer.Subscribe(teacher);
        return teacher;
    }

    public Room CreatePoisonedRoom(int capacity) {
        Room room = new Room(capacity);
        room.AddAbility(new PoisonAbility());
        return room;
    }

    private void InitRooms() {
        Room room1 = new Room(5);
        Room room2 = new Room(3);

        rooms.add(room1);
        rooms.add(room2);

        // TODO: implement room generation
    }

    private void InitStudents(int count) {
        for (int i = 0; i < count; ++i) {
            Student player = CreateStudent(Integer.toString(i));

            // TODO: decide in which room to spawn the student in
            player.Teleport(rooms.get(0));
        }
    }

    private void InitTeachers(int count) {
        for (int i = 0; i < count; ++i) {
            Teacher teacher = CreateTeacher();

            // TODO: decide in which room to spawn the teacher in
            teacher.Teleport(rooms.get(1));
        }
    }

    public void Start() {
        System.out.println("Game.Start()");

        Room starterRoom = new Room(5);
        timer.Subscribe(starterRoom);

        InitRooms();
        InitStudents(2);
        InitTeachers(3);

        while(roundsLeft > 0) {
            MainLoop();
        }
    }

    public void End(boolean victory){
        System.out.printf("Game.End(%b)", victory);

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
