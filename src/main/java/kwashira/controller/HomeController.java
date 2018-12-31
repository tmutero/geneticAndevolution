package kwashira.controller;


import kwashira.algorithm.Classification;
import kwashira.data.Data;
import kwashira.data.Result;
import kwashira.dto.InputsDTO;
import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private static final String defaultTrainingFile = "datasets/training.txt";
    private static final String defaultTestFile = "datasets/test.txt";
    public static final int maxGenerations = 100; // Maximum number of generations to evolve
    public static final int stepSize = 1; // The number of times to evolve in one step (iteration)
    public static final String DATASETS_PATH = "datasets/";
    String[] fileNames;
    long endIterationsMillis, endTimeMillis;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        List<String> results = new ArrayList<String>();
        File[] files = new File(DATASETS_PATH).listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());

            }
        }
        model.addAttribute("files", results);
        model.addAttribute("inputsDTO", new InputsDTO());
        return "index";
    }


    @PostMapping
    public String process(@ModelAttribute("inputsDTO") @Valid InputsDTO inputsDTO,
                          BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        Data trainingData;
        Data testData;
        long startTimeMillis;
        List<Result> runQueue = new ArrayList<>();
        System.out.println("Training data being read from: " + defaultTrainingFile);
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTimeMillis = System.currentTimeMillis();

        try {
            trainingData = new Data("datasets/training.txt");


            //System.out.println("Test data being read from: " + defaultTestFile);

            //data beign loaded here test Data
            testData = new Data(DATASETS_PATH + inputsDTO.getDataset());
            for (int k = 0; k < inputsDTO.getTrials(); k++) {
                // Set up Genetic Program configuration
                GPConfiguration config = new GPConfiguration();
                config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
                config.setMaxInitDepth(6);
                config.setPopulationSize(inputsDTO.getPopulation());
                config.setMaxCrossoverDepth(6);
                config.setFitnessFunction(new Classification.FitnessFunction());
                config.setStrictProgramCreation(true);

                switch (inputsDTO.getMutationType()) {
                    case "singleInvert":
                        config.setMutationProb(25f);
                        break;
                    case "doubleInvert":
                        config.setMutationProb(50f);
                        break;
                    case "hyperMutate":
                        config.setMutationProb(75f);
                        break;
                    default:
                        config.setMutationProb(1f);
                        break;
                }

                config.setCrossoverProb(90f);

                // Create our genetic program
                GPProblem problem = new Classification(trainingData.getPatients(), config);
                GPGenotype geneticProgram = problem.create();
                geneticProgram.setVerboseOutput(true);

                /*
                 * Run genetic program and stop either at max generations or when program obtains a fitness function result at
                 * or above 98%
                 */


                for (int i = 0; i < inputsDTO.getMaxGeneration(); i += 2) {
                    geneticProgram.evolve(stepSize);
                    if (geneticProgram.getAllTimeBest() != null && geneticProgram.getAllTimeBest().getFitnessValue() >= 98) {
                        System.out.println("\nStopping as fitness value of current solutions is greater or equal to 98%");
                        System.out.println(i + " generations evolved\n");
                        break;
                    }
                }
                geneticProgram.outputSolution(geneticProgram.getAllTimeBest());

                // Check the best program against test data
                Classification.patients = testData.getPatients();


                double percentCorrect = new Classification.FitnessFunction().computeFitness(geneticProgram
                        .getAllTimeBest());


                endIterationsMillis = System.currentTimeMillis() - startTimeMillis;

                System.out.printf("\nCorrect Classification Percent: %.2f%%", percentCorrect);
                Result result2 = new Result(k, percentCorrect, endIterationsMillis, 34);

                runQueue.add(result2);

                Configuration.reset();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }


        endTimeMillis = System.currentTimeMillis() - startTimeMillis;

        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            long actualMemUsed = afterUsedMem - beforeUsedMem;
 model.addAttribute("endTimeMillis", endTimeMillis);
        model.addAttribute("actualMemUsed", actualMemUsed);
        model.addAttribute("algorithm", inputsDTO.getAlgorithm());
        model.addAttribute("selection", inputsDTO.getSelectionType());
        model.addAttribute("crossover", inputsDTO.getCrossoverType());
        model.addAttribute("mutation", inputsDTO.getMutationType());
        model.addAttribute("population", inputsDTO.getPopulation());
        model.addAttribute("dataset", inputsDTO.getDataset());
        model.addAttribute("runQueue", runQueue);

        return "result";
    }
}










