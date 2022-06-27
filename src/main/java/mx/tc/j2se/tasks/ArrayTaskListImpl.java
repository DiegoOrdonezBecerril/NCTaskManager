package mx.tc.j2se.tasks;

/**
 * The class ArrayTaskListImpl is the main ArrayTaskList interface implementation
 */
public class ArrayTaskListImpl implements ArrayTaskList {
    private Task[] list = new Task[0];

    /**
     * Constructs an array task list without set any property
     */
    public ArrayTaskListImpl() {}

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException when task is null.
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Task can not be null");
        }

        Task[] temp = new Task[this.list.length + 1];
        System.arraycopy(this.list, 0, temp, 0, this.list.length);
        temp[temp.length - 1] = task;
        this.list = temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Task task) {
        boolean removed = false;

        for(int i = 0; i < this.list.length; i++){
            if(removed){
                this.list[i - 1] = this.list[i];
            }

            if(!removed && task != null && task.equals(this.list[i])){
                this.list[i] = null;
                removed = true;
            }
        }

        if(removed){
            Task[] temp = new Task[this.list.length - 1];
            System.arraycopy(this.list, 0, temp, 0, this.list.length - 1);
            this.list = temp;
        }

        return removed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.list.length;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (this.size() <= index) {
            throw new IndexOutOfBoundsException();
        }

        return this.list[index];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException when 'from' or 'to' are negative numbers or when 'to' is less or equals than 'from'
     */
    @Override
    public ArrayTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("'from' or 'to' can not be a negative number");
        } else if (to <= from) {
            throw new IllegalArgumentException("'to' can not be less or equals than 'from'");
        }

        ArrayTaskList arrayTaskList = new ArrayTaskListImpl();

        for (int i = 0; i < this.size(); i++) {
            Task task = this.getTask(i);
            int next = task != null ? task.nextTimeAfter(from) : -1;
            if (next > from && next < to) {
                arrayTaskList.add(task);
            }
        }

        return arrayTaskList;
    }
}