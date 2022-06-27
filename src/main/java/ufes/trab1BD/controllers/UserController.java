package ufes.trab1BD.controllers;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.models.*;
import ufes.trab1BD.repo.*;


@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Operation(summary = "Retorna todos os usuarios cadastrados")
    @GetMapping("/users/read")
    public List<User> readUsers(){
        return userRepo.readUsers();
    }

    @Operation(summary = "Retorna os dados do usuario com ID especificado")
    @GetMapping("/users/read/{user_id}")
    public ResponseEntity<User> readUserById(@PathVariable int user_id){
        User user = userRepo.readUserById(user_id);
        if(user == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Insere um novo usuario no banco de dados")
    @PostMapping("/users/create")
    public ResponseEntity<String> createUser(String name, String document, String date_of_birth){
        userRepo.createUser(name, document, date_of_birth, 0);

        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @Operation(summary = "Atualiza o saldo do usuario especificado (Valores Positivos inserem, negativos removem)")
    @PutMapping("/users/update/{user_id}/updateBalance/{value}")
    public ResponseEntity<String> updateUserBalance(@PathVariable int user_id, @PathVariable int value){
        User user = userRepo.readUserById(user_id);
        if(user == null) return ResponseEntity.badRequest().body("Usuário não encontrado");

        float newBalance = user.getBalance() + value;
        if(newBalance < 0) return ResponseEntity.badRequest().body("Usuário não pode ficar com saldo negativo");

        userRepo.updateUserBalance(user_id, user.getBalance() + value);

        return ResponseEntity.ok("Saldo atualizado com sucesso");
    }

    @Operation(summary = "Apaga os dados do usuario com ID especificado")
    @DeleteMapping("/users/delete/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable int user_id){
        User user = userRepo.readUserById(user_id);
        if(user == null) return ResponseEntity.badRequest().body("Usuário não existe");

        if(!storeRepo.readStoresByOwner(user_id).isEmpty()) return ResponseEntity.badRequest().body("Não é possível deletar o usuário pois ele é dono de uma ou mais lojas. Caso queira deletá-lo, delete suas lojas primeiro.");

        userRepo.deleteUser(user_id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}
