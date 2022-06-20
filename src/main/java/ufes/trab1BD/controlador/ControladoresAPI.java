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

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public List<User> getSpecificUser(int id){
        List<User> usuarios = userRepo.findAll();
        System.out.println(usuarios);

        return userRepo.findAll();
    }
    
}
