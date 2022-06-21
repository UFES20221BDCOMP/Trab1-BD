package ufes.trab1BD.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.modelo.*;
import ufes.trab1BD.repo.*;

@RestController
public class ControladoresAPI {

    @Autowired
    private UserRepo userRepo;

    @Autowired 
    private TransferRepo transferRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.getUsers();
    }

    @GetMapping("/users/getById")
    public User getUserById(@RequestParam int user_id){
        return userRepo.getUserById(user_id);
    }
    
    @GetMapping("/users/getByName")
    public List<User> getUsersByName(@RequestParam String name){
        return userRepo.getUsersByName(name);
    }

    @GetMapping("/users/purchases")
    public List<Purchase> getPurchasesByUserId(@RequestParam int user_id){
        return purchaseRepo.getPurchasesByUserId(user_id);
    }

    @GetMapping("/users/transfers")
    public List<Transfer> getTransfersByUserId(@RequestParam int user_id){
        return transferRepo.getTransfersByUserId(user_id);
    }

    @PostMapping("/users/new")
    public void getNewUser(@RequestBody User user){
        userRepo.addUser(user.getName(), user.getDate_of_birth(), user.getBalance());
    }
}
