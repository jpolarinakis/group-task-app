package refactoredGTA;

public class User {
	private String username;
	private String password;
	private String email;
	private int userID;
	
	public User(String username, String password, String email, int userID)
	{
		this.email = email;
		this.userID = userID;
		this.password = password;
		this.username = username;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	public int getID(){
		return userID;
	}
	
}

