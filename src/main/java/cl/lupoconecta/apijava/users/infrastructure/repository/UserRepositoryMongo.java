package cl.lupoconecta.apijava.users.infrastructure.repository;

import cl.lupoconecta.apijava.users.domain.model.User;
import cl.lupoconecta.apijava.users.domain.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
   * Implementa el puerto `UserRepository`.
   * Delegará a un repositorio Spring Data real para Mongo.
   */
@Repository
public class UserRepositoryMongo implements UserRepository {
    private final SpringDataUserRepository repository;

    public UserRepositoryMongo(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}