package org.tommy.base;

public enum Status {

    /**
     * Represents the status of a task in the task tracker system.
     *
     * The status can be:
     * - {@code NOT_DONE}: Task is not started yet.
     * - {@code IN_PROGRESS}: Task is currently being worked on.
     * - {@code DONE}: Task has been completed.
     */

    NOT_DONE("NOT DONE"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");

    private final String status;

    /**
     * Constructs a status with a human-readable string representation.
     *
     * @param status The display string for the status.
     */
    Status(String status) {
        this.status =  status;
    }

    /**
     * Returns the human-readable string associated with the status.
     *
     * @return Display string for the status.
     */
    public String getStatus() {
        return status;
    }
}
