package main;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    int roundsLeft = 4;
    int roundNumber = 0;

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Entity> entities = new ArrayList<>();

    Timer timer = new Timer();

    public void InitRooms() {
        Room room1 = new Room(5);
        Room room2 = new Room(3);

        rooms.add(room1);
        rooms.add(room2);

        // TODO: implement room generation
    }

    public void InitStudents(int count) {
        for (int i = 0; i < count; ++i) {
            Student player = new Student();
            timer.Subscribe(player);
            entities.add(player);

            // TODO: decide in which room to spawn the student in
            player.Teleport(rooms.get(0));
        }
    }

    public void InitTeachers(int count) {
        for (int i = 0; i < count; ++i) {
            Teacher teacher = new Teacher();
            timer.Subscribe(teacher);
            entities.add(teacher);

            // TODO: decide in which room to spawn the teacher in
            teacher.Teleport(rooms.get(1));
        }
    }

    public void Start() {
        System.out.println("Game.Start()");

        Room starterRoom = new Room(5);
        timer.Subscribe(starterRoom);

        while(roundsLeft > 0) {
            MainLoop();
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
