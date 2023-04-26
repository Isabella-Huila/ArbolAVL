import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArbolAVL arbolAVL= new ArbolAVL();
        Scanner scanner = new Scanner(System.in);
        int n= 5;

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i=0; i < n; n++){
            numbers.clear();
            String line = scanner.nextLine();
            String[] numbersString = line.split(" ");
            for (String numberString : numbersString) {
                int number = Integer.parseInt(numberString);
                numbers.add(number);
            }
            if (numbers.get(0) == 1){
                arbolAVL.insertar(numbers.get(1));
                arbolAVL.levelOrder();
            }
            System.out.println();
        }

    }
}