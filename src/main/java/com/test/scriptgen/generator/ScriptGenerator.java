
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

	// Internal variables
	private FileSystemResourceAccessor resouceAccessor;

	/**
	 * Name of the input file. Should match a file name that can be found on the
	 * classpath.
	 */
	private String inputFile;

	/**
	 * String array of the dialects for which SQL is generated. Filled with db2,
	 * oracle, and mssql by default.
	 */
	private String[] dialects;

	/**
	 * Transforms command line arguments to instance variables.
	 */
	private boolean handleCommandlineArguments(String dbType) {
		inputFile = null;
		dialects = null;

		// Check for dbms argument
		if ("oracle".equals(dbType)) {
			dialects = new String[] { "oracle" };
		}
		if ("db2".equals(dbType)) {
			dialects = new String[] { "db2" };
		}
		if ("mssql".equals(dbType)) {
			dialects = new String[] { "mssql" };
		}
		if ("all".equals(dbType)) {
			dialects = ALLOWED_DIALECTS.toArray(new String[ALLOWED_DIALECTS.size()]);
		}

		// Set the input file.
		if (StringUtils.isEmpty(inputFile)) {
			inputFile = "temp.xml";
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

		ArrayList<String> returnStrings = new ArrayList<>();
		// Scripts will be generated for each dialect and each phase that has been set.
		// Loop over the dialects first.
		for (String dialect : dialects) {
			// Build a liquibase object for the given dialect.
			Liquibase liquibase = buildLiquibase(dialect);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Writer writer = new OutputStreamWriter(stream);
			liquibase.update("online_changes", writer);
			String returnString = new String(stream.toByteArray());
			returnStrings.add(returnString);
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

	public static void main(String[] args) {
		new ScriptGenerator().generate(null, null);
	}

	public List<String> generate(String xml, String dbType) {
		List<String> strings = null;
		File file = new File("temp.xml");
		System.out.println(xml);
		BufferedWriter bufferedWriter = null;
		try {
			file.createNewFile();
			Writer writer = new FileWriter(file);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(xml);
			bufferedWriter.close();
			ScriptGenerator generator = new ScriptGenerator();
			// Process the command line arguments.
			if (!generator.handleCommandlineArguments(dbType)) {
				// System.exit(1);
				return null;
			}
			// Perform further initialization.
			if (!generator.finishInitalization()) {
				return null;
			}

			// Generate the scripts.
			strings = generator.generateScripts();
			file.delete();
		} catch (LiquibaseException le) {
			System.out.println("Could not initialize liquibase.");
			le.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Could not write the output.");
			ioe.printStackTrace();
		}
		return strings;
	}

}
