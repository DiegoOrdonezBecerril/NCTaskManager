package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTaskListImplTest {
    @Test
    public void emptyConstructorTest() {
        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();
        assertNotEquals(null, arrayTaskList);
    }

    @Test
    public void addTest() {
        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();
        Task task = new TaskImpl("Meeting in a café", 10);

        arrayTaskList.add(task);

        assertEquals(1, arrayTaskList.size());
        assertEquals(task, arrayTaskList.getTask(0));
    }

    @Test
    public void removeTest() {
        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();
        Task task = new TaskImpl("Meeting in a café", 10);

        arrayTaskList.add(task);

        assertTrue(arrayTaskList.remove(task));
        assertEquals(0, arrayTaskList.size());
        assertFalse(arrayTaskList.remove(task));
    }

    @Test
    public void sizeTest() {
        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();
        Task task = new TaskImpl("Meeting in a café", 10);

        for(int i = 0; i < 10; i++){
            arrayTaskList.add(task);
        }
        arrayTaskList.remove(task);

        assertEquals(9, arrayTaskList.size());
    }

    @Test
    public void getTaskTest() {
        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();
        Task task1 = new TaskImpl("Meeting in a café", 10);
        Task task2 = new TaskImpl("Meeting with friends", 10, 20, 2);

        arrayTaskList.add(task1);
        arrayTaskList.add(task2);

        assertEquals(task1, arrayTaskList.getTask(0));
        assertEquals(task2, arrayTaskList.getTask(1));
        assertNull(arrayTaskList.getTask(10));
    }
}
