import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * GamersFileIo
 */
public class GamersFileIo {

	public GamersFileIo() {
	}

	public Gamer[] getGamers() throws IOException {

		int count = getRowCount();
		Gamer[] gamerArray = new Gamer[count];

		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader("gamers.csv"));

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
			inputStream = new BufferedReader(new FileReader("gamers.csv"));

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
		StringTokenizer tokenizer = new StringTokenizer(csvLine, ",");

		int id = Integer.parseInt(tokenizer.nextToken());
		String nickname = tokenizer.nextToken();
		String name = tokenizer.nextToken();
		String phone = tokenizer.nextToken();
		int exp = Integer.parseInt(tokenizer.nextToken());

		return new Gamer(id, nickname, name, phone, exp);
	}
}
