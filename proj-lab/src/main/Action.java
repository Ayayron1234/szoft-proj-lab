package main;

public interface Action {
    /**
     * This method is to execute an action
     * @param target who is the entity the action executed on
     */
    void Execute(Entity target);
}
