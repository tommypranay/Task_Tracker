package org.tommy.persist;

import org.tommy.exception.PersistenceException;
import org.tommy.models.Task;

import java.util.List;

/**
 * Interface defining persistence operations for saving, updating, retrieving, and deleting tasks.
 *
 * Implementations can persist tasks in various formats such as JSON files, databases, or remote services.
 */
public interface TaskSaver {

    /**
     * Adds a new task to the persistence store.
     *
     * @param task The task to be added.
     * @return The added task with updated details (e.g., assigned ID).
     * @throws PersistenceException If an error occurs while saving.
     */
    public Task addTask(Task task) throws PersistenceException;

    /**
     * Deletes the task with the given ID.
     *
     * @param taskId The ID of the task to be deleted.
     * @return The ID of the deleted task.
     * @throws PersistenceException If deletion fails or task is not found.
     */
    public int deleteTask(int taskId) throws PersistenceException;

    /**
     * Updates the provided task in the persistence store.
     *
     * @param task The task with updated details.
     * @return The updated task.
     * @throws PersistenceException If the update fails.
     */
    public Task updateTask(Task task) throws PersistenceException;

    /**
     * Retrieves the task with the specified ID.
     *
     * @param id The ID of the task to retrieve.
     * @return The corresponding task, or {@code null} if not found.
     */
    public Task getTask(int id);

    /**
     * Retrieves all tasks from the persistence store.
     *
     * @return A list of all stored tasks.
     */
    public List<Task> getTasks();

}
