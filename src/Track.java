public class Track {
    private int numberOfLaps;
    private int numberOfCorners;

    public Track(int numberOfLaps, int numberOfCorners) {
        this.numberOfLaps = numberOfLaps;
        this.numberOfCorners = numberOfCorners;
    }

    public int getNumberOfLaps() {
        return numberOfLaps;
    }

    public int getNumberOfCorners() {
        return numberOfCorners;
    }

    public void displayTrackInfo() {
        System.out.println("Track Details:");
        System.out.println("Number of Laps: " + getNumberOfLaps());
        System.out.println("Number of Corners: " + getNumberOfCorners());
    }
    public void displayTrackInfo(int numberOfCorners, int numberOfLaps) {
        System.out.println("Track Details:");
        System.out.println("Number of Laps: " + numberOfLaps);
        System.out.println("Number of Corners: " + numberOfCorners);
    }
}
