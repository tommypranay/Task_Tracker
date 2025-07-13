package org.tommy.modes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.tommy.TaskManager;
import org.tommy.base.CliMode;
import org.tommy.configuration.AppConfig;
import org.tommy.persist.FileSaver;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCliMode {

    @BeforeAll
    static void clearDataOnce() throws IOException {
        AppConfig appConfig = AppConfig.getInstance();
        try(FileWriter writer = new FileWriter(appConfig.getProperty("json.file.path"))){
            writer.write("");
        }
    }

    @Test
    void testCliModeAddAndExit(){

        String stimulatedInput = "task-cli add \"Read Books\"\ntask-cli exit\n";
        System.setIn(new ByteArrayInputStream(stimulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CliMode cliMode = new CliMode();
        try {
            TaskManager taskManager = new TaskManager(new FileSaver());
            cliMode.start(taskManager);

            String output = outputStream.toString();
            assertTrue(output.contains("Task added successfully"), "Should confirm task addition");
            assertTrue(output.contains("Goodbye! :)"), "Should exit after second command");
        }catch (FileNotFoundException e){
            System.out.println("configuration file missing");
        }
    }
}
