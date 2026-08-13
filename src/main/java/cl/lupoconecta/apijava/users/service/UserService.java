package cl.lupoconecta.apijava.users.service;

import cl.lupoconecta.apijava.users.model.User;
import cl.lupoconecta.apijava.users.repository.SpringDataUserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
   * Servicio de usuarios que contiene la logica de negocio relacionada con los usuarios.
   * Este servicio se encarga de procesar las solicitudes relacionadas con los usuarios,
   * como crear un nuevo usuario, obtener la lista de usuarios, etc.
   *
   * El controlador llama a `userService.createUser(user)`
   * Esta capa contiene lógica de negocio (en este caso, delega directo a persistencia)
   */
@Service
public class UserService {
    private final SpringDataUserRepository userRepository;

    public UserService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Obtener todos los usuarios
    // Devuelve una lista de Objetos de Dominio Pure `User`
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    // Devuelve un `Optional<User>` para manejar el caso de "No Encontrado"
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}
