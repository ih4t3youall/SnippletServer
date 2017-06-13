package ar.com.SnippletServer.data.layer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.SnippletServer.domain.Role;

@Repository("snippletRepository")
public interface SnippletRepository  extends JpaRepository<Role, Integer>{

	
}
