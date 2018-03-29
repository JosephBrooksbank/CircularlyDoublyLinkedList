import java.util.Random;

public class Driver {



    public static void main(String[] args) {

        Random rand = new Random();
        CircularlyDoublyLinkedList<Integer> list = new CircularlyDoublyLinkedList<>();
        for (int i = 1; i<11; i++){
            list.addAfterCursor(i);

        }
        System.out.println(list);
        System.out.println();
        while (!list.isEmpty()){
            list.advanceCursor(rand.nextInt(10));
            System.out.println(list.deleteCursor());
        }
    }
}
