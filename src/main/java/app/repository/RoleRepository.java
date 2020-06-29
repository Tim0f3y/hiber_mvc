package app.repository;

import app.entity.Role;

import java.util.List;

public interface RoleRepository {
    Role getByName(String name);
    List<Role> getAll();
    public void addRole(Role role);
}
