package ex3;

public interface UserDao {	
	public boolean addUser(User user);

	public User getUser(String userName,String password);
	
}
