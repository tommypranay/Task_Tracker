package org.tommy;

import org.tommy.exception.PersistenceException;
import org.tommy.models.Task;
import org.tommy.persist.TaskSaver;

import java.util.List;

/**
 * The TaskManager acts as a service layer that delegates task operations
 * (such as add, update, delete, fetch) to the underlying TaskSaver implementation.
 * It abstracts persistence logic from the rest of the application.
 */
public class TaskManager {

    private final TaskSaver taskSaver;

    /**
     * Constructs a TaskManager with the given TaskSaver.
     *
     * @param taskSaver the persistence handler for tasks.
     */
    public TaskManager(TaskSaver taskSaver) {
        this.taskSaver = taskSaver;
    }

    /**
     * Adds a new task.
     *
     * @param T the task to add.
     * @return the ID of the newly added task.
     * @throws PersistenceException if writing to storage fails.
     */
    public int addTask(Task T) throws PersistenceException {
        Task task = taskSaver.addTask(T);
        return task.getId();
    }

    /**
     * Updates an existing task.
     *
     * @param id   the ID of the task to update.
     * @param T the task object with updated fields.
     * @return the ID of the updated task.
     * @throws PersistenceException if update fails.
     */
    public int updateTask(int id, Task T) throws PersistenceException{
        Task task = taskSaver.updateTask(T);
        return task.getId();
    }

    /**
     * Deletes a task by ID.
     *
     * @param taskId the ID of the task to delete.
     * @return the ID of the deleted task.
     */
    public int DeleteTask(int taskId){
        return taskSaver.deleteTask(taskId);
    }

    /**
     * Placeholder: Deletes a task by object. Not implemented.
     *
     * @param T the task object to delete.
     * @return 0 always, not implemented.
     */
    public int DeleteTask(Task T){
        return DeleteTask(T.getId());
    }

    /**
     * Retrieves a task by ID.
     *
     * @param id the task ID.
     * @return the corresponding Task.
     */

    /**
     * Retrieves all tasks.
     *
     * @return list of tasks.
     */
    public Task getTask(int id){
        return taskSaver.getTask(id);
    }


    public List<Task> getTasks(){
        return taskSaver.getTasks();
    }
}
