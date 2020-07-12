package app.repository;

import app.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getByName(String login) {
        return (Role) sessionFactory.getCurrentSession().createQuery("from Role where role = :login")
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return sessionFactory.getCurrentSession().createStoredProcedureCall("from Role").getResultList();
    }


    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }
}
