package user;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
public class SimpleUserAuthenticator implements UserAuthenticator {

	@Override
	public boolean authenticate(String username, String password) {
		if (UserDataBase.getInstance().isUser(username)) {
			if (UserDataBase.getInstance().checkValue(username, password))
				return true;
		}
		return false;
	}
}
