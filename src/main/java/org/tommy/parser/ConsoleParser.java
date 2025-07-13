package org.tommy.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleParser implements Parser{

    /**
     * A parser implementation for parsing command-line arguments from a single string input.
     *
     * This parser supports quoted strings to allow multi-word arguments.
     * For example: {@code task-cli add "Buy groceries"} will produce:
     * {@code ["task-cli", "add", "Buy groceries"]}
     */


    /**
     * Parses a raw input string into individual arguments, preserving quoted strings as single arguments.
     *
     * @param args The raw input string from the console.
     * @return A list of parsed tokens.
     */
    @Override
    public List<String> parseArguments(String args) {
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(args);
        List<String> tokens = new ArrayList<>();
        while(matcher.find()){
            if(matcher.group(1) != null){
                tokens.add(matcher.group(1));
            }
            else{
                tokens.add(matcher.group(2));
            }
        }
        return tokens;
    }
}
