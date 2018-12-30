package kwashira.data;


public class Patient {

    private int id;	//Sample ID
    private int ct; // Clump thickness
    private int usz; // Uniformity of Cell Size
    private int ushp; // Uniformity of Cell Shape
    private int ma; // Marginal Adhesion
    private int sesz; // SingleEpithelial Cell Size
    private int bn; // Bare Nuclei
    private int bc; // Bland Chromatin
    private int nn; // Normal Nucleoli
    private int m; // Mitoses
    private int AClass; //  class - 2 for benign, 4 for malignant

    public static final int BENIGN = 2;
    public static final int MALIGNANT = 4;

    private Patient(PatientBuilder builder) {
	this.id = builder.id;
	this.ct = builder.ct;
	this.usz = builder.usz;
	this.ushp = builder.ushp;
	this.ma = builder.ma;
	this.sesz = builder.sesz;
	this.bn = builder.bn;
	this.bc = builder.bc;
	this.nn = builder.nn;
	this.m = builder.m;
	this.AClass = builder.Class;
    }

    /**
     * Sample ID
     * @return
     */
    public int getId() {
	return id;
    }

    /**
     * Get Clump thickness
     * 
     * @return
     */
    public int getCt() {
	return ct;
    }

    /**
     * Get Uniformity of Cell Size
     * 
     * @return
     */
    public int getUsz() {
	return usz;
    }

    /**
     * Get Uniformity of Cell Shape
     * 
     * @return
     */
    public int getUshp() {
	return ushp;
    }

    /**
     * Get Marginal Adhesion
     * 
     * @return
     */
    public int getMa() {
	return ma;
    }

    /**
     * Get SingleEpithelial Cell Size
     * 
     * @return
     */
    public int getSesz() {
	return ma;
    }

    /**
     * Get Bare Nuclei
     * 
     * @return
     */
    public int getBn() {
	return bn;
    }

    /**
     * Get Bland Chromatin
     * 
     * @return
     */
    public int getBc() {
	return bc;
    }

    /**
     * Get Normal Nucleoli
     * 
     * @return
     */
    public int getNn() {
	return nn;
    }

    /**
     * Get Mitoses
     * 
     * @return
     */
    public int getM() {
	return m;
    }

    /**
     * Cancer Class - 2 for benign, 4 for malignant
     * 
     * @return
     */
    public int getAClass() {
	return AClass;
    }

    @Override
    public String toString() {
	return "Patient - id: " + id + " ct: " + ct + " usz: " + usz
		+ " ushp: " + ushp + " ma: " + ma + " sesz: " + sesz + " bn: " + bn + " bc: " + bc
		+ " nn: " + nn + " m: " + m + " class: " + AClass;
    }

    
    public static class PatientBuilder {
	private int id;
	private int ct; // Clump thickness
	private int usz; // Uniformity of Cell Size
	private int ushp; // Uniformity of Cell Shape
	private int ma; // Marginal Adhesion
	private int sesz; // SingleEpithelial Cell Size
	private int bn; // Bare Nuclei
	private int bc; // Bland Chromatin
	private int nn; // Normal Nucleoli
	private int m; // Mitoses
	private int Class; // Class - 2 for benign, 4 for malignant

	/**
	 * Construct a new Cancer patient builder.
	 * @param id The sample id
	 */
	public PatientBuilder(int id) {
	    this.id = id;
	}

	/**
	 * Clump thickness
	 * 
	 * @param ct Clump thickness
	 * @return
	 */
	public PatientBuilder ct(int ct) {
	    this.ct = ct;
	    return this;
	}

	/**
	 * Uniformity of Cell Size
	 * 
	 * @param usz Uniformity of Cell Size
	 * @return
	 */
	public PatientBuilder usz(int usz) {
	    this.usz = usz;
	    return this;
	}

	/**
	 * Uniformity of Cell Shape
	 * 
	 * @param ushp Uniformity of Cell Shape
	 * @return
	 */
	public PatientBuilder ushp(int ushp) {
	    this.ushp = ushp;
	    return this;
	}

	/**
	 * Marginal Adhesion
	 * 
	 * @param ma Marginal Adhesion
	 * @return
	 */
	public PatientBuilder ma(int ma) {
	    this.ma = ma;
	    return this;
	}

	/**
	 * SingleEpithelial Cell Size
	 * 
	 * @param sesz SingleEpithelial Cell Size
	 * @return
	 */
	public PatientBuilder sesz(int sesz) {
	    this.sesz = sesz;
	    return this;
	}

	/**
	 * Bare Nuclei
	 * 
	 * @param bn Bare Nuclei
	 * @return
	 */
	public PatientBuilder bn(int bn) {
	    this.bn = bn;
	    return this;
	}

	/**
	 * Bland Chromatin
	 * 
	 * @param bc Bland Chromatin
	 * @return
	 */
	public PatientBuilder bc(int bc) {
	    this.bc = bc;
	    return this;
	}

	/**
	 * Normal Nucleoli
	 * 
	 * @param nn Normal Nucleoli
	 * @return
	 */
	public PatientBuilder nn(int nn) {
	    this.nn = nn;
	    return this;
	}

	/**
	 * Mitoses
	 * 
	 * @param m Mitoses
	 * @return
	 */
	public PatientBuilder m(int m) {
	    this.m = m;
	    return this;
	}


	public PatientBuilder Class(int Class) {
	    this.Class = Class;
	    return this;
	}

	/**
	 * Return a new Cancer Patient based on the values set on this builder
	 * object
	 * 
	 * @return A new Cancer Patient
	 */
	public Patient build() {
	    return new Patient(this);
	}
    }

}
