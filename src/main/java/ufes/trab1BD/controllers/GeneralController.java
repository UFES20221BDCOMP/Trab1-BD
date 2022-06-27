package ufes.trab1BD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ufes.trab1BD.repo.GeneralRepo;
//import ufes.trab1BD.repo.UserRepo;

@RestController
public class GeneralController {
    @Autowired
    private GeneralRepo generalRepo;

    // @Autowired
    // private UserRepo userRepo;
    
    // @Autowired
    // private StoreRepo storeRepo;
    
    // @Autowired
    // private PurchaseRepo purchaseRepo;
    
    // @Autowired 
    // private TransferRepo transferRepo;

    
    @GetMapping("/")
    public String redirectSwagger(){
        String myURL = "<HTML><body> <a href=\"http://localhost:8080/swagger-ui/index.html\">Swagger Page</a></body></HTML>";

        return myURL;
    }

    @GetMapping("/Richest")
    public String readRichest(){
        return generalRepo.readRichest();
    }

    @GetMapping("/Money")
    public float readMoney(){
        //criar
        //Float value = generalRepo.getMoney()//
        return 0; //return value;
    }
}
