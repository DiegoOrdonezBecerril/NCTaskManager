package mx.tc.j2se.tasks;

/**
 * The class LinkedTaskListImpl is the main LinkedTaskList interface implementation
 */
public class LinkedTaskListImpl implements LinkedTaskList {
    private Node first;
    private Node current;

    /**
     * Constructs a linked task list without set any property
     */
    public LinkedTaskListImpl() {}

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

        if (this.first == null) {
            this.current = new Node(task);
            this.first = this.current;
        } else {
            Node node = new Node(task);
            this.current.next = node;
            this.current = node;
        }
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
        int counter = 0;
        Node node = this.first;

        while (node != null) {
            counter++;
            node = node.next;
        }

        return counter;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when index is less than zero or when is equals or greater than the list size
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
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException when 'from' or 'to' are negative numbers or when 'to' is less or equals than 'from'
     */
    @Override
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("'from' or 'to' can not be a negative number");
        } else if (to <= from) {
            throw new IllegalArgumentException("'to' can not be less or equals than 'from'");
        }

        LinkedTaskList linkedTaskList = new LinkedTaskListImpl();
        Node node = this.first;

        while (node != null) {
            int next = node.task.nextTimeAfter(from);
            if (next > from && next < to) {
                linkedTaskList.add(node.task);
            }
            node = node.next;
        }

        return linkedTaskList;
    }

    /**
     * The class Node is the node representation for the LinkedTaskList
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
        public Node(Task task) throws IllegalArgumentException {
            if (task == null) {
                throw new IllegalArgumentException("Task can not be null");
            }

            this.task = task;
        }
    }
}