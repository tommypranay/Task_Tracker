package org.tommy;

import org.junit.jupiter.api.*;
import org.tommy.configuration.AppConfig;
import org.tommy.models.Task;
import org.tommy.base.Status;
import org.tommy.persist.FileSaver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setup() throws IOException {
        AppConfig appConfig = AppConfig.getInstance();
        try(FileWriter writer = new FileWriter(appConfig.getProperty("json.file.path"))){
            writer.write("");
        }

        taskManager = new TaskManager(new FileSaver());
    }

    @Test
    void testAddAndRetrieveTask() {
        Task newTask = new Task(0, Status.NOT_DONE, "Write unit tests");
        int id = taskManager.addTask(newTask);

        Task retrieved = taskManager.getTask(id);
        assertNotNull(retrieved);
        assertEquals("Write unit tests", retrieved.getTask());
        assertEquals(Status.NOT_DONE, retrieved.getStatus());
    }

    @Test
    void testUpdateTask() {
        Task newTask = new Task(0, Status.NOT_DONE, "Draft report");
        int id = taskManager.addTask(newTask);

        Task update = taskManager.getTask(id);
        update.setTask("Finalize report");
        update.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(id, update);

        Task updated = taskManager.getTask(id);
        assertEquals("Finalize report", updated.getTask());
        assertEquals(Status.IN_PROGRESS, updated.getStatus());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(0, Status.NOT_DONE, "Prepare slides");
        int id = taskManager.addTask(task);

        int deletedId = taskManager.DeleteTask(id);
        assertEquals(id, deletedId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskManager.getTask(id);
        });
        assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    void testListTasks() {
        taskManager.addTask(new Task(0, Status.NOT_DONE, "T1"));
        taskManager.addTask(new Task(0, Status.DONE, "T2"));
        taskManager.addTask(new Task(0, Status.IN_PROGRESS, "T3"));

        List<Task> tasks = taskManager.getTasks();
        assertEquals(3, tasks.size());
    }
}
