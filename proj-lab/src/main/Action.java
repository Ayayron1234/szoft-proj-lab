package main;


/**
 * The Action interface represents an action that can be executed on an entity.
 * The types of actions that can be performed on the entities are the implementations of this interface.
 */
public interface Action {
    /**
     * This method is to execute an action
     *
     * @param target who is the entity the action executed on
     */
    void Execute(Entity target);
}
