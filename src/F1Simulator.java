import java.util.Scanner;
public class F1Simulator {
    private F1Player player1;
    private F1Player player2;
    private Track track;
    private Scanner scanner;

    public F1Simulator(String player1Name, String player2Name, int totalLaps) {
        this.player1 = new F1Player(player1Name);
        this.player2 = new F1Player(player2Name);
        this.track = new Track(totalLaps);
        this.scanner = new Scanner(System.in);
    }

    public void startRace() {
        for (int lap = 1; lap <= track.getTotalLaps(); lap++) {
            System.out.println("\n--- Lap " + lap + " ---");

            if (!player1.isCrashed()) {
                System.out.println("\n" + player1.getName() + "'s Turn:");
                executeTurn(player1);
            }

            if (!player2.isCrashed()) {
                System.out.println("\n" + player2.getName() + "'s Turn:");
                executeTurn(player2);
            }

            if (player1.isCrashed() && player2.isCrashed()) {
                System.out.println("\nBoth players have crashed! The race is over.");
                return;
            }
        }

        determineWinner();
    }

    private void executeTurn(F1Player player) {
        System.out.println("Choose your strategy:");
        System.out.println("1. Aggressive Turn");
        System.out.println("2. Moderate Turn");
        System.out.println("3. Defensive Turn");

        int choice = scanner.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            choice = scanner.nextInt();
        }

        F1PlayerTurn turn = new F1PlayerTurn(choice);
        String result = turn.executeTurn(player);
        System.out.println(result);
    }

    private void determineWinner() {
        System.out.println("\n--- Race Results ---");
        if (player1.isCrashed()) {
            System.out.println(player2.getName() + " wins as " + player1.getName() + " crashed!");
        } else if (player2.isCrashed()) {
            System.out.println(player1.getName() + " wins as " + player2.getName() + " crashed!");
        } else if (player1.getTime() < player2.getTime()) {
            System.out.println(player1.getName() + " wins with a total time of " + player1.getTime() + " seconds!");
        } else if (player2.getTime() < player1.getTime()) {
            System.out.println(player2.getName() + " wins with a total time of " + player2.getTime() + " seconds!");
        } else {
            System.out.println("It's a tie! Both players have a total time of " + player1.getTime() + " seconds!");
        }
    }

    public static void main(String[] args) {
        F1Simulator simulator = new F1Simulator("Player 1", "Player 2", 5);
        simulator.startRace();
    }
}
