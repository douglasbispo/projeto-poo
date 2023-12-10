package entities.user;

public class User {
	private static Integer count = 1;
	
	private Integer idUser;
	private String email;
	private String password;
	
	public User() {}

	public User(String email, String password) {
		this.idUser = User.count;
		this.email = email;
		this.password = password;
		User.count += 1;
	}

	public Integer getIdUser() {
		return idUser;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getIdUser() + 
				"\nE-mail: " + this.getEmail() + 
				"\nSenha: " + this.getPassword() + "\n";
	}	
}
