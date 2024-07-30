package org.example.repository;
import org.example.entity.User;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Query;

import javax.inject.Inject;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EbeanServer ebeanServer;

    @Inject
    public UserRepositoryImpl(EbeanServer ebeanServer) {
        this.ebeanServer = ebeanServer;
    }

    @Override
    public User findById(Long id) {
        return ebeanServer.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        Query<User> query = ebeanServer.find(User.class);
        return query.findList();
    }

    @Override
    public void save(User user) {
        ebeanServer.save(user);
    }

    @Override
    public void update(User user) {
        ebeanServer.update(user);
    }

    @Override
    public void delete(User user) {
        ebeanServer.delete(user);
    }
    @Override
    public User findByEmail(String email) {
        return ebeanServer.find(User.class).where().eq("email", email).findOne();
    }
}
