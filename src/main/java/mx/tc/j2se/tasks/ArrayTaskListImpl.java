package mx.tc.j2se.tasks;

/**
 * The class ArrayTaskListImpl is the main ArrayTaskList interface implementation
 */
public class ArrayTaskListImpl implements ArrayTaskList {
    private Task[] list = new Task[0];

    /**
     * Constructs a task without set any property
     */
    public ArrayTaskListImpl() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Task task) {
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

            if(!removed && task.equals(this.list[i])){
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
    public Task getTask(int index) {
        return this.size() > index ? this.list[index] : null;
    }
}
