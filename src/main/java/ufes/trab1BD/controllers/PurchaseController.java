package ufes.trab1BD.controllers;

import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
public class PurchaseController {
    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Operation(summary = "Retorna todas as compras realizadas")
    @GetMapping("/purchases/read")
    public List<Purchase> readPurchases(){
        return purchaseRepo.readPurchases();
    }

    @Operation(summary = "Retorna os dados da compra com ID especificado")
    @GetMapping("/purchases/read/{purchase_id}")
    public ResponseEntity<Purchase> readPurchaseById(@PathVariable int purchase_id){
        Purchase purchase = purchaseRepo.readPurchaseById(purchase_id);
        if(purchase == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(purchase);
    }

    @Operation(summary = "Insere uma nova compra no banco de dados")
    @PostMapping("/purchases/create")
    public ResponseEntity<String> createPurchase(int user_id, int store_id, int value){        
        User user = userRepo.readUserById(user_id);
        Store store = storeRepo.readStoreById(store_id);

        if(user != null && store != null){
            if(user.getBalance() < value){ // Saldo insuficiente
                return ResponseEntity.badRequest().body("Saldo insuficiente");
            }else{
                userRepo.updateUserBalance(user_id, user.getBalance() - value);
                storeRepo.updateStoreBalance(store_id, store.getBalance() + value);
            }
        }else if(user == null){
            return ResponseEntity.badRequest().body("Usuário não existente");
        }else if(store == null){
            return ResponseEntity.badRequest().body("Loja não existente");
        }else{
            return ResponseEntity.badRequest().body("Usuário e loja não existem");
        }
        
        LocalDate localDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        LocalTime localTime = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
        String date = localDate.toString() + " " + localTime.toString().substring(0, 8);
        
        purchaseRepo.createPurchase(user_id, store_id, value, date);
        return ResponseEntity.ok("Compra realizada com sucesso");
    }

    @Operation(summary = "Cancela, se possivel, uma compra realizada")
    @DeleteMapping("/purchase/cancel/{purchase_id}")
    public ResponseEntity<String> cancelPurchase(@PathVariable int purchase_id){
        Purchase purchase = purchaseRepo.readPurchaseById(purchase_id);
        if(purchase == null) return ResponseEntity.badRequest().body("Compra não existente");

        User  user  = purchase.getUser();
        Store store = purchase.getStore();
        float value = purchase.getValue();

        if(store.getBalance() < value) return ResponseEntity.badRequest().body("Impossível cancelar essa compra, pois a loja envolvida não possui saldo suficiente para retornar o valor ao usuário pagador");
        
        userRepo.updateUserBalance(user.getUser_id(), user.getBalance() + value);
        storeRepo.updateStoreBalance(store.getStore_id(), store.getBalance() - value);

        purchaseRepo.cancelPurchase(purchase_id);
        return ResponseEntity.ok().body("Compra cancelada com sucesso");
    }
}
