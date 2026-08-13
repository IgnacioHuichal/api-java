package cl.lupoconecta.apijava.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.lupoconecta.apijava.users.model.User;
/**
   * Extiende `MongoRepository<User, String>`
   * Esto le da acceso a métodos CRUD básicos sin necesidad de implementarlos.
   * Spring Data MongoDB se encargará de generar la implementación en tiempo de ejecución.
   * Spring Boot provee la implementación real de CRUD sobre Mongo para la entidad User.
   */
public interface SpringDataUserRepository extends MongoRepository<User, String> {
}