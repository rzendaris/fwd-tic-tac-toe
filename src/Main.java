import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int num;

        System.out.println("Enter the number of dimension: ");
        Scanner s = new Scanner(System.in);
        num = s.nextInt();

        new TicTacToeGame(num);
    }
}