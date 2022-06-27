package ufes.trab1BD.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> readRichest(){
        List<Object[]> rich_list = generalRepo.findRichest();
        Object[] richest = rich_list.get(0); //pega o primeiro da lista

        Map<String, Object> json = new LinkedHashMap();
        json.put("user_id", richest[0]);
        json.put("name", richest[1]);
        json.put("total_balance", richest[2]);

        return json;
    }

    @GetMapping("/Money")
    public Map<String, String> readMoney(){
        Map<String, String> json = new LinkedHashMap();

        json.put("money", generalRepo.readMoney().toString());

        return json;
    }
}
