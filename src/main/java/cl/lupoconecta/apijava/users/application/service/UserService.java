package cl.lupoconecta.apijava.users.application.service;

import cl.lupoconecta.apijava.users.domain.model.User;
import cl.lupoconecta.apijava.users.domain.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final UserRepository userRepository;         

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
