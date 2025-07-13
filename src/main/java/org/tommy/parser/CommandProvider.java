package org.tommy.parser;

public interface CommandProvider {

    /**
     * Represents a source of user commands for the Task Tracker application.
     *
     * Implementations of this interface provide input commands from various sources,
     * such as the console, files, or remote APIs.
     */

    boolean hasNextCommand();
    String getNextCommand();
    void close();
}
