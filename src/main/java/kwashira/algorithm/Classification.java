package kwashira.algorithm;




import kwashira.data.Patient;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.*;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Variable;
import org.jgap.gp.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;



public class Classification extends GPProblem {

    public static Variable ct;
    public static Variable usz;
    public static Variable ushp;
    public static Variable ma;
    public static Variable sesz;
    public static Variable bn;
    public static Variable bc;
    public static Variable nn;
    public static Variable m;
    public static List<Patient> patients;

    public Classification(List<Patient> patients, GPConfiguration config)
	    throws InvalidConfigurationException {
	super(config);
	Classification.patients = new ArrayList<Patient>(patients);
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
	GPConfiguration conf = getGPConfiguration();
	// At first, we define the return type of the GP program.
	// ------------------------------------------------------
	Class[] types = {
		// Return type of result-producing chromosome
		CommandGene.IntegerClass,
		// ADF-relevant:
		// Return type of ADF 1
		CommandGene.IntegerClass };
	// Then, we define the arguments of the GP parts. Normally, only for
	// ADF's
	// there is a specification here, otherwise it is empty as in first
	// case.
	// -----------------------------------------------------------------------
	Class[][] argTypes = {
		// Arguments of result-producing chromosome: none
		{},
		// ADF-relevant:
		// Arguments of ADF1: all 3 are float
		{ CommandGene.IntegerClass, CommandGene.IntegerClass, CommandGene.IntegerClass } };

	CommandGene[][] nodeSets = {
		{
			// We use variables that can be set in the fitness
			// function.
			// ----------------------------------------------------------
			ct = Variable.create(conf, "ct", CommandGene.IntegerClass),
			usz = Variable.create(conf, "usz", CommandGene.IntegerClass),
			ushp = Variable.create(conf, "ushp", CommandGene.IntegerClass),
			ma = Variable.create(conf, "ma", CommandGene.IntegerClass),
			sesz = Variable.create(conf, "sesz", CommandGene.IntegerClass),
			bn = Variable.create(conf, "bn", CommandGene.IntegerClass),
			bc = Variable.create(conf, "bc", CommandGene.IntegerClass),
			nn = Variable.create(conf, "nn", CommandGene.IntegerClass),
			m = Variable.create(conf, "m", CommandGene.IntegerClass),
			new Multiply(conf, CommandGene.IntegerClass), new Add(conf, CommandGene.IntegerClass),
			new Divide(conf, CommandGene.IntegerClass), new Subtract(conf, CommandGene.IntegerClass),
			new Terminal(conf, CommandGene.IntegerClass, -10, 10, true), },
		// ADF-relevant:
		// and now the definition of ADF(1)
		{ new Add3(conf, CommandGene.IntegerClass), } };

	return GPGenotype.randomInitialGenotype(conf, types, argTypes, nodeSets, 20, true);
    }

    public static class FitnessFunction extends GPFitnessFunction {

	@Override
	protected double evaluate(final IGPProgram program) {
	    return computeFitness(program);
	}

	/**
	 * This function checks a program by running it over all the patients and seeing how many it gets correct.
	 * 
	 * @param program The program that the genetic algorithm has evolved
	 * @return A percent of how many the program correctly classified
	 */
	public double computeFitness(final IGPProgram program) {
	    double correct = 0; // Double so we can calculate a percent

	    for (int i = 0; i < patients.size(); i++) {
		ct.set(patients.get(i).getCt());
		usz.set(patients.get(i).getUshp());
		ushp.set(patients.get(i).getUshp());
		ma.set(patients.get(i).getMa());
		sesz.set(patients.get(i).getSesz());
		bn.set(patients.get(i).getBn());
		bc.set(patients.get(i).getBc());
		nn.set(patients.get(i).getNn());
		m.set(patients.get(i).getM());

		try {
		    int result = program.execute_int(0, new Object[0]);
		    int Class = 0;

		    if (result < 0) {
			Class = Patient.MALIGNANT;
		    } else {
			 Class = Patient.BENIGN;
		    }

		    if (Class == patients.get(i).getAClass()) {
			correct++;
		    }
		} catch (ArithmeticException exception) {
		    // This should not happen, some illegal operation was
		    // executed.
		    // ------------------------------------------------------------
		    System.out.println("x = " + patients.get(i).toString());
		    System.out.println(program);
		    throw exception;
		}

	    }
	    return (correct / patients.size()) * 100;
	}

    }

}
