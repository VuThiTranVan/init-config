package demo.bean;

public class ConditionSearchUser {
	private String userName;
	private String gender;
	private String address;

	public ConditionSearchUser(String userName, String gender, String address) {

		this.userName = userName;
		this.gender = gender;
		this.address = address;
	}

	public ConditionSearchUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
