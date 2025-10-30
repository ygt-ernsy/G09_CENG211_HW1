import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * GamersFileIo
 */
public class GamersFileIo {

	private String filePath;

	public GamersFileIo() {
		this(null);
	}

	public GamersFileIo(String filePath) {
		this.filePath = filePath;
	}

	public Gamer[] getGamers() throws IOException {

		int count = getRowCount();
		Gamer[] gamerArray = new Gamer[count];

		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			// Skip the header
			inputStream.readLine();
			for (int i = 0; i < count; i++) {
				String csvLine = inputStream.readLine();
				gamerArray[i] = parseGamer(csvLine);
			}

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return gamerArray;
	}

	private int getRowCount() throws IOException {
		BufferedReader inputStream = null;
		int count = 0;

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			// Skip the header
			inputStream.readLine();
			while (inputStream.readLine() != null) {
				count++;
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return count;
	}

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
			System.out.println("Warning: Skipping invalid gamer line. Could not parse number.");
			System.out.println("  Line: " + csvLine);
			return null;
		} catch (NoSuchElementException e) {
			System.out.println("Warning: Skipping invalid gamer line. Missing columns.");
			System.out.println("  Line: " + csvLine);
			return null;
		}
	}
}
