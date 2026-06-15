package cl.lupoconecta.apijava.users.domain.port;

import cl.lupoconecta.apijava.users.domain.model.User;

import java.util.List;
import java.util.Optional;
/**
   * Es una interfaz que expone métodos estándar para persistencia de usuarios (save, find, etc).
   * Permite desacoplar la lógica de negocio de la infraestructura.
   */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(String id);

    List<User> findAll();
}