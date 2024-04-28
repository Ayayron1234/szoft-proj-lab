package main;

public class Student extends Entity {
    private String uniqueName;

    public Student(int uid, Game game) {
        super(uid, game);
    }

    public void SetUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    @Override
    public void HandleTurn() {

    }

    @Override
    public String GetName() {
        return String.format("%s", uniqueName);
    }
}
