package mx.tc.j2se.tasks;

/**
 * The AbstractTaskList defines the basic structure of a task list
 */
public abstract class AbstractTaskList {
    /**
     * Adds a new task to the list.
     *
     * @param task the task to add
     *
     * @throws IllegalArgumentException when task is null.
     */
    abstract void add(Task task) throws IllegalArgumentException;

    /**
     * Removes a specific task from the list.
     *
     * @param task the task to remove
     *
     * @return true if task has been removed from the list or false if it has been not.
     */
    abstract boolean remove(Task task);

    /**
     * Returns the size of the list.
     *
     * @return the number of tasks in the list.
     */
    abstract int size();

    /**
     * Returns a specific task from the list by the index.
     *
     * @param index the position of the desired task
     *
     * @return the task specified by the index or null if it does not exist.
     *
     * @throws IndexOutOfBoundsException when index is less than zero or when is equals or greater than the list size
     */
    abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /**
     * Returns an AbstractTaskList object with a subset of tasks that are scheduled
     * for execution at least once in a specified time-lapse.
     *
     * @param from the argument who will be used as time-lapse beginning
     * @param to   the argument who will be used as time-lapse ending
     * @return a subset of tasks scheduled in the time-lapse specified.
     *
     * @throws IllegalArgumentException when 'from' or 'to' are negative numbers or when 'to' is less or equals than 'from'
     */
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("'from' or 'to' can not be a negative number");
        } else if (to <= from) {
            throw new IllegalArgumentException("'to' can not be less or equals than 'from'");
        }

        AbstractTaskList abstractTaskList = this instanceof LinkedTaskListImpl ? new LinkedTaskListImpl() : new ArrayTaskListImpl();

        for (int i = 0; i < this.size(); i++) {
            Task task = this.getTask(i);
            int next = task != null ? task.nextTimeAfter(from) : -1;
            if (next > from && next < to) {
                abstractTaskList.add(task);
            }
        }

        return abstractTaskList;
    }
}
