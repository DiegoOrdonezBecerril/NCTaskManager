package mx.tc.j2se.tasks;

/**
 * The interface Task defines the task basic structure
 */
public interface Task {
    /**
     * Returns the task title.
     *
     * @return The title.
     */
    String getTitle();

    /**
     * Sets the task title from a String.
     *
     * @param title the argument who will be set as title
     */
    void setTitle(String title);

    /**
     * Returns the task status.
     *
     * @return the value true if it's active or false if it's not.
     */
    boolean isActive();

    /**
     * Sets the task status from a boolean.
     *
     * @param active the argument who will be set as status
     */
    void setActive(boolean active);

    /**
     * Returns the task time, if task is a repetitive one, it will return the task start time.
     *
     * @return the time or start time if task is not repetitive.
     */
    int getTime();

    /**
     * Sets the task time from an integer, if the task
     * is repetitive, it will become in a non-repetitive one.
     *
     * @param time the argument who will be set as time
     */
    void setTime(int time);

    /**
     * Returns the task start time, if task is a non-repetitive, it will return the task time.
     *
     * @return the start time or time if task is not repetitive.
     */
    int getStartTime();

    /**
     * Returns the task end time, if task is a non-repetitive, it will return the task time.
     *
     * @return the end time or time if task is not repetitive.
     */
    int getEndTime();

    /**
     * Returns the task repeat interval, if task is a non-repetitive, it will return zero.
     *
     * @return the repeat interval or zero if task is not repetitive.
     */
    int getRepeatInterval();

    /**
     * Sets the task start time, task end time and task repeat
     * interval, if task is a non-repetitive one, it will become in repetitive.
     *
     * @param start the argument who will be set as start time
     * @param end the argument who will be set as end time
     * @param interval the argument who will be set as repeat interval
     */
    void setTime(int start, int end, int interval);

    /**
     * Returns the task repeatability.
     *
     * @return the value true if it's repetitive or false if it's not.
     */
    boolean isRepeated();

    /**
     * Returns the next task execution time after the current time, if after
     * the specified time the task won't be executed anymore, it returns -1.
     *
     * @param current the argument who represent the current time
     *
     * @return the next execution time after current time or -1 if task won't
     * be executed anymore.
     */
    int nextTimeAfter (int current);
}