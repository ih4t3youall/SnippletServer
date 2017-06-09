package ar.com.SnippletServer.data.layer;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import ar.com.SnippletServer.domain.Role;
import ar.com.SnippletServer.domain.User;


@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	
	public User findUserByEmail(String name) {
		return userRepository.findByName(name);
	}

	public void saveUser(User user) {
		user.setPassword(user.getPassword());
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}