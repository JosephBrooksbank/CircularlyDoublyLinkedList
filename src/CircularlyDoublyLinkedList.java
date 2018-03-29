
public class CircularlyDoublyLinkedList<ValueType> {
    private int size;
    private Node cursor;
    private Node head;


    public CircularlyDoublyLinkedList() {
        this.size = 0;
        this.cursor = null;

    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public void addAfterCursor(ValueType value){
        if (cursor == null){
            System.out.println("should be doing this once");
            cursor = new Node(value);
            head = cursor;
        }
        else if (cursor.getNext() == null){
            System.out.println("doing this");
            addBetween(value, cursor, head);
        } else {
            System.out.println("doing that");
            addBetween(value, cursor, cursor.getNext());
        }
    }

    public ValueType deleteCursor(){
        if (cursor == null){
            throw new IllegalStateException("Can't delete from an empty list");
        }
        ValueType ret = cursor.getValue();
        removeNode(cursor);
        return ret;
    }

    public void advanceCursor(int n) {
        if (cursor == null){
            throw new IllegalStateException("can't advance an empty list");
        }
        for (int i = 0; i < n; i++){
            if (cursor.getNext() == null){
                cursor = head;
            } else {
                cursor = cursor.getNext();
            }
        }
    }

    public ValueType getValue(){
        return cursor.getValue();
    }



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


    public String toString(){
        StringBuilder sb = new  StringBuilder();

        sb.append("(size = ").append(size).append(")");
        for (int i = 0; i < size; i++){
            sb.append(" ").append(cursor.getValue());
            this.advanceCursor(i);
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
