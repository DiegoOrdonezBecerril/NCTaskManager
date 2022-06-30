package mx.tc.j2se.tasks;

/**
 * The TaskListFactory class allows instance a task list that extends of AbstractTaskList
 */
public class TaskListFactory {
    /**
     * Returns an instance of some class that extends of AbstractTaskList depending on the given type
     *
     * @param type the wished type of the returned task list
     *
     * @return a new task list instance
     */
    public static AbstractTaskList createTaskList(ListTypes.types type) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("Type can not be null");
        }

        switch (type) {
            case ARRAY:
                return new ArrayTaskListImpl();
            case LINKED:
                return new LinkedTaskListImpl();
            default:
                return null;
        }
    }
}
