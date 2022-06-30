package mx.tc.j2se.tasks;

/**
 * The class LinkedTaskListImpl allow to save task in a simple linked list
 */
public class LinkedTaskListImpl extends AbstractTaskList {
    private Node first;
    private Node current;
    private int size = 0;

    /**
     * Constructs a linked task list without set any property
     */
    public LinkedTaskListImpl() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Task can not be null");
        }

        if (this.first == null) {
            this.current = new Node(task);
            this.first = this.current;
        } else {
            Node node = new Node(task);
            this.current.next = node;
            this.current = node;
        }

        this.size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Task task) {
        boolean removed = false;
        Node node = this.first;
        Node lastNode = null;

        while (node != null && !removed) {
            if (node.task.equals(task)) {
                if (node.equals(this.first)) {
                    this.first = this.first.next;
                } else {
                    lastNode.next = node.next;
                }

                removed = true;
                this.size--;
            }

            lastNode = node;
            node = node.next;
        }

        return removed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index must be in the list limits, it can not be less than zero");
        }

        int counter = 0;
        Task task = null;
        Node node = this.first;

        while (node != null && counter <= index) {
            if (counter == index) {
                task = node.task;
            }
            counter++;
            node = node.next;
        }

        return task;
    }

    /**
     * The class Node is the node representation for a linked task list
     */
    private static class Node {
        private final Task task;
        private Node next;

        /**
         * Constructs a node only setting the task.
         *
         * @param task the argument who will be contained by the node
         *
         * @throws IllegalArgumentException when task is null.
         */
        private Node(Task task) throws IllegalArgumentException {
            if (task == null) {
                throw new IllegalArgumentException("Task can not be null");
            }

            this.task = task;
        }
    }
}