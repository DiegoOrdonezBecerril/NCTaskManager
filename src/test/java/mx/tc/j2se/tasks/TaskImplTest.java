package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskImplTest {
    //Tests has been done based on practice requirements

    @Test
    public void emptyConstructorTest() {
        Task task = new TaskImpl();
        assertNotEquals(null, task);
    }

    @Test
    public void nonRepetitiveConstructorTest() {
        Task task = new TaskImpl("Meeting in a café", 10);
        assertFalse(task.isActive());
        assertEquals("Meeting in a café", task.getTitle());
        assertEquals(10, task.getTime());
    }

    @Test
    public void repetitiveConstructorTest() {
        Task task = new TaskImpl("Meeting in a café", 0, 10, 2);
        assertFalse(task.isActive());
        assertEquals("Meeting in a café", task.getTitle());
        assertEquals(0, task.getStartTime());
        assertEquals(10, task.getEndTime());
        assertEquals(2, task.getRepeatInterval());
    }

    @Test
    public void getTitleTest() {
        Task task = new TaskImpl("Meeting in a café", 10);
        assertEquals("Meeting in a café", task.getTitle());
    }

    @Test
    public void setTitleTest() {
        Task task = new TaskImpl("Meeting in a café", 10);
        task.setTitle("Morning run");
        assertEquals("Morning run", task.getTitle());
    }

    @Test
    public void isActiveTest() {
        Task task = new TaskImpl("Meeting in a café", 10);
        assertFalse(task.isActive());
    }

    @Test
    public void setActiveTest() {
        Task task = new TaskImpl("Meeting in a café", 10);
        task.setActive(true);
        assertTrue(task.isActive());
    }

    @Test
    public void getTimeTest() {
        /*
        The getTime method must return the time given in the constructor,
        if is repetitive, instead it must return the start time
         */
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);

        assertEquals(10, nonRepetitiveTask.getTime());
        assertEquals(0, repetitiveTask.getTime());
    }

    @Test
    public void setTimeTest() {
        /*
        The getTime method must return the time given in the setTime method,
        if is repetitive, task also must become in a non-repetitive one
         */
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);

        nonRepetitiveTask.setTime(20);
        repetitiveTask.setTime(20);

        assertEquals(20, nonRepetitiveTask.getTime());
        assertEquals(20, repetitiveTask.getTime());
        assertFalse(repetitiveTask.isRepeated());
    }

    @Test
    public void getStartTimeTest() {
        /*
        The getStartTime method must return the start time given in the constructor,
        if is non-repetitive, instead it must return the time
         */
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);

        assertEquals(0, repetitiveTask.getStartTime());
        assertEquals(10, nonRepetitiveTask.getStartTime());
    }

    @Test
    public void getEndTimeTest() {
        /*
        The getEndTime method must return the end time given in the constructor,
        if is non-repetitive, instead it must return the time
         */
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);

        assertEquals(10, repetitiveTask.getEndTime());
        assertEquals(10, nonRepetitiveTask.getEndTime());
    }

    @Test
    public void getRepeatIntervalTest() {
        /*
        The getRepeatInterval method must return the repeat interval given in the constructor,
        if is non-repetitive, instead it must return 0
         */
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);

        assertEquals(2, repetitiveTask.getRepeatInterval());
        assertEquals(0, nonRepetitiveTask.getRepeatInterval());
    }

    @Test
    public void repetitiveSetTimeTest() {
        /*
        The getStartTime, getEndTime and getRepeatInterval methods must return the
        values given in the setTime method, if is non-repetitive,
        task also must become in a repetitive one
         */
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);

        repetitiveTask.setTime(1, 11, 3);
        nonRepetitiveTask.setTime(1, 11, 3);

        //Repetitive validation
        assertEquals(1, repetitiveTask.getStartTime());
        assertEquals(11, repetitiveTask.getEndTime());
        assertEquals(3, repetitiveTask.getRepeatInterval());

        //Non-repetitive validation
        assertEquals(1, nonRepetitiveTask.getStartTime());
        assertEquals(11, nonRepetitiveTask.getEndTime());
        assertEquals(3, nonRepetitiveTask.getRepeatInterval());
        assertTrue(nonRepetitiveTask.isRepeated());
    }

    @Test
    public void isRepeatedTest() {
        Task repetitiveTask = new TaskImpl("Meeting in a café", 0, 10, 2);
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);

        assertTrue(repetitiveTask.isRepeated());
        assertFalse(nonRepetitiveTask.isRepeated());
    }

    @Test
    public void nextTimeAfterTest() {
        /*
        The nextTimeAfter method must return the next time that the task will be executed after current,
        if it will be not executed anymore, it must return -1
         */
        Task nonRepetitiveTask = new TaskImpl("Meeting in a café", 10);
        Task repetitiveTask = new TaskImpl("Meeting in a café", 10, 20, 3);

        //Non-repetitive validation
        assertEquals(10, nonRepetitiveTask.nextTimeAfter(8));
        assertEquals(-1, nonRepetitiveTask.nextTimeAfter(11));
        assertEquals(-1, nonRepetitiveTask.nextTimeAfter(10));

        // Repetitive validation, in this task, it will be executed at 10, 13 and 16
        assertEquals(10, repetitiveTask.nextTimeAfter(8));
        assertEquals(13, repetitiveTask.nextTimeAfter(11));
        assertEquals(16, repetitiveTask.nextTimeAfter(15));
        assertEquals(-1, repetitiveTask.nextTimeAfter(17));
        assertEquals(-1, repetitiveTask.nextTimeAfter(21));
    }
}
