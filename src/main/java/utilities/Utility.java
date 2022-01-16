package utilities;

import java.io.File;

public class Utility {

	// Create the report path if it does not exist
	public static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}

	public static void cleanDirectory(String path) {

		File directory = new File(path);
		if (directory.exists()) {
			directory.delete();
		}
		directory.mkdir();
	}
}
