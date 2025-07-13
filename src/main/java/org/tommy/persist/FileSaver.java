package org.tommy.persist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.tommy.base.LocalDateTimeAdapter;
import org.tommy.configuration.AppConfig;
import org.tommy.exception.PersistenceException;
import org.tommy.models.Task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implements the {@link TaskSaver} interface to persist tasks using a JSON file.
 * Handles reading from and writing to the file system using Gson.
 */
public class FileSaver implements TaskSaver{

    private final Gson gson;
    private final AppConfig appConfig;
    private TaskStore taskStore;

    /**
     * Constructs a new FileSaver and loads existing tasks from the configured JSON file.
     * Initializes Gson with support for LocalDateTime serialization.
     *
     * @throws FileNotFoundException if the configured JSON file is not found.
     */
    public FileSaver() throws FileNotFoundException {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting().create();
        this.appConfig = AppConfig.getInstance();
        this.taskStore = loadFromFile();
    }

    /**
     * Loads the task store from the JSON file. If the file is empty or invalid,
     * returns a new TaskStore with an empty task list and nextId set to 1.
     *
     * @return the loaded or newly initialized TaskStore.
     * @throws FileNotFoundException if the file is not found.
     */
    private TaskStore loadFromFile() throws FileNotFoundException {
        TaskStore taskStore = gson.fromJson(new FileReader(appConfig.getProperty("json.file.path")), TaskStore.class);
        if(taskStore == null) {
            taskStore = new TaskStore(1, new ArrayList<>());
        }
        return taskStore;
    }

    /**
     * Adds a new task, assigns it a unique ID, timestamps it, saves to file,
     * and returns the created task.
     *
     * @param task the task to add (without ID).
     * @return the created task with ID and timestamps.
     * @throws PersistenceException if writing to file fails.
     */
    @Override
    public Task addTask(Task task) throws PersistenceException {
        int nextId = taskStore.getNextId();
        Task tempTask = new Task(nextId, task.getStatus(), task.getTask());
        LocalDateTime currentTime = LocalDateTime.now();
        tempTask.setCreatedAt(currentTime);
        tempTask.setUpdatedAt(currentTime);
        taskStore.setNextId(nextId + 1);
        taskStore.addTask(tempTask);
        try(FileWriter fileWriter = new FileWriter(appConfig.getProperty("json.file.path"))){
            gson.toJson(taskStore, fileWriter);
            fileWriter.flush();
            taskStore = loadFromFile();
        }catch (FileNotFoundException e) {
            throw new PersistenceException(e);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
        return tempTask;
    }

    /**
     * Deletes a task with the given ID from the task store and writes the updated store to file.
     *
     * @param taskId the ID of the task to delete.
     * @return the ID of the deleted task.
     * @throws PersistenceException if the task is not found or file write fails.
     */
    @Override
    public int deleteTask(int taskId) throws PersistenceException {
        Task task = getTask(taskId);
        if(task == null) {
            throw new PersistenceException("Task with id: " + taskId + " not found.");
        }
        taskId = taskStore.deleteTask(task);
        try(FileWriter fileWriter = new FileWriter(appConfig.getProperty("json.file.path"))){
            gson.toJson(taskStore, fileWriter);
            fileWriter.flush();
            taskStore = loadFromFile();
        }catch (FileNotFoundException e) {
            taskStore.getTasks().add(task);
            throw new PersistenceException(e);
        } catch (IOException e) {
            taskStore.getTasks().add(task);
            throw new PersistenceException(e);
        }
        return taskId;

    }

    /**
     * Updates an existing task with new values and writes changes to file.
     *
     * @param task the task with updated fields.
     * @return the updated task.
     * @throws PersistenceException if the task is not found or file write fails.
     */
    @Override
    public Task updateTask(Task task) throws PersistenceException {
        Task persistedTask;
        try{
            persistedTask = getTask(task.getId());
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
        Task backupTask = new Task(persistedTask.getId(), persistedTask.getStatus(), persistedTask.getTask());
        if(persistedTask == null) {
            throw new PersistenceException("Task with id: " + task.getId() + " not found.");
        }
        persistedTask.setStatus(task.getStatus());
        persistedTask.setTask(task.getTask());
        persistedTask.setUpdatedAt(LocalDateTime.now());
        try(FileWriter fileWriter = new FileWriter(appConfig.getProperty("json.file.path"))){
            gson.toJson(taskStore, fileWriter);
            fileWriter.flush();
            taskStore = loadFromFile();
        }catch (FileNotFoundException e) {
            persistedTask.setStatus(backupTask.getStatus());
            persistedTask.setTask(backupTask.getTask());
            throw new PersistenceException(e);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
        return persistedTask;
    }

    /**
     * Retrieves a task by ID.
     *
     * @param id the ID of the task.
     * @return the task with the given ID.
     * @throws NoSuchElementException if no task is found.
     */
    @Override
    public Task getTask(int id) throws NoSuchElementException {
        Task task = taskStore.getTasks().stream().filter(t -> t.getId()==id).findAny().orElse(null);
        if(task==null){
            throw new NoSuchElementException(String.format("Task with %d not found", id));
        }
        return task;
    }

    /**
     * Returns the list of all tasks in the store.
     *
     * @return the task list.
     */
    @Override
    public List<Task> getTasks() {
        return taskStore.getTasks();
    }
}
