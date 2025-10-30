import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * GamersFileIo
 * 
 * Handles reading gamer information from a CSV file and converting each record
 * into a Gamer object.
 * Provides methods to load all gamers and to count the number of gamer records.
 */
public class GamersFileIO {

	private String filePath; // Path to the CSV file containing gamer data

	/**
	 * Default constructor sets filePath to null.
	 */
	public GamersFileIO() {
	}

	/**
	 * Copy constructor
	 *
	 * @param gamersFileIO an object
	 */
	public GamersFileIO(GamersFileIO gamersFileIO) {
		this(gamersFileIO.getFilePath());
	}

	/**
	 * Constructor with file path.
	 * 
	 * @param filePath Path to the CSV file
	 */
	public GamersFileIO(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Reads all gamer records from the CSV file and returns them as an array of
	 * Gamer objects.
	 * Skips the header row, parses each line, and handles malformed lines
	 * gracefully.
	 * 
	 * @return Array of Gamer objects parsed from the file
	 * @throws IOException If an I/O error occurs
	 */
	public Gamer[] getGamers() throws IOException {

		int count = getRowCount(); // Get the number of gamer records (excluding header)
		Gamer[] gamerArray = new Gamer[count];

		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			// Skip the header
			inputStream.readLine();
			for (int i = 0; i < count; i++) {
				String csvLine = inputStream.readLine();
				gamerArray[i] = parseGamer(csvLine); // Parse each line into a Gamer object
			}

		} finally {
			// Ensure resources are closed to prevent memory leaks
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return gamerArray;
	}

	/**
	 * Counts the number of data rows (excluding the header) in the CSV file.
	 * 
	 * @return Number of data rows (gamers) in the file
	 * @throws IOException If file reading encounters an error
	 */
	private int getRowCount() throws IOException {
		BufferedReader inputStream = null;
		int count = 0;

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			// Skip the header
			inputStream.readLine();
			while (inputStream.readLine() != null) {
				count++; // Increment count for each non-header line
			}
		} finally {
			// Ensure resources are closed
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return count;
	}

	/**
	 * Parses a single CSV line into a Gamer object.
	 * Handles conversion of string values to their respective types.
	 * Handles and reports malformed lines gracefully.
	 * 
	 * @param csvLine The CSV-formatted line representing a gamer
	 * @return A Gamer object, or null if the line is invalid or incomplete
	 */
	private Gamer parseGamer(String csvLine) {
		try {
			StringTokenizer tokenizer = new StringTokenizer(csvLine, ",");

			int id = Integer.parseInt(tokenizer.nextToken());
			String nickname = tokenizer.nextToken();
			String name = tokenizer.nextToken();
			String phone = tokenizer.nextToken();
			int exp = Integer.parseInt(tokenizer.nextToken());

			return new Gamer(id, nickname, name, phone, exp);
		} catch (NumberFormatException e) {
			// Handles cases where integer parsing fails
			System.out.println("Warning: Skipping invalid gamer line. Could not parse number.");
			System.out.println("  Line: " + csvLine);
			return null;
		} catch (NoSuchElementException e) {
			// Handles cases where columns are missing
			System.out.println("Warning: Skipping invalid gamer line. Missing columns.");
			System.out.println("  Line: " + csvLine);
			return null;
		}
	}

	public String getFilePath() {
		return filePath;
	}
}
