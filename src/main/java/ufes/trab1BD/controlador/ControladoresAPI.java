package ufes.trab1BD.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladoresAPI {
    @GetMapping("/")
    public String getHelloWorld(){
        return "Hello World!";
    }
}
