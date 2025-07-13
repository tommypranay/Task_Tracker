package org.tommy.models;

import org.junit.jupiter.api.Test;
import org.tommy.base.Status;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testConstructorAndGetters() {
        Task task = new Task(1, Status.NOT_DONE, "Test task");
        assertEquals(1, task.getId());
        assertEquals("Test task", task.getTask());
        assertEquals(Status.NOT_DONE, task.getStatus());
    }

    @Test
    void testSetters() {
        Task task = new Task(1);
        task.setTask("Updated task");
        task.setStatus(Status.DONE);
        task.setCreatedAt(LocalDateTime.now());

        assertEquals("Updated task", task.getTask());
        assertEquals(Status.DONE, task.getStatus());
        assertNotNull(task.getCreatedAt());
    }
}
