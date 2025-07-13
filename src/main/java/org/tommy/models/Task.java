package org.tommy.models;

import org.apache.commons.lang3.StringUtils;
import org.tommy.base.Status;

import java.time.LocalDateTime;

public class Task {

    /**
     * Represents a task in the task tracker application.
     * Each task has a unique ID, description, status, and timestamps indicating creation and last update time.
     */


    /** Unique identifier for the task. */
    private final int id;

    /** Current status of the task (e.g., NOT_DONE, IN_PROGRESS, DONE). */
    private Status status;

    /** Description of the task. */
    private String task;

    /** Timestamp indicating when the task was created. */
    private LocalDateTime createdAt;

    /** Timestamp indicating when the task was last updated. */
    private LocalDateTime updatedAt;

    /**
     * Constructs a new Task with only an ID.
     * Used when ID is known but other attributes will be set later.
     *
     * @param id The unique identifier for the task.
     */
    public Task(int id){
        this.id = id;
    }

    /**
     * Constructs a Task with ID, status, and description.
     *
     * @param id     The unique identifier.
     * @param status The status of the task.
     * @param task   The task description.
     */
    public Task(int id, Status status, String task) {
        this.id = id;
        this.status = status;
        this.task = task;
    }

    /**
     * Constructs a Task with a description and ID.
     *
     * @param task The task description.
     * @param id   The unique identifier.
     */
    public Task(String task, int id) {
        this.task = task;
        this.id = id;
    }

    /**
     * Returns the unique ID of the task.
     *
     * @return Task ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the current status of the task.
     *
     * @return Task status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The new status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets the task description.
     *
     * @param task The new task description.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Returns the creation timestamp.
     *
     * @return Creation time.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt Time of creation.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the last updated timestamp.
     *
     * @return Last update time.
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last updated timestamp.
     *
     * @param updatedAt Time of last update.
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return this.id+" "+ StringUtils.wrap(task,"\"");
    }

    /**
     * Compares tasks based on their ID.
     *
     * @param o The object to compare with.
     * @return {@code true} if both tasks have the same ID.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    /**
     * Returns a hash code based on the task ID.
     *
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return id;
    }
}
