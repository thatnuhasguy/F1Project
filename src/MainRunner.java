import java.util.Scanner;
public class MainRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello and welcome to the F1 Simulation game, where two players battle each other for the win on the final lap of the race.");
        System.out.print("Player 1, please enter your name: ");
        String player1= scan.nextLine();
        System.out.print("Player 2, please enter your name: ");
        String player2= scan.nextLine();
        System.out.println("Now, how many turns does the said track contain? (enter an integer)");
        int turns= scan.nextInt();
        F1Simulator simulator = new F1Simulator(player1,player2,turns);
        simulator.startRace();


    }
}
