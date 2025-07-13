package org.tommy;

import org.tommy.base.CliMode;
import org.tommy.base.OperationMode;
import org.tommy.persist.FileSaver;
import org.tommy.persist.TaskSaver;

import java.io.FileNotFoundException;

/**
 * Entry point of the Task Tracking application.
 * Initializes the appropriate mode of operation (e.g., CLI) and starts the task manager.
 */
public class TaskTracker {

    private final TaskManager taskManager;
    private final OperationMode operationMode;

    /**
     * Constructs the TaskTracker with the given operation mode and task saver.
     *
     * @param operationMode the interface mode (CLI or API).
     * @param taskSaver the persistence mechanism to store and retrieve tasks.
     */
    public TaskTracker(OperationMode operationMode, TaskSaver taskSaver) {
        this.operationMode = operationMode;
        this.taskManager = new TaskManager(taskSaver);
    }

    /**
     * Returns the task manager used by this tracker.
     *
     * @return the TaskManager instance.
     */
    public TaskManager getTaskManager() {
        return taskManager;
    }


    /**
     * Starts the task tracker by invoking the operation mode's start logic.
     */
    public void run(){
        operationMode.start(taskManager);
    }

    /**
     * Main method. Determines the mode (e.g., "console") and launches the TaskTracker accordingly.
     *
     * @param args command line arguments, where args[0] can specify the mode (e.g., "console")
     */
    public static void main(String[] args) {
        String mode="";
        TaskTracker taskTracker = null;

        if(args.length>0) mode=args[0];

        switch (mode.toLowerCase()){
            case "console":
                try {
                    taskTracker = new TaskTracker(new CliMode(), new FileSaver());
                    taskTracker.run();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Unknown command.");
                System.exit(1);
        }
    }
}