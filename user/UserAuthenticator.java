package user;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
public interface UserAuthenticator {

	boolean authenticate(String username, String password);
}
