package ufes.trab1BD.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.models.*;
import ufes.trab1BD.repo.*;

@RestController
public class StoreController {
    @Autowired
    private StoreRepo storeRepo;

    @GetMapping("/stores/read")
    public List<Store> readStores(){
        return storeRepo.readStores();
    }

    @GetMapping("/stores/read/{store_id}")
    public Store readStoreById(@PathVariable int store_id){
        return storeRepo.readStoreById(store_id);
    }

    @PostMapping("/stores/create")
    public void createStore(@RequestBody Store store){
        storeRepo.createStore(store.getName(), store.balance, store.getOwner());
    }

    @PutMapping("/stores/update/{store_id}")
    public void updateStore(@PathVariable int store_id, @RequestBody Store store){
        storeRepo.updateStore(store_id, store.getName(), store.getBalance(), store.getOwner());
    }

    @DeleteMapping("/stores/delete/{store_id}")
    public void deleteStore(@PathVariable int store_id){
        storeRepo.deleteStore(store_id);
    }
}
