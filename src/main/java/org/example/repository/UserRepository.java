package org.example.repository;
import org.example.entity.User;

import java.util.List;

public interface UserRepository {

    User findById(Long id);

    List<User> findAll();

    void save(User user);

    void update(User user);

    void delete(User user);
    User findByEmail(String email);
}

