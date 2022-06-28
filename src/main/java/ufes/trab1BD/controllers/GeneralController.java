package ufes.trab1BD.controllers;

import io.swagger.v3.oas.annotations.Operation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.repo.GeneralRepo;

@RestController
public class GeneralController {
    @Autowired
    private GeneralRepo generalRepo;
    
    @Operation(summary = "Retorna um link para a documentação da API")
    @GetMapping("/")
    public String redirectSwagger(){
        String myURL = "<HTML><body> <a href=\"http://localhost:8080/swagger-ui/index.html\">Swagger Page</a></body></HTML>";

        return myURL;
    }

    @Operation(summary = "Retorna o usuário mais rico do sistema")
    @GetMapping("/richest")
    public Map<String, Object> readRichest(){
        List<Object[]> rich_list = generalRepo.findRichest();
        Object[] richest = rich_list.get(0); //pega o primeiro da lista

        Map<String, Object> json = new LinkedHashMap();
        json.put("user_id", richest[0]);
        json.put("name", richest[1]);
        json.put("total_balance", richest[2]);

        return json;
    }

    @Operation(summary = "Retorna a quantidade total de dinheiro do sistema")
    @GetMapping("/money")
    public Map<String, String> readMoney(){
        Map<String, String> json = new LinkedHashMap();

        json.put("money", generalRepo.readMoney().toString());

        return json;
    }
}
