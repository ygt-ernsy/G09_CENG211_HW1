/**
 * Gamer
 */
public class Gamer {
	private int id;
	private String nickname;
	private String name;
	private String phoneNumber;
	private int experianceYears;

	public Gamer() {
		this(0, null, null, null, 0);
	}

	// Copy constructor
	public Gamer(Gamer gamer) {
		this(gamer.getId(), gamer.getNickname(), gamer.getName(), gamer.getPhoneNumber(),
				gamer.getExperianceYears());
	}

	public Gamer(int id, String nickname, String name, String phoneNumber, int experianceYears) {
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.experianceYears = experianceYears;
	}

	public int getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getExperianceYears() {
		return experianceYears;
	}

	@Override
	public String toString() {
		return "Id: " + id + " Nickname: " + nickname + " Name: " + " PhoneNumber: " + " ExperianceYears: "
				+ experianceYears;
	}
}
