package user;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.HashMap;

public class UserDataBase {

	private HashMap<String, String> passwords;
	private static UserDataBase dataBase;

	private UserDataBase() {
		this.passwords = new HashMap<String, String>();
	}

	public synchronized static UserDataBase getInstance() {
		if (dataBase == null)
			dataBase = new UserDataBase();
		return dataBase;
	}

	public void addUser(String user, String password) {
		passwords.put(user, password);
	}

	public boolean isUser(String userName) {
		return passwords.containsKey(userName);
	}

	public boolean checkValue(String user, String pass) {
		if (passwords.get(user).compareTo(pass) == 0)
			return true;
		else
			return false;

	}
}
