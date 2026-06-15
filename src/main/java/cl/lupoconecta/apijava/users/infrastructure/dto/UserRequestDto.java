package cl.lupoconecta.apijava.users.infrastructure.dto;


import jakarta.validation.constraints.*;
/**
   * Dto para recibir los datos de un nuevo usuario desde el cliente. 
   * Este dto se utiliza para mapear los datos de la solicitud a un 
   * objeto que pueda ser procesado por el servicio de usuarios.
   */
public class UserRequestDto {
    @NotBlank(message="El nombre es obligatorio")
    private String name;
    @Email(message="El email debe ser valido")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}