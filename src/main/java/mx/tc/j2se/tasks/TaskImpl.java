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
     *
     * @throws IllegalArgumentException when time is a negative number or when title is null.
     */
    public TaskImpl(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Time can not be a negative number");
        } else if (title == null) {
            throw new IllegalArgumentException("The title can not be null");
        }

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
     *
     * @throws IllegalArgumentException when the start time is a negative number, the end time is
     * greater than start time or when repeat interval is equals or less than zero or when title is
     * null.
     */
    public TaskImpl(String title, int start, int end, int interval) throws IllegalArgumentException {
        if (start < 0) {
            throw new IllegalArgumentException("The start time can not be a negative number");
        } else if (end <= start) {
            throw new IllegalArgumentException("The end time must be greater than start time");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("The repeat interval can not be equals or less than zero");
        } else if (title == null) {
            throw new IllegalArgumentException("The title can not be null");
        }

        this.active = false;
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException when title is null
     */
    @Override
    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null) {
            throw new IllegalArgumentException("The title can not be null");
        }

        this.title = title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTime() {
        return this.isRepeated() ? this.startTime : this.time;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException when time is a negative number.
     */
    @Override
    public void setTime(int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Time can not be a negative number");
        }

        this.time = time;

        //If task is repetitive, we become it into a non-repetitive, restoring some properties to the default value
        if (this.isRepeated()) {
            this.startTime = 0;
            this.endTime = 0;
            this.repeatInterval = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStartTime() {
        return this.isRepeated() ? this.startTime : this.time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEndTime() {
        return this.isRepeated() ? this.endTime : this.time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRepeatInterval() {
        return this.isRepeated() ? this.repeatInterval : 0;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException when the start time is a negative number, the end time is
     * greater than start time or when repeat interval is equals or less than zero.
     */
    @Override
    public void setTime(int start, int end, int interval) throws IllegalArgumentException {
        if (start < 0) {
            throw new IllegalArgumentException("The start time can not be a negative number");
        } else if (end <= start) {
            throw new IllegalArgumentException("The end time must be greater than start time");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("The repeat interval can not be equals or less than zero");
        }

        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;

        //If task is non-repetitive, we become it into a repetitive restoring time property to the default value
        if (!this.isRepeated()) {
            this.time = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRepeated() {
        return this.repeatInterval > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nextTimeAfter(int current) {
        int nextTime;

        /*
        If task is active, in both cases if current is less than the time or start time,
        the value returned by the getTime method will be the next
        execution time, if it's greater or equal and the task is repetitive
        the next execution time has to be computed.
         */
        if (current < this.getTime() && this.isActive()) {
            nextTime = this.getTime();
        } else if(isRepeated() && this.isActive()) {
            int sT = this.getStartTime();
            int eT = this.getEndTime();
            int rI = this.getRepeatInterval();

            int maxRepeatedTimes = (eT - sT) / rI;
            int limit = (maxRepeatedTimes * rI) + sT - rI;
            int computedNextTime = (((current - sT) / rI) * rI) + sT + rI;

            nextTime = computedNextTime <= limit ? computedNextTime : -1;
        } else {
            nextTime = -1;
        }

        return nextTime;
    }
}