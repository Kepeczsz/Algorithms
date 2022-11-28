import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k =0;
        String word = scanner.next();
        do{
            k++;
            System.out.println(k + " " + word)  ;
        }while(scanner.hasNext());

    }
}