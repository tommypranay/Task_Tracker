package org.tommy.base;

import org.tommy.TaskManager;

public class ApiMode implements OperationMode{

    /**
     * ApiMode class enables TaskTracker to run in API-driven mode.
     *
     * This mode is intended for scenarios where tasks are managed via
     * RESTful APIs or external HTTP-based systems, rather than through
     * the command-line interface or file-based input.
     *
     * Typical usage includes integrating with web servers,
     * mobile apps, or microservices where task operations such as
     * add, update, delete, and list are exposed over HTTP.
     *
     * Note: This class serves as a placeholder for future API integration
     * and does not currently implement active endpoints.
     */

    @Override
    public void start(TaskManager taskManager) {
        // yet To be implemented
        throw new UnsupportedOperationException("This feature is not implemented yet.");
    }

    @Override
    public void end() {
        // yet to be implemented
        throw new UnsupportedOperationException("This feature is not implemented yet.");
    }
}
