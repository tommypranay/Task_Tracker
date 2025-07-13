package org.tommy.base;

import org.tommy.TaskManager;
import org.tommy.exception.PersistenceException;
import org.tommy.models.Task;
import org.tommy.parser.CommandProvider;
import org.tommy.parser.ConsoleCommandProvider;
import org.tommy.parser.ConsoleParser;
import org.tommy.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CliMode implements OperationMode{

    private final CommandProvider commandProvider;
    private final Parser parser;
    private boolean running = false;

    /**
     * {@code CliMode} provides an interactive command-line interface (CLI)
     * for managing tasks using textual commands.
     *
     * This mode supports user interactions through standard input (console),
     * allowing operations such as adding, updating, deleting, listing, and
     * marking tasks via simple commands like:
     *
     * <pre>
     * task-cli add "Buy groceries"
     * task-cli update 1 "Buy groceries and cook"
     * task-cli delete 1
     * task-cli list [done|not-done|in-progress]
     * task-cli mark-done 1
     * task-cli exit
     * </pre>
     *
     * The class uses {@link ConsoleCommandProvider} to read commands and
     * {@link ConsoleParser} to parse them into arguments. It delegates task
     * operations to {@link TaskManager}, which handles persistence and updates.
     *
     * This implementation loops until the user issues the `exit` command.
     */

    public CliMode(){
        this.commandProvider = new ConsoleCommandProvider();
        this.parser = new ConsoleParser();
    }


    /**
     * Starts the CLI loop, processing user commands until 'exit' is received.
     */
    @Override
    public void start(TaskManager taskManager) {
        running = true;
        while(running && commandProvider.hasNextCommand()){
            String command = commandProvider.getNextCommand();
            List<String> args = parser.parseArguments(command);
            if(args.size() <2 || !args.get(0).equals("task-cli")){
                String message = """
                        Unknown command.
                        Please Type task-cli help for more information.
                        """;
                System.out.println(message);
                continue;
            }
            if(args.get(1).equals("add")){
                if(args.size() < 3){
                    String message = """
                        Unknown operation.
                        Please Type task-cli help for more information.
                        """;
                    System.out.println(message);
                    continue;
                }
                if(args.size() >3){
                    String message = """
                        Too many arguments.
                        Please Type task-cli help for more information.
                        """;
                    System.out.println(message);
                    continue;
                }
                Task task = new Task(0, Status.NOT_DONE, args.get(2));
                try{
                    int id = taskManager.addTask(task);
                    System.out.printf("Task added successfully. (ID: %d)%n", id);
                }catch (PersistenceException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if(args.get(1).equals("list")){
                List<Task> tasks = taskManager.getTasks();
                if(args.size() < 3) {
                    printTasks(tasks);
                    continue;
                }
                switch (args.get(2)) {
                    case "done" -> {
                        tasks = tasks.stream()
                                .filter(t -> t.getStatus().equals(Status.DONE))
                                .collect(Collectors.toCollection(ArrayList::new));
                        if(tasks.isEmpty()){
                            System.out.println("No tasks are Done");
                            continue;
                        }
                        printTasks(tasks);
                    }
                    case "not-done" ->{
                        tasks = tasks.stream()
                                .filter(t -> t.getStatus().equals(Status.NOT_DONE))
                                .collect(Collectors.toCollection(ArrayList::new));
                        if(tasks.isEmpty()){
                            System.out.println("No tasks are Not Done");
                            continue;
                        }
                        printTasks(tasks);
                    }
                    case "in-progress" ->{
                        tasks = tasks.stream()
                                .filter(t -> t.getStatus().equals(Status.IN_PROGRESS))
                                .collect(Collectors.toCollection(ArrayList::new));
                        if(tasks.isEmpty()){
                            System.out.println("No tasks are In Progress");
                            continue;
                        }
                        printTasks(tasks);
                    }
                    default ->{
                            System.out.println("Unknown status: " + args.get(2));
                            System.out.println("Available filters: done, not-done, in-progress");
                    }
                }
                continue;
            }
            if(args.get(1).equals("help")){
                String message = """
                        task-cli add <task>
                        task-cli update <taskId> <task>
                        task-cli delete <taskId>
                        task-cli list [<filter>]
                        task-cli help
                        task-cli mark-in-progress <taskId>
                        task-cli mark-done <taskId>
                        task-cli exit
                        """;
                System.out.println(message);
                continue;
            }
            if(args.get(1).equals("update")){
                if(args.size() < 4){
                    String message = """
                            Please provide a task ID and a new task.
                            """;
                    System.out.println(message);
                    continue;
                }
                int id = Integer.parseInt(args.get(2));
                Task task = taskManager.getTask(id);
                task.setTask(args.get(3));
                try{
                    taskManager.updateTask(id, task);
                    System.out.println("Task updated successfully.");
                }catch (PersistenceException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if(args.get(1).equals("delete")) {
                if (args.size() < 3) {
                    String message = """
                            Please provide a task ID.
                            """;
                    System.out.println(message);
                    continue;
                }
                int id = Integer.parseInt(args.get(2));
                try{
                    taskManager.DeleteTask(id);
                    System.out.println("Task deleted successfully.");
                }catch (PersistenceException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if(args.get(1).equals("mark-in-progress")){
                if(args.size() < 3){
                    String message = """
                            Please provide a task ID.
                            """;
                    System.out.println(message);
                    continue;
                }
                int id = Integer.parseInt(args.get(2));
                try{
                    Task task = taskManager.getTask(id);
                    task.setStatus(Status.IN_PROGRESS);
                    taskManager.updateTask(id, task);
                    System.out.println("Task marked in progress successfully.");
                }catch (PersistenceException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if(args.get(1).equals("mark-done")){
                if(args.size() < 3){
                    String message = """
                            Please provide a task ID.
                            """;
                    System.out.println(message);
                    continue;
                }
                int id = Integer.parseInt(args.get(2));
                try{
                    Task task = taskManager.getTask(id);
                    task.setStatus(Status.DONE);
                    taskManager.updateTask(id, task);
                    System.out.println("Task marked done successfully.");
                }catch (PersistenceException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if(args.get(1).equals("exit")){
                commandProvider.close();
                System.out.println("Goodbye! :)");
                break;
            }

        }
    }

    /**
     * Prints a list of tasks to the console.
     */
    private void printTasks(List<Task> tasks) {
        tasks.forEach(System.out::println);
    }


    /**
     * Gracefully ends the CLI mode (currently unused).
     */
    @Override
    public void end() {
        commandProvider.close();
        System.out.println("Process interrupted. Shutting down...... :)");
        System.exit(0);
    }
}
