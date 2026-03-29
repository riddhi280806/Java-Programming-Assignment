// 1. Base Class
abstract class Match {
    protected int currentScore;
    protected double currentOvers;
    protected int targetScore;

    public Match(int currentScore, double currentOvers, int targetScore) {
        this.currentScore = currentScore;
        this.currentOvers = currentOvers;
        this.targetScore = targetScore;
    }

    // Common calculation for all matches
    public double calculateCurrentRunRate() {
        if (currentOvers == 0) return 0.0;
        return currentScore / currentOvers;
    }

    // Abstract methods to be implemented by child classes
    public abstract double calculateRequiredRunRate();
    public abstract void displayMatchDetails();
}

// 2. Derived Class: One Day International (50 Overs)
class ODIMatch extends Match {
    private static final int MAX_OVERS = 50;

    public ODIMatch(int currentScore, double currentOvers, int targetScore) {
        super(currentScore, currentOvers, targetScore);
    }

    @Override
    public double calculateRequiredRunRate() {
        double oversRemaining = MAX_OVERS - currentOvers;
        if (oversRemaining <= 0) return 0.0;
        int runsRequired = targetScore - currentScore;
        return runsRequired / oversRemaining;
    }

    @Override
    public void displayMatchDetails() {
        System.out.println("--- ODI Match Status (50 Overs) ---");
        System.out.printf("Current Score: %d in %.1f overs%n", currentScore, currentOvers);
        System.out.printf("Target: %d%n", targetScore);
        System.out.printf("Current Run Rate: %.2f%n", calculateCurrentRunRate());
        System.out.printf("Required Run Rate: %.2f%n", calculateRequiredRunRate());
        System.out.println("-----------------------------------\n");
    }
}

// 3. Derived Class: T20 Match (20 Overs)
class T20Match extends Match {
    private static final int MAX_OVERS = 20;

    public T20Match(int currentScore, double currentOvers, int targetScore) {
        super(currentScore, currentOvers, targetScore);
    }

    @Override
    public double calculateRequiredRunRate() {
        double oversRemaining = MAX_OVERS - currentOvers;
        if (oversRemaining <= 0) return 0.0;
        int runsRequired = targetScore - currentScore;
        return runsRequired / oversRemaining;
    }

    @Override
    public void displayMatchDetails() {
        System.out.println("--- T20 Match Status (20 Overs) ---");
        System.out.printf("Current Score: %d in %.1f overs%n", currentScore, currentOvers);
        System.out.printf("Target: %d%n", targetScore);
        System.out.printf("Current Run Rate: %.2f%n", calculateCurrentRunRate());
        System.out.printf("Required Run Rate: %.2f%n", calculateRequiredRunRate());
        System.out.println("-----------------------------------\n");
    }
}

// 4. Main Class to handle Command Line Arguments
public class CricketSystem {
    public static void main(String[] args) {
        // Check if the user provided the correct number of arguments
        if (args.length != 4) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java CricketSystem <Format> <CurrentScore> <CurrentOvers> <TargetScore>");
            System.out.println("Formats available: ODI, T20");
            System.out.println("Example: java CricketSystem T20 120 14.5 180");
            return;
        }

        try {
            // Parse command line arguments
            String matchFormat = args[0].toUpperCase();
            int currentScore = Integer.parseInt(args[1]);
            double currentOvers = Double.parseDouble(args[2]);
            int targetScore = Integer.parseInt(args[3]);

            Match match = null;

            // Instantiate the correct child class based on input
            switch (matchFormat) {
                case "ODI":
                    match = new ODIMatch(currentScore, currentOvers, targetScore);
                    break;
                case "T20":
                    match = new T20Match(currentScore, currentOvers, targetScore);
                    break;
                default:
                    System.out.println("Error: Unknown match format. Please use 'ODI' or 'T20'.");
                    return;
            }

            // Display using Polymorphism
            match.displayMatchDetails();

        } catch (NumberFormatException e) {
            System.out.println("Error: Score, Overs, and Target must be numeric values.");
        }
    }
}
