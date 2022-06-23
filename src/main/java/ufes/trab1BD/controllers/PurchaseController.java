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
public class PurchaseController {
    @Autowired
    private PurchaseRepo purchaseRepo;

    @GetMapping("/purchases/read")
    public List<Purchase> readPurchases(){
        return purchaseRepo.readPurchases();
    }

    @GetMapping("/purchases/read/{purchase_id}")
    public Purchase readPurchaseById(@PathVariable int purchase_id){
        return purchaseRepo.readPurchaseById(purchase_id);
    }

    @PostMapping("/purchases/create")
    public void createPurchase(@RequestBody Purchase purchase){
        purchaseRepo.createPurchase(purchase.getUser(), purchase.getStore(), purchase.getValue(), purchase.getDate());
    }

    @PutMapping("/purchases/update/{purchase_id}")
    public void updatePurchase(@PathVariable int purchase_id, @RequestBody Purchase purchase){
        purchaseRepo.updatePurchase(purchase_id, purchase.getUser(), purchase.getStore(), purchase.getValue(), purchase.getDate());
    }

    @DeleteMapping("/purchases/delete/{purchase_id}")
    public void deletePurchase(@PathVariable int purchase_id){
        purchaseRepo.deletePurchase(purchase_id);
    }
}
