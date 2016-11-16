package ar.com.SnippletServer.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.SnippletServer.domain.User;

@Repository
@Transactional
public class UserDAO {

	@Autowired
	private SessionFactory _sessionFactory;

	public Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(User user) {
		getSession().persist(user);
		return;
	}

	public void delete(User user) {
		getSession().delete(user);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getById(long id) {
		return (User) getSession().load(User.class, id);
	}

	public void update(User user) {
		getSession().update(user);
		return;
	}

	public User getUser(String name) {

		System.out.println("esto muestroe en la base de datos, nombre: "+name);
		return (User) getSession().createQuery("from User where name = :name").setParameter("name", name).uniqueResult();
		

	}

} // class UserDao
