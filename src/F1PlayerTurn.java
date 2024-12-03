public class F1PlayerTurn {
    private int strategy;

    public F1PlayerTurn(int strategy) {
        this.strategy = strategy;
    }

    public String executeTurn(F1Player player) {
        double lapTime = player.calculateLapTime(strategy);

        if (player.isCrashed()) {
            return player.getName() + " crashed while attempting their turn!";
        } else {
            player.increaseTime(lapTime);
            return player.getName() + " completed the lap in " + lapTime + " seconds.";
        }
    }
}

