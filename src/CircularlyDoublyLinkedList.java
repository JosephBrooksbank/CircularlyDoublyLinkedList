
public class CircularlyDoublyLinkedList<ValueType> {
    private int size;
    private Node cursor;


    public CircularlyDoublyLinkedList() {
        this.size = 0;
        this.cursor = null;

    }

    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * Adds a value after the current cursor location
     * @param value the value to add
     */
    public void addAfterCursor(ValueType value){
            Node newNode = new Node(value);
            // Special case: list is empty
            if (isEmpty()){
                cursor = newNode;
                size++;
            // Special case: list has only one value (value added will be "final" loopback node)
            } else if (cursor.getNext() == null){
                addBetween(value, cursor, cursor);
            // Base case
            } else {
                addBetween(value, cursor, cursor.getNext());
            }
    }

    /**
     * Deletes current cursor, and moves to next in list for new cursor
     * @return  The value of the deleted object
     */
    public ValueType deleteCursor(){
        if (this.cursor == null){
            throw new IllegalStateException("Can't delete from an empty list");
        }
        // Node to set cursor to after old cursor is deleted
        Node next = this.cursor.getNext();
        ValueType ret = removeNode(this.cursor);

        this.cursor = next;
        return ret;
    }

    /**
     * Moves the cursor forward some amount of steps
     * @param n The number of steps to move
     */
    public void advanceCursor(int n) {
        if (isEmpty()){
            throw new IllegalStateException("Can't move forward in an empty list");
        }
        for (int i = 0; i < n; i++){
            this.cursor = this.cursor.getNext();
        }
    }

    /**
     * @return  The value of the current location
     */
    public ValueType getValue(){
        if (cursor == null){
            throw new IllegalStateException("Can't get value from empty list");
        }
        return cursor.getValue();
    }

    /**
     * Adds a new node between two already created nodes
     * @param value The value to add
     * @param pred  The previous node
     * @param succ  The successor node
     */
    private void addBetween(ValueType value, Node pred, Node succ) {
        Node newNode = new Node(value);

        // Fixing pointers from surrounding nodes
        pred.setNext(newNode);
        succ.setPrev(newNode);

        // Adding points for new node
        newNode.setPrev(pred);
        newNode.setNext(succ);

        size++;

    }

    /**
     * Removes a node
     * @param node  The node to remove
     * @return  The value of the removed node
     */
    private ValueType removeNode(Node node) {
        // Saving value
        ValueType ret = node.getValue();

        // Update the references
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());

        // Decrement size
        size--;
        // Return old value
        return ret;
    }

    /**
     * @return The values of the list as a String
     */
    public String toString(){
        StringBuilder sb = new  StringBuilder();

        sb.append("(size = ").append(size).append(")");
        for (int i = 0; i < size; i++){
            sb.append(" ").append(cursor.getValue());
            this.advanceCursor(1);
        }

        return sb.toString();
    }

    /**
     * Node class for each data node
     * NEXT node is NULL is last node
     *
     */
    private class Node {
        //private variables
        //1 our data
        private ValueType value;
        //2 our reference to the next Node
        private Node next;
        //3 our reference to the previous Node
        private Node prev;


        // constructors

        /**
         * Constructor for a doubly linked node
         * @param value The value of the node
         * @param prev  The previous node
         * @param next  The next node
         */
        public Node(ValueType value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;

        }

        /**
         * Constructor for a node which is not yet in a list
         * @param value The value of the node
         */
        public Node(ValueType value){
            this(value, null, null);
        }

        // getters and setters

        // Value
        public ValueType getValue() {
            return value;
        }

        public void setValue(ValueType value) {
            this.value = value;
        }

        // Next
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        // Prev
        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }


    }
}
