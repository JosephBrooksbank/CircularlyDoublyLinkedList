import java.util.Random;

public class Driver {



    public static void main(String[] args) {
        // Random object for moving forward a random number of spaces
        Random rand = new Random();
        // List object
        CircularlyDoublyLinkedList<Integer> list = new CircularlyDoublyLinkedList<>();
        // Adding values to list
        for (int i = 1; i<11; i++){
            list.addAfterCursor(i);

        }
        // Printing list
        System.out.println(list);
        System.out.println();

        // Moving some amount, and deleting at that cursor
        while (!list.isEmpty()){
            list.advanceCursor(rand.nextInt(10));
            System.out.println("deleting value of: " + list.deleteCursor());
            System.out.println();
        }
    }
}
