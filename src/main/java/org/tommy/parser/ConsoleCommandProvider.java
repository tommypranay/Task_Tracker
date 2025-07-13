package org.tommy.parser;

import java.util.Scanner;

public class ConsoleCommandProvider implements CommandProvider{
    /**
     * A command provider that reads commands from the system console.
     *
     * This implementation continuously waits for user input from the terminal.
     */

    /** Scanner to read user input from the console. */
    private Scanner scanner;

    public ConsoleCommandProvider(){
        scanner = new Scanner(System.in);
    }

    /**
     * Always returns {@code true} since the console continuously accepts input.
     *
     * @return {@code true}
     */
    @Override
    public boolean hasNextCommand() {
        return true; // Console always waits for input
    }


    /**
     * Prompts the user and reads the next line from the console.
     *
     * @return The next user-entered command as a {@code String}.
     */
    @Override
    public String getNextCommand() {
        System.out.println("> ");
        return scanner.nextLine();
    }

    /**
     * Closes the console scanner to release system resources.
     */
    @Override
    public void close() {
        scanner.close();
    }


}
