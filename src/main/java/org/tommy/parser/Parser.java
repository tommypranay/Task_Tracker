package org.tommy.parser;

import java.util.List;

/**
 * Defines a strategy for parsing raw input strings into command arguments.
 *
 * This interface allows for different parsing implementations such as console or file input.
 */
public interface Parser {

    /**
     * Parses the given input string into a list of arguments.
     *
     * @param args The raw input string (e.g., from console or file).
     * @return A list of parsed argument strings.
     */
    List<String> parseArguments(String args);

}

