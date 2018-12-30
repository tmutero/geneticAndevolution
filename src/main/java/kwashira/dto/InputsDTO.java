package kwashira.dto;

import java.io.File;

public class InputsDTO {
    String dataset;
    int trials;
    String algorithm;
    int population;
    String mutationType;
    String selectionType;
    int maxGeneration;
    String crossoverType;
    public String getCrossoverType() {
        return crossoverType;
    }
    public void setCrossoverType(String crossoverType) {
        this.crossoverType = crossoverType;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public void setMaxGeneration(int maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public int getTrials() {
        return trials;
    }

    public void setTrials(int trials) {
        this.trials = trials;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getMutationType() {
        return mutationType;
    }

    public void setMutationType(String mutationType) {
        this.mutationType = mutationType;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

}
