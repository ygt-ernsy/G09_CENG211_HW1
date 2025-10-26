import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * GameFileIO
 */
public class GameFileIO {

	private String filePath;

	public GameFileIO(String filePath) {
		this.filePath = filePath;
	}

	public Game[] getGames() throws IOException {

		int count = getRowCount();
		Game[] gameArray = new Game[count];

		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			// Skip the header
			inputStream.readLine();
			for (int i = 0; i < count; i++) {
				String csvLine = inputStream.readLine();
				gameArray[i] = parseGame(csvLine);
			}

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return gameArray;
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

	private Game parseGame(String csvLine) {
		StringTokenizer tokenizer = new StringTokenizer(csvLine, ",");

		int id = Integer.parseInt(tokenizer.nextToken());
		String gameName = tokenizer.nextToken();
		int basePointPerRound = Integer.parseInt(tokenizer.nextToken());

		return new Game(id, gameName, basePointPerRound);
	}
}
