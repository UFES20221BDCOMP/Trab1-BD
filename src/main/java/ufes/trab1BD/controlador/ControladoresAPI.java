package ufes.trab1BD.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.modelo.*;
import ufes.trab1BD.repo.*;

@RestController
public class ControladoresAPI {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
}
