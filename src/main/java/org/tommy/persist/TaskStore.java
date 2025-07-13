package org.tommy.persist;

import org.tommy.models.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the in-memory store of all tasks along with the next available task ID.
 * Acts as a data container for serialization and deserialization.
 */
public class TaskStore {
    private int nextId;
    private Set<Task> tasks;

    public TaskStore() {}

    /**
     * Constructs a TaskStore with a given next ID and list of tasks.
     *
     * @param nextId the next task ID to assign.
     * @param tasks  the initial list of tasks.
     */
    public TaskStore(int nextId, List<Task> tasks) {
        this.nextId = nextId;
        this.tasks = new HashSet<>(tasks);
    }

    /**
     * Returns the next available task ID.
     *
     * @return the next ID.
     */
    public int getNextId() {
        return nextId;
    }

    /**
     * Sets the next task ID.
     *
     * @param nextId the ID to be set.
     */
    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    /**
     * Returns a list of all tasks.
     *
     * @return list of tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Sets the task list from a given list.
     *
     * @param tasks the new task list.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = new HashSet<>(tasks);
    }

    /**
     * Adds a task to the store.
     *
     * @param task the task to add.
     * @return true if the task was added successfully, false if it was a duplicate.
     */
    public boolean addTask(Task task){
        tasks.add(task);
        return tasks.contains(task);
    }

    public int deleteTask(Task task){
        tasks.remove(task);
        return task.getId();
    }
}
