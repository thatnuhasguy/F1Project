public class Player {
    private String name;
    private double totalLapTime;
    private boolean collided;

    // Overloaded constructors
    public Player(String name) {
        this.name = name;
        this.totalLapTime = 0.0;
        this.collided = false;
    }

    public Player(String first, String last) {
        this.name = first+ " "+ last;
        this.totalLapTime = 0.0;
        this.collided = false;
    }

    public String getName() {
        return name;
    }

    public double getTotalLapTime() {
        return totalLapTime;
    }

    public boolean hasCollided() {
        return collided;
    }

    public void addLapTime(double lapTime) {
        this.totalLapTime += lapTime;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }


}
