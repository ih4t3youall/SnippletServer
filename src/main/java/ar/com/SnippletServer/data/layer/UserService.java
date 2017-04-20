package ar.com.SnippletServer.data.layer;

import ar.com.SnippletServer.domain.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}