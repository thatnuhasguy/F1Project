import java.util.Random;
import java.util.Scanner;

public class RaceLogic {
    private Player player1;
    private Player player2;
    private Track track;
    private Random random = new Random();
    private Scanner scan = new Scanner(System.in);

    public RaceLogic() {
        Scanner inputScanner = new Scanner(System.in);
        // Gather input for players
        System.out.print("Enter Player 1 name: ");
        String player1Name = inputScanner.nextLine();
        if(player1Name.equalsIgnoreCase("")){
            player1= new Player("Lewis", "Hamilton");
        } else {
            player1 = new Player(player1Name);
        }
        System.out.print("Enter Player 2 name: ");
        String player2Name = inputScanner.nextLine();
        if(player2Name.equalsIgnoreCase("")){
            player2= new Player("Max", "Verstappen");
        } else {
            player1 = new Player(player1Name);
        }
        // Gather input for track details
        System.out.print("Enter number of laps: ");
        int laps = inputScanner.nextInt();
        System.out.print("Enter number of corners per lap: ");
        int corners = inputScanner.nextInt();
        track = new Track(laps, corners);
    }

    public void start() {
        track.displayTrackInfo();
        for (int lap = 1; lap <= track.getNumberOfLaps(); lap++) {
            System.out.println("\n--- Lap " + lap + " ---");
            // Simulate laps for both players
            boolean redFlag = simulateLap(player1);
            if (!redFlag) {
                redFlag = simulateLap(player2);
            }
            if (redFlag) {
                System.out.println("\nRed Flag! The race is stopped due to a crash.");
                break;
            }
            checkDRS(); //checks the ability to use DRS at the end of the lap (not realistic, but we will carry on with this format)
        }
        determineWinner();
    }

    private boolean simulateLap(Player player) {
        double lapTime = 0.0;
        boolean collision = false;
        for (int corner = 1; corner <= track.getNumberOfCorners(); corner++) {
            int choice = getPlayerStrategy(player, corner);
            lapTime += calculateCornerTime(choice);
            // Check for crashes
            if (choice == 1 && random.nextBoolean()) { // Divebombing is risky. Random.nextBoolean represents a 50/50 chance when it comes to divebomb.
                collision = true;
                player.setCollided(true);
                System.out.println(player.getName() + " has crashed at corner " + corner + "!");
                break;
            }
        }
        if (!collision) { //if there is no collision, laptimes will be posted along with the player that completed it.
            player.addLapTime(lapTime);
            System.out.println(player.getName() + " finished the lap with time: " + lapTime);
        }
        return collision; // gets passed back into start() method to see if a crash occurred. Race will be red flagged accordingly.
    }

    private int getPlayerStrategy(Player player, int corner) {
        System.out.println(player.getName() + ", choose your strategy for corner " + corner + ":");
        System.out.println("1: Divebomb (Risky but fastest)");
        System.out.println("2: Ideal Line (Balanced)");
        System.out.println("3: Early Braking (Safest but slowest)");
        return scan.nextInt();
    }

    private double calculateCornerTime(int strategy) {
        if(strategy==1){
            return 3.0;
        } else if(strategy==2){
            return 4.0;
        } else{
            return 5.0;
        }
    }

    private void checkDRS() {
        double timeDifference = Math.abs(player1.getTotalLapTime() - player2.getTotalLapTime());
        double p1Lap= player1.getTotalLapTime();
        double p2Lap= player2.getTotalLapTime();
        Player behind;
        Player ahead;
        if(p1Lap<p2Lap){  // player with the most time is behind and player with the least time is in front
             behind = player2;
             ahead = player1;
        } else{
             behind= player1;
             ahead= player2;
        }
        if (timeDifference <= 1.0) {
            System.out.println("DRS activated for " + behind.getName() + "! Attempting overtake...");
            if (random.nextBoolean()) {
                System.out.println("Overtake successful! " + behind.getName() + " overtakes " + ahead.getName());
        }
        }
    }

    private void determineWinner() {
        System.out.println("\nFinal Results:");
        System.out.println(player1.getName() + "'s Final Time: " + player1.getTotalLapTime());
        System.out.println(player2.getName() + "'s Final Time: " + player2.getTotalLapTime());
        if (player1.getTotalLapTime() < player2.getTotalLapTime()) {
            System.out.println("Winner: " + player1.getName());
        } else if (player2.getTotalLapTime() < player1.getTotalLapTime()) {
            System.out.println("Winner: " + player2.getName());
        } else {
            System.out.println("It's a tie!");
        }
        if (player1.hasCollided()) {
            System.out.println(player1.getName() + " had a collision during the race.");
        }
        if (player2.hasCollided()) {
            System.out.println(player2.getName() + " had a collision during the race.");
        }
    }
}