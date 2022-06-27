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
public class TransferController {
    @Autowired 
    private TransferRepo transferRepo;

    @Autowired
    private UserRepo userRepo;

    @Operation(summary = "Retorna todas as transferencias realizadas")
    @GetMapping("/transfers/read")
    public List<Transfer> readTransfers(){
        return transferRepo.readTransfers();
    }
    
    @Operation(summary = "Retorna os dados da transferencia com ID especificado")
    @GetMapping("/transfers/read/{transfer_id}")
    public ResponseEntity<Transfer> readTransferById(@PathVariable int transfer_id){
        Transfer transfer = transferRepo.readTransferById(transfer_id);
        if(transfer == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(transfer);
    }

    @Operation(summary = "Insere uma nova transferencia no banco de dados")
    @PostMapping("/transfers/create")
    public ResponseEntity<String> createTransfer(int payer_id, int payee_id, int value){
        User payer = userRepo.readUserById(payer_id);
        User payee = userRepo.readUserById(payee_id);

        if(payer != null && payee != null){
            if(payer.getBalance() < value){ // Saldo insuficiente
                return ResponseEntity.badRequest().body("Saldo insuficiente");
            }else{
                userRepo.updateUserBalance(payer_id, payer.getBalance() - value);
                userRepo.updateUserBalance(payee_id, payee.getBalance() + value);
            }
        }else if(payee == null){
            return ResponseEntity.badRequest().body("Usuário beneficiado não existente");
        }else if(payer == null){
            return ResponseEntity.badRequest().body("Usuário pagador não existente");
        }else{
            return ResponseEntity.badRequest().body("Usuários da transação não existem");
        }
        
        LocalDate localDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        LocalTime localTime = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
        String date = localDate.toString() + " " + localTime.toString().substring(0, 8);
        System.out.println(date);
        
        transferRepo.createTransfer(payer_id, payee_id, value, date);
        return ResponseEntity.ok("Transferência realizada com sucesso");

    }

    @Operation(summary = "Cancela, se possivel, uma transferencia realizada")
    @DeleteMapping("/transfers/cancel/{transfer_id}")
    public ResponseEntity<String> cancelTransfer(@PathVariable int transfer_id){
        Transfer transfer = transferRepo.readTransferById(transfer_id);
        if(transfer == null) return ResponseEntity.badRequest().body("Transferência não existe");

        User payer = transfer.getPayer();
        User payee = transfer.getPayee();
        float value = transfer.getValue();

        if(payee.getBalance() < value) return ResponseEntity.badRequest().body("Impossível cancelar essa trasferência, pois o usuário beneficiado não possui saldo suficiente para retornar o valor ao usuário pagador");

        userRepo.updateUserBalance(payer.getUser_id(), payer.getBalance() + value);
        userRepo.updateUserBalance(payee.getUser_id(), payee.getBalance() - value);
        
        transferRepo.cancelTransfer(transfer_id);
        return ResponseEntity.ok().body("Transferência cancelada com sucesso");
    }
}
