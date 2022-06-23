package ufes.trab1BD.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.models.*;
import ufes.trab1BD.repo.*;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Operation(summary = "Retorna todos os usuarios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Todos os usuarios retornados com sucesso",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
            description = "Erro ao retornar usuarios",
            content = @Content)
    })
    @GetMapping("/users/read")
    public List<User> readUsers(){
        return userRepo.readUsers();
    }

    @GetMapping("/users/read/{user_id}")
    public User readUserById(@PathVariable int user_id){
        return userRepo.readUserById(user_id);
    }

    @PostMapping("/users/create")
    public void createUser(@RequestBody User user){
        userRepo.createUser(user.getName(), user.getDate_of_birth(), user.getBalance());
    }

    @PutMapping("/users/update/{user_id}")
    public void updateUser(@PathVariable int user_id, @RequestBody User user){
        userRepo.updateUser(user_id, user.getName(), user.getDate_of_birth(), user.getBalance());
    }

    @DeleteMapping("/users/delete/{user_id}")
    public void deleteUser(@PathVariable int user_id){
        userRepo.deleteUser(user_id);
    }
}
