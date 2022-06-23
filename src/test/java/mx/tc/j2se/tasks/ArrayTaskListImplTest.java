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

    @Test
    public void incomingTest() {
        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();
        Task task1 = new TaskImpl("Meeting in a café", 10);
        Task task2 = new TaskImpl("Meeting with friends", 10, 20, 2);
        Task task3 = new TaskImpl("Taking medication", 5, 15, 8);
        Task task4 = new TaskImpl("Lunch with a beautiful girl", 13);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);

        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        arrayTaskList.add(task4);

        ArrayTaskList subset = arrayTaskList.incoming(10, 16);

        assertEquals(2, subset.size());
        assertEquals("Meeting with friends", subset.getTask(0).getTitle());
        assertEquals("Lunch with a beautiful girl", subset.getTask(1).getTitle());
    }
}
