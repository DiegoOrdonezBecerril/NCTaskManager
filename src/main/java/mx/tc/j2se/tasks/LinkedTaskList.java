package mx.tc.j2se.tasks;

/**
 * The interface LinkedTaskList defines the task basic methods to manipulate the list
 */
public interface LinkedTaskList {
    /**
     * Adds a new task to the list.
     *
     * @param task the task to add
     */
    void add(Task task);

    /**
     * Removes a specific task from the list.
     *
     * @param task the task to remove
     *
     * @return true if task has been removed from the list or false if it has been not.
     */
    boolean remove(Task task);

    /**
     * Returns the size of the list.
     *
     * @return the number of tasks in the list.
     */
    int size();

    /**
     * Returns a specific task from the list by the index.
     *
     * @param index the position of the desired task
     *
     * @return the task specified by the index or null if it does not exist.
     */
    Task getTask(int index);

    /**
     * Returns a LinkedTaskList object with a subset of tasks that are scheduled
     * for execution at least once in a specified time-lapse.
     *
     * @param from the argument who will be used as time-lapse beginning
     * @param to the argument who will be used as time-lapse ending
     *
     * @return a subset of tasks scheduled in the time-lapse specified.
     */
    LinkedTaskList incoming(int from, int to);
}