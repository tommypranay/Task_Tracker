package org.tommy.base;

import org.tommy.TaskManager;

public interface OperationMode {
    /**
     * Represents a mode of operation for the TaskTracker application,
     * such as CLI mode or API mode.
     *
     * Implementations define how task operations are initiated and controlled,
     * depending on the input/output interface (e.g., console or REST API).
     */

    /**
     * Starts the execution flow for the selected operation mode.
     *
     * @param taskManager The {@link TaskManager} instance used to perform task operations.
     */
    void start(TaskManager taskManager);

    /**
     * Gracefully ends or shuts down the operation mode.
     */
    void end();
}
