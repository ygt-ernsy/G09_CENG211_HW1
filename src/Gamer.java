/**
 * Gamer
 * 
 * Represents a gamer/user with identifying information and experience details.
 */
public class Gamer {
	private int id; // Unique identifier for the gamer
	private String nickname; // Gamer's nickname
	private String name; // Gamer's real name
	private String phoneNumber; // Gamer's phone number
	private int experianceYears; // Number of years of experience

	/**
	 * Default constructor initializes fields to default values.
	 */
	public Gamer() {
		this(0, null, null, null, 0);
	}

	/**
	 * Copy constructor creates a new Gamer from another Gamer object.
	 * 
	 * @param gamer The gamer instance to copy
	 */
	public Gamer(Gamer gamer) {
		this(gamer.getId(), gamer.getNickname(), gamer.getName(), gamer.getPhoneNumber(),
				gamer.getExperianceYears());
	}

	/**
	 * Constructs a Gamer with all fields specified.
	 * 
	 * @param id              Unique identifier for the gamer
	 * @param nickname        Gamer's nickname
	 * @param name            Gamer's real name
	 * @param phoneNumber     Gamer's phone number
	 * @param experianceYears Number of years of experience
	 */
	public Gamer(int id, String nickname, String name, String phoneNumber, int experianceYears) {
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.experianceYears = experianceYears;
	}

	/**
	 * @return The gamer's unique identifier
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return The gamer's nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return The gamer's real name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The gamer's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return The number of years of experience
	 */
	public int getExperianceYears() {
		return experianceYears;
	}

	/**
	 * Returns a string representation of the Gamer object.
	 */
	@Override
	public String toString() {
		return "Id: " + id + " Nickname: " + nickname + " Name: " + name + " PhoneNumber: " + phoneNumber +
				" ExperianceYears: " + experianceYears;
	}
}
