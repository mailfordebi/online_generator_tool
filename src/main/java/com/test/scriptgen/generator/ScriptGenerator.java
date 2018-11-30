
package com.test.scriptgen.generator;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import liquibase.Liquibase;
import liquibase.database.OfflineConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

/**
 * This class generates sql scripts based on liquibase changelogs.
 */
@Component
public class ScriptGenerator {

	/** Contains all dialects for which script generation is supported. */
	private static final ArrayList<String> ALLOWED_DIALECTS = new ArrayList<>(Arrays.asList("db2", "oracle", "mssql"));

	/** Base name of all output files. */
	private static final String BASE_OUTPUT_FILE_NAME = "changelog";

	// Internal variables
	private FileSystemResourceAccessor resouceAccessor;

	// Input
	/**
	 * Name of the input file. Should match a file name that can be found on the
	 * classpath.
	 */
	private String inputFile;
	/**
	 * The output directory in which generated SQL is stored. If empty, the
	 * resulting SQL will be printed to the console.
	 */
	private File outputDirectory;
	/**
	 * String array of the dialects for which SQL is generated. Filled with db2,
	 * oracle, and mssql by default.
	 */
	private String[] dialects;
	/**
	 * List of the phases for which SQL is generated. Filled with every phase except
	 * for migration phases by default.
	 */
	private ArrayList<String> phases;
	
	private String returnString;

	/**
	 * Transforms command line arguments to instance variables.
	 */
	private boolean handleCommandlineArguments(String dbType) {
		inputFile = null;
		outputDirectory = null;
		dialects = null;
		phases = null;

		// Parse the commands that were given.
				// Check for input file argument.
				/*if (arg.startsWith("-i=") || arg.startsWith("-input=") || arg.startsWith("-inputFile=")) {
					inputFile = arg.split("=")[1];
					continue;
				}

				// Check for output file argument
				if (arg.startsWith("-o=") || arg.startsWith("-output=") || arg.startsWith("-outputDirectory=")) {
					String dirName = arg.split("=")[1];
					outputDirectory = new File(dirName);
					if (!outputDirectory.isDirectory()) {
						System.out.println(dirName + " is not a valid directory!");
						return false;
					}

					continue;
				}*/

				// Check for dbms argument
				if ("oracle".equals(dbType)) {
					dialects = new String[]{"oracle"};
				}
				if ("db2".equals(dbType)) {
					dialects = new String[]{"db2"};
				}
				if ("mssql".equals(dbType)) {
					dialects = new String[]{"mssql"};
				}
				if ("all".equals(dbType)) {
					dialects = ALLOWED_DIALECTS.toArray(new String[ALLOWED_DIALECTS.size()]);
				}

				// Check for phases argument
				/*if (arg.startsWith("-p=") || arg.startsWith("-phases=")) {
					String phasesArg = arg.split("=")[1];
					phases = new ArrayList<>(Arrays.asList(phasesArg.split(",")));
					continue;
				}*/

				// If we reach here, an illegal argument was passed.
				/*throw new IllegalArgumentException(
						"Unknown argument: " + arg + ". Only '-i', '-o', '-dbms', and '-phases' are allowed.");*/

		// If no values were passed, set the default values.
		// Set the input file.
		if (StringUtils.isEmpty(inputFile)) {
			inputFile = "temp.xml";
		}

		// Set the dbms dialects.
		/*if (dialects == null || dialects.length == 0) {
			dialects = ALLOWED_DIALECTS.toArray(new String[ALLOWED_DIALECTS.size()]);
		}*/

		// Set the phases.
		if (phases == null || phases.isEmpty()) {
			// Initialize the phases array.
			phases = new ArrayList<>();
			// For each phase, add the phase order to the list.
			IntStream.range(0, Phase.ALL_SCRIPT_PHASES.length)
					.forEach(i -> phases.add(Phase.ALL_SCRIPT_PHASES[i].getOrder().toString()));
		}

		return true;
	}

	/**
	 * Finishes initialization by checking this objects instance variables and
	 * initializing the {@link #resouceAccessor}.
	 */
	public boolean finishInitalization() {
		// Check the input file.
		if (StringUtils.isEmpty(inputFile)) {
			System.out.println("The input file is not set!");
			return false;
		}

		// Check the dbms dialects.
		if (dialects == null || dialects.length == 0) {
			System.out.println("The dialects are not set!");
			return false;
		}

		for (String dialect : dialects) {
			if (!ALLOWED_DIALECTS.contains(dialect)) {
				System.out.println("The dialect " + dialect + " is not an allowed dialect for script generation!");
				return false;
			}
		}

		// Check the phases.
		if (phases == null || phases.isEmpty()) {
			System.out.println("The phases are not set!");
			return false;
		}

		for (String phase : phases) {
			Integer p;
			try {
				p = Integer.valueOf(phase);
			} catch (NumberFormatException e) {
				System.out.println("The phase " + phase + " is not a number!");
				return false;
			}

			if (p < 0 || p > Phase.ORDER_OF_LAST_PHASE) {
				System.out.println("The phases should be larger than 0 and smaller than "
						+ String.valueOf(Phase.ORDER_OF_LAST_PHASE) + ".");
				return false;
			}
		}

		// Initialize the class loader for liquibase.
		resouceAccessor = new FileSystemResourceAccessor();

		return true;
	}

	/**
	 * Loops over all phases and database dialects that were set during
	 * initialization, and generates sql scripts for them using the given changelog
	 * file.
	 * 
	 * @throws LiquibaseException when liquibase fails to initialize.
	 * @throws IOException        when the output files could not be created / saved
	 *                            / read.
	 */
	public ArrayList<String> generateScripts() throws LiquibaseException, IOException {

		ArrayList<String> returnStrings=new ArrayList<>();
		// Scripts will be generated for each dialect and each phase that has been set.
		// Loop over the dialects first.
		for (String dialect : dialects) {
			// Build a liquibase object for the given dialect.
			Liquibase liquibase = buildLiquibase(dialect);

			// Loop over the phases
			for (String phase : phases) {
				//Writer writer = initializeWriter(determineFileName(dialect, phase));
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				Writer writer =new OutputStreamWriter(stream);
				liquibase.update(Phase.determineNameByOrder(Integer.valueOf(phase)), writer);
				String returnString=new String(stream.toByteArray());
				returnStrings.add(returnString);
			}
		}

		return returnStrings;
	}

	/**
	 * Build an instance of liquibase, which holds an offline connection to a
	 * database specified by the dialect.
	 */
	private Liquibase buildLiquibase(String dialect) throws LiquibaseException {
		// Build the url, which indicates liquibase should use an offline connection,
		// for a specific database, while still outputting insert statements for the
		// DatabaseChangelog table.
		String url = "offline:" + dialect + "?outputLiquibaseSql=true";

		Liquibase liquibase = new Liquibase(inputFile, resouceAccessor, new OfflineConnection(url, resouceAccessor));

		return liquibase;
	}

	/**
	 * Determines which writer to use for the output. This is a writer to the
	 * console if the output directory is empty, otherwise a writer for an output
	 * file.
	 */
	/*private Writer initializeWriter(String fileName) throws IOException {
		if (outputDirectory == null) {
			// Write to System.out
			File file = new File("temp.txt");
			file.createNewFile();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			return new OutputStreamWriter(stream);
		}

		return new BufferedWriter(new FileWriter(new File(outputDirectory, fileName)));
	}*/

	/**
	 * Determines the filename of the file in which the sql for a certain phase and
	 * dialect are stored.
	 */
	/*private String determineFileName(String dialect, String phase) {
		return BASE_OUTPUT_FILE_NAME + "-" + dialect + "-" + Phase.determineNameByOrder(Integer.valueOf(phase))
				+ ".sql";
	}*/

	/**
	 * This class generates SQL scripts based on changelog files, for any given
	 * combination of phases and database dialects. USAGE: java
	 * com.keylane.ScriptGenerator [-i=inputFileName] [-o=outputDirectory]
	 * [-dbms=db2,oracle,mssql] [-phases=1,2,5,6,7] All parameters are optional. The
	 * input file should refer to a changelog that is available on the classpath. If
	 * the output directory is empty, the output will be logged to the console. The
	 * default values are:
	 * <ul>
	 * <li>Input file name: changelog-master.xml</li>
	 * <li>Output directory: empty (write output to console/log)</li>
	 * <li>Dialects: db2, oracle, mssql (all three are generated)</li>
	 * <li>phases: 1, 2, 5, 6, 7 (sql is generated for all phases that
	 * conventionally use liquibase).</li>
	 * </ul>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*ScriptGenerator generator = new ScriptGenerator();

		try { // Process the command line arguments.
			if (!generator.handleCommandlineArguments(null)) {
				System.exit(1);
				return;
			}

			// Perform further initialization.
			if (!generator.finishInitalization()) {
				System.exit(1);
				return;
			}

			// Generate the scripts.
			if (!generator.generateScripts()) {
				System.exit(1);
				return;
			}

			System.exit(0);
		} catch (LiquibaseException le) {
			System.out.println("Could not initialize liquibase.");
			le.printStackTrace();
			System.exit(1);
		} catch (IOException ioe) {
			System.out.println("Could not write the output.");
			ioe.printStackTrace();
			System.exit(1);
		}*/

		 new ScriptGenerator().generate(null,null);
	}

	public List<String> generate(String xml, String dbType) {
		List<String> strings=null;
		File file = new File("temp.xml");
		System.out.println(xml);
		BufferedWriter bufferedWriter=null;
		try {
		file.createNewFile();
		Writer writer = new FileWriter(file);
		bufferedWriter= new BufferedWriter(writer);
        bufferedWriter.write(xml);
        bufferedWriter.close();
		ScriptGenerator generator = new ScriptGenerator();
			// Process the command line arguments.
			if (!generator.handleCommandlineArguments(dbType)) {
				//System.exit(1);
				return null;
			}
			// Perform further initialization.
			if (!generator.finishInitalization()) {
				//System.exit(1);
				return null;
			}

			// Generate the scripts.
			strings=generator.generateScripts();
			file.delete();
			//System.exit(0);
		} catch (LiquibaseException le) {
			System.out.println("Could not initialize liquibase.");
			le.printStackTrace();
			//System.exit(1);
		} catch (IOException ioe) {
			System.out.println("Could not write the output.");
			ioe.printStackTrace();
			//System.exit(1);
		}
		return strings;
	}
	

}
