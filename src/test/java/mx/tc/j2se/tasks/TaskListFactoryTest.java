package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListFactoryTest {
    @Test
    public void createArrayList() {
        AbstractTaskList arrayTaskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        assertInstanceOf(ArrayTaskListImpl.class, arrayTaskList);
    }

    @Test
    public void createLinkedList() {
        AbstractTaskList linkedTaskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        assertInstanceOf(LinkedTaskListImpl.class, linkedTaskList);
    }
}
