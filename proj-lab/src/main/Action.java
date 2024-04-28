package main;

/**
 * This method is to execute an action
 * @param target who is the entity the action executed on
 */
public interface Action {
    void Execute(Entity target);
}
