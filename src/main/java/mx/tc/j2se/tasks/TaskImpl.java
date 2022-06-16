package mx.tc.j2se.tasks;

/**
 * The class TaskImpl is the main task interface implementation
 */
public class TaskImpl implements Task {
    private String title;
    private boolean active;
    private int time;
    private int startTime;
    private int endTime;
    private int repeatInterval;

    /**
     * Constructs a task without set any property
     */
    public TaskImpl() {}

    /**
     * Constructs an inactive non-repetitive task
     * to run at specified time with a given name.
     *
     * @param title the argument who will be set as task title
     * @param time the argument who will be set as time
     */
    public TaskImpl(String title, int time) {
        this.active = false;
        this.title = title;
        this.time = time;
    }

    /**
     * Constructs an inactive task to run within
     * the specified time range with the set repetition interval
     * and with a given name.
     *
     * @param title the argument who will be set as title
     * @param start the argument who will be set as start time
     * @param end the argument who will be set as end time
     * @param interval the argument who will be set as repeat interval
     */
    public TaskImpl(String title, int start, int end, int interval) {
        this.active = false;
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
    }

    /**
     * Returns the task title.
     *
     * @return The title.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title from a String.
     *
     * @param title the argument who will be set as title
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the task status.
     *
     * @return the value true if it's active or false if it's not.
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Sets the task status from a boolean.
     *
     * @param active the argument who will be set as status
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the task time, if task is a repetitive one, it will return the task start time.
     *
     * @return the time or start time if task is not repetitive.
     */
    @Override
    public int getTime() {
        return this.isRepeated() ? this.startTime : this.time;
    }

    /**
     * Sets the task time from an integer, if the task
     * is repetitive, it will become in a non-repetitive one.
     *
     * @param time the argument who will be set as time
     */
    @Override
    public void setTime(int time) {
        this.time = time;

        //If task is repetitive, we become it into a non-repetitive, restoring some properties to the default value
        if (this.isRepeated()) {
            this.startTime = 0;
            this.endTime = 0;
            this.repeatInterval = 0;
        }
    }

    /**
     * Returns the task start time, if task is a non-repetitive, it will return the task time.
     *
     * @return the start time or time if task is not repetitive.
     */
    @Override
    public int getStartTime() {
        return this.isRepeated() ? this.startTime : this.time;
    }

    /**
     * Returns the task end time, if task is a non-repetitive, it will return the task time.
     *
     * @return the end time or time if task is not repetitive.
     */
    @Override
    public int getEndTime() {
        return this.isRepeated() ? this.endTime : this.time;
    }

    /**
     * Returns the task repeat interval, if task is a non-repetitive, it will return zero.
     *
     * @return the repeat interval or zero if task is not repetitive.
     */
    @Override
    public int getRepeatInterval() {
        return this.isRepeated() ? this.repeatInterval : 0;
    }

    /**
     * Sets the task start time, task end time and task repeat
     * interval, if task is a non-repetitive one, it will become in repetitive.
     *
     * @param start the argument who will be set as start time
     * @param end the argument who will be set as end time
     * @param interval the argument who will be set as repeat interval
     */
    @Override
    public void setTime(int start, int end, int interval) {
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;

        //If task is non-repetitive, we become it into a repetitive restoring time property to the default value
        if (!this.isRepeated()) {
            this.time = 0;
        }
    }

    /**
     * Returns the task repeatability.
     *
     * @return the value true if it's repetitive or false if it's not.
     */
    @Override
    public boolean isRepeated() {
        return this.repeatInterval > 0;
    }
}
