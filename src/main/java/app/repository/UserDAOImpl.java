package app.repository;

import app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User get(Long id) {
        return entityManager.createQuery(
                "SELECT u from User u WHERE u.id = :id", User.class).
                setParameter("id", id).getSingleResult();
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByName(String login) {
        try {
            return entityManager.createQuery("select usr from User usr where usr.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
