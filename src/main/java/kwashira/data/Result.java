package kwashira.data;


import org.jgap.gp.IGPProgram;

public class Result {
	public double percentageAccuracy;
	public  long timeTaken;
	public IGPProgram generations;

    public Result(double percentageAccuracy) {

        this.percentageAccuracy = percentageAccuracy;
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

    public IGPProgram getGenerations() {
        return generations;
    }

    public void setGenerations(IGPProgram generations) {
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
