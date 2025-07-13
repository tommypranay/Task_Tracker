package org.tommy.persist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tommy.base.Status;
import org.tommy.models.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskStoreTest {

    private TaskStore store;

    @BeforeEach
    void setup() {
        store = new TaskStore(1, List.of());
    }

    @Test
    void testAddAndGetTasks() {
        Task task = new Task(1, Status.NOT_DONE, "Buy milk");
        assertTrue(store.addTask(task));

        List<Task> tasks = store.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Buy milk", tasks.get(0).getTask());
    }

    @Test
    void testNextId() {
        store.setNextId(5);
        assertEquals(5, store.getNextId());
    }
}
