package demo.model;

/**
 * User model
 */
public class User {
	// ユーザID
	private Integer id;

	// ユーザパスワード
	private String password;

	// ユーザ名
	private String userName;

	// 住所
	private String address;

	// ユーザ性別
	private int gender;

	// ユーザメール
	private String email;

	// 電話番号
	private String telNumber;

	/* Construction */
	public User() {

	}

	public User(Integer userId, String password, String userName, String address, int gender, String email,
			String telNumber) {
		super();
		this.id = userId;
		this.password = password;
		this.userName = userName;
		this.address = address;
		this.gender = gender;
		this.email = email;
		this.telNumber = telNumber;
	}

	/**
	 * @return the userId
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param userId
	 */
	public void setId(Integer userId) {
		this.id = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param Gender
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telNumber
	 */
	public String getTelNumber() {
		return telNumber;
	}

	/**
	 * @param telNumber
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

}
