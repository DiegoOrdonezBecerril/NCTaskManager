package mx.tc.j2se.tasks;

/**
 * The class ArrayTaskListImpl allow to save task in an array
 */
public class ArrayTaskListImpl extends AbstractTaskList {
    private Task[] list = new Task[0];

    /**
     * Constructs an array task list without set any property
     */
    public ArrayTaskListImpl() {}

    /**
     * {@inheritDoc}
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
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index must be in the array limits, it can not be less than zero");
        }

        return this.list[index];
    }
}