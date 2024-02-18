package base;

public class users 
{
	private String name;
	private String mail;
	private String role;
	private String diplayName;
	private String password;
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public users(String name, String mail, String role, String diplayName, String password, String mobile) {
		super();
		this.name = name;
		this.mail = mail;
		this.role = role;
		this.diplayName = diplayName;
		this.password = password;
		this.mobile = mobile;
	}
	public users() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDiplayName() {
		return diplayName;
	}
	public void setDiplayName(String diplayName) {
		this.diplayName = diplayName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
