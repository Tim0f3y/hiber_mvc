package app.repository;

import app.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User",User.class).list();
    }

    @Override
    public void deleteUser(Long id) {
        sessionFactory.getCurrentSession().createQuery("delete from User where id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User get(Long id) {
        List<User> user = sessionFactory.getCurrentSession().createQuery("from User where id=:id")
                .setParameter("id", id).list();
        return (!user.isEmpty()) ? user.get(0) : null;
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().createQuery("delete from User where id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
