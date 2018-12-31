package kwashira.data;


import org.jgap.gp.IGPProgram;

public class Result {
	public double percentageAccuracy;
	public  long timeTaken;
	public int generations;
	public int iterations;

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public Result(double percentageAccuracy) {

        this.percentageAccuracy = percentageAccuracy;
    }

    public Result(int iterations, double percentageAccuracy, long timeTaken, int generations) {
        this.percentageAccuracy = percentageAccuracy;
        this.timeTaken = timeTaken;
        this.generations = generations;
        this.iterations= iterations;
    }

    public double getPercentageAccuracy() {
        return percentageAccuracy;
    }

    public void setPercentageAccuracy(double percentageAccuracy) {
        this.percentageAccuracy = percentageAccuracy;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    @Override
    public String toString() {
        return "Result{" +
                "percentageAccuracy=" + percentageAccuracy +
                ", timeTaken=" + timeTaken +
                ", generations=" + generations +
                '}';
    }
}
