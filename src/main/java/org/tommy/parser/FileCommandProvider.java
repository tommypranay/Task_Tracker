package org.tommy.parser;

/**
 * A placeholder implementation of {@link CommandProvider} for reading commands from a file.
 *
 * This class is not yet implemented and currently returns default values.
 * Intended for future extension to support batch execution of commands from a file.
 */
public class FileCommandProvider implements CommandProvider{

    /**
     * Indicates whether there are more commands available.
     *
     * @return {@code false}, since this implementation is not yet complete.
     */
    @Override
    public boolean hasNextCommand() {
        return false;
    }

    @Override
    public String getNextCommand() {
        return "";
    }

    @Override
    public void close() {

    }
}
