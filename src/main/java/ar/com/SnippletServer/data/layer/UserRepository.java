package ar.com.SnippletServer.data.layer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.SnippletServer.domain.User;



@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByName(String name);
}
