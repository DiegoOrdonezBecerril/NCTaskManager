package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedTaskListImplTest {
    @Test
    public void emptyConstructorTest() {
        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        assertNotEquals(null, linkedTaskList);
    }

    @Test
    public void addTest() {
        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        Task task = new TaskImpl("Meeting in a café", 10);

        linkedTaskList.add(task);

        assertEquals(1, linkedTaskList.size());
        assertEquals(task, linkedTaskList.getTask(0));
    }

    @Test
    public void removeTest() {
        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        Task task = new TaskImpl("Meeting in a café", 10);

        linkedTaskList.add(task);

        assertTrue(linkedTaskList.remove(task));
        assertEquals(0, linkedTaskList.size());
        assertFalse(linkedTaskList.remove(task));
        assertFalse(linkedTaskList.remove(null));
    }

    @Test
    public void sizeTest() {
        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        Task task = new TaskImpl("Meeting in a café", 10);

        for(int i = 0; i < 10; i++){
            linkedTaskList.add(task);
        }
        linkedTaskList.remove(task);

        assertEquals(9, linkedTaskList.size());
    }

    @Test
    public void getTaskTest() {
        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        Task task1 = new TaskImpl("Meeting in a café", 10);
        Task task2 = new TaskImpl("Meeting with friends", 10, 20, 2);

        linkedTaskList.add(task1);
        linkedTaskList.add(task2);

        assertEquals(task1, linkedTaskList.getTask(0));
        assertEquals(task2, linkedTaskList.getTask(1));

        LinkedTaskList linkedTaskList2 = new LinkedTaskListImpl();

        assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedTaskList2.getTask(2));
    }

    @Test
    public void incomingTest() {
        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        Task task1 = new TaskImpl("Meeting in a café", 10);
        Task task2 = new TaskImpl("Meeting with friends", 10, 20, 2);
        Task task3 = new TaskImpl("Taking medication", 5, 15, 8);
        Task task4 = new TaskImpl("Lunch with a beautiful girl", 13);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);

        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);

        LinkedTaskList subset = linkedTaskList.incoming(10, 16);

        assertEquals(2, subset.size());
        assertEquals(task2, subset.getTask(0));
        assertEquals(task4, subset.getTask(1));
    }
}
