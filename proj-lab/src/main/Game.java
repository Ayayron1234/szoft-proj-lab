package main;

import main.roomabilities.PoisonAbility;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Manages the state and logic of the game, including rounds, entities (students and teachers),
 * and rooms. This class is responsible for initializing the game environment, starting the game,
 * and handling the progression of rounds until the game ends either through victory or defeat.
 */
public class Game {
    /**
     * Removes an entity from the game and unsubscribes it from the timer. If no entities remain,
     * the game ends in defeat.
     *
     * @param entity The entity to remove from the game.
     */
    public void RemoveEntity(Entity entity) {
        System.out.println("Game.RemoveEntity");

        System.out.println("Was this the last student in the game?\n 1-yes 2-no");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equals("1")) {
            End(false);
        }
    }

    /**
     * Creates a new student entity, adds it to the game, and subscribes it to the timer.
     *
     * @param name The name of the student.
     * @return The created student entity.
     */
    public Student CreateStudent(String name) {
        System.out.println("Game.CreateStudent");
        Student student = new Student(name, this);
        return student;
    }

    /**
     * Creates a new teacher entity, adds it to the game, and subscribes it to the timer.
     *
     * @return The created teacher entity.
     */
    public Teacher CreateTeacher() {
        System.out.println("Game.CreateTeacher");
        Teacher teacher = new Teacher(this);
        return teacher;
    }

    /**
     * Creates a room with a specified capacity and adds a poison ability to it.
     *
     * @param capacity The maximum number of entities the room can hold.
     * @return The created room with a poison ability.
     */
    public Room CreatePoisonedRoom(int capacity) {
        System.out.println("Game.CreatePoisonedRoom");
        Room room = new Room(capacity);
        room.AddAbility(new PoisonAbility());
        return room;
    }

    /**
     * Initializes the rooms in the game. Additional implementation required for
     * generating rooms beyond the initial setup.
     */
    private void InitRooms() {
        System.out.println("Game.InitRooms");
    }

    /**
     * Initializes the student entities in the game and assigns them to rooms.
     *
     * @param count The number of students to create and initialize.
     */
    private void InitStudents(int count) {
        System.out.println("Game.InitStudents");
    }

    /**
     * Initializes the teacher entities in the game and assigns them to rooms.
     *
     * @param count The number of teachers to create and initialize.
     */
    private void InitTeachers(int count) {
        System.out.println("Game.InitTeachers");
    }

    /**
     * Starts the game by initializing the game environment and entering the main game loop.
     * Continues until all rounds are completed or the game ends.
     */
    public void Start() {
        System.out.println("Game.Start");
    }

    /**
     * Ends the game with a specified outcome.
     *
     * @param victory True if the game ends in victory, false otherwise.
     */
    public void End(boolean victory){
        System.out.printf("Game.End\n");
    }
/**
 * Executes a single round of the game. This includes starting the round, executing turns for
 * each entity, and then ending  the round. It processes all entities' actions, increments the round number, and decrements the rounds left.
 **/
    private void MainLoop() {
        System.out.println("Game.MainLoop");
        TimerEvent timerEvent = new TimerEvent(0, 0, 0);

        Timer timer = new Timer();
        timer.StartRound(timerEvent);

        System.out.println("For entity in entities");

        Student student = new Student("");

        timer.StartTurn(student, timerEvent);
        timer.EndTurn(student, timerEvent);
        timerEvent.IncreaseTurnCounter();

        timer.EndRound(timerEvent);
    }
}
