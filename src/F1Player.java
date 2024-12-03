public class F1Player {
    private String name;
    private double time;
    private boolean crashed;

    public F1Player(String name){
        this.name=name;
        time =0;
        crashed=false;
    }
    public String getName() {
        return name;
    }
    public double getTime() {
        return time;
    }
    public void increaseTime(double increment){
        time +=increment;
    }
    public void setCrashed(){
        crashed=true;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public double aggressiveTurn() {
        double randomFactor = Math.random();
        double baseLapTime = 100.0;

        if (randomFactor < 0.3) {
            setCrashed();
            return -1; // Crash
        } else if (randomFactor < 0.7) {
            return baseLapTime - 15; // Successful aggressive turn
        } else {
            return baseLapTime + 10; // Partial failure
        }
    }

    public double moderateTurn() {
        double randomFactor = Math.random();
        double baseLapTime = 100.0;

        if (randomFactor < 0.2) {
            setCrashed();
            return -1; // Crash
        } else if (randomFactor < 0.8) {
            return baseLapTime - 10; // Successful moderate turn
        } else {
            return baseLapTime + 5; // Partial failure
        }
    }

    public double defensiveTurn() {
        double randomFactor = Math.random();
        double baseLapTime = 100.0;

        if (randomFactor < 0.1) {
            setCrashed();
            return -1; // Crash
        } else if (randomFactor < 0.9) {
            return baseLapTime - 5; // Successful defensive turn
        } else {
            return baseLapTime + 3; // Partial failure
        }
    }

    // Main method to calculate lap time based on strategy
    public double calculateLapTime(int strategy) {
        if (strategy == 1) {
            return aggressiveTurn();
        } else if (strategy == 2) {
            return moderateTurn();
        } else if (strategy == 3) {
            return defensiveTurn();
        } else {
            System.out.println("Invalid strategy.");
            return 0.0;
        }
    }
}
