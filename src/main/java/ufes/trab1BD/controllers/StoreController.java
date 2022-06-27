package ufes.trab1BD.controllers;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.models.*;
import ufes.trab1BD.repo.*;

@RestController
public class StoreController {
    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private UserRepo userRepo;

    @Operation(summary = "Retorna todas as lojas cadastradas")
    @GetMapping("/stores/read")
    public List<Store> readStores(){
        return storeRepo.readStores();
    }

    @Operation(summary = "Retorna os dados da loja com ID especificado")
    @GetMapping("/stores/read/{store_id}")
    public ResponseEntity<Store> readStoreById(@PathVariable int store_id){
        Store store = storeRepo.readStoreById(store_id);
        if(store == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(store);
    }

    @GetMapping("/stores/readByOwner/{owner_id}")
    public ResponseEntity<List<Store>> readStoresByOwner(@PathVariable int owner_id){
        List<Store> lojas = storeRepo.readStoresByOwner(owner_id);
        return ResponseEntity.ok().body(lojas);
    }

    @Operation(summary = "Insere uma nova loja no banco de dados")
    @PostMapping("/stores/create")
    public ResponseEntity<String> createStore(String name, int owner_id){
        User user = userRepo.readUserById(owner_id);
        if(user == null) return ResponseEntity.badRequest().body("Usuário dono da loja não existente");
        storeRepo.createStore(name, 0, owner_id);
        
        return ResponseEntity.ok().body("Loja criada com sucesso");
    }

    @Operation(summary = "Apaga os dados da loja com ID especificado")
    @DeleteMapping("/stores/delete/{store_id}")
    public ResponseEntity<String> deleteStore(@PathVariable int store_id){
        Store store = storeRepo.readStoreById(store_id);
        
        if(store == null) return ResponseEntity.badRequest().body("Loja não existe");
        storeRepo.deleteStore(store_id);

        return ResponseEntity.ok("Loja deletada com sucesso");
    }
}
