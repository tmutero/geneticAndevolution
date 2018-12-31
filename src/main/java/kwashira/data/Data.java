package kwashira.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Data {
    // The default location for the data file
    public static final String defaultFileLocation = "data/test";

    // Our collection of cancer patients from the data file
    private List<Patient> patients = new ArrayList<>();

    /**
     * Construct a new Data object. This data object will automatically read all
     * the data in from the file specified or will use the default file location
     * 
     * @param fileName - The name of the file holding all the data
     * @throws FileNotFoundException
     */
    public Data(String fileName) throws FileNotFoundException {
	if (fileName == null || fileName.isEmpty()) {
	    readFile(defaultFileLocation);
	} else {
	    readFile(fileName);
	}
    }

    public List<Patient> getPatients() {
	return new ArrayList<>(patients);
    }

    /*
     * Read all of our data in from the file provided
     * 
     * @param fileName The name of the file holding the data 
     * @throws FileNotFoundException
     */
    private void readFile(String fileName) throws FileNotFoundException {
	Scanner fileInput = new Scanner(new File(fileName));

	while (fileInput.hasNextLine()) {
	    Scanner line = new Scanner(fileInput.nextLine());
	    line.useDelimiter(",");

	    // Create a new patient builder with an id from the data file and then read the reset of the data
	    Patient.PatientBuilder patientBuilder = new Patient.PatientBuilder(line.nextInt()).ct(line.nextInt()).usz(line.nextInt())
		    .ushp(line.nextInt()).ma(line.nextInt()).sesz(line.nextInt()).bn(line.nextInt()).bc(line.nextInt())
		    .nn(line.nextInt()).m(line.nextInt()).Class(line.nextInt());

	    line.close();

	    patients.add(patientBuilder.build());
	}
	fileInput.close();
	System.out.println("Read " + patients.size() + "  patients information");
    }
}
