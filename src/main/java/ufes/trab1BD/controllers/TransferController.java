package ufes.trab1BD.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufes.trab1BD.models.*;
import ufes.trab1BD.repo.*;

@RestController
public class TransferController {
    @Autowired 
    private TransferRepo transferRepo;

    @GetMapping("/transfers/read")
    public List<Transfer> readTransfers(){
        return transferRepo.readTransfers();
    }

    @GetMapping("/transfers/read/{transfer_id}")
    public Transfer readTransferById(@PathVariable int transfer_id){
        return transferRepo.readTransferById(transfer_id);
    }

    @PostMapping("/transfers/create")
    public void createTransfer(@RequestBody Transfer transfer){
        transferRepo.createTransfer(transfer.getPayer(), transfer.getPayee(), transfer.getValue(), transfer.getDate());
    }

    @PutMapping("/transfers/update/{transfer_id}")
    public void updateTransfer(@PathVariable int transfer_id, @RequestBody Transfer transfer){
        transferRepo.updateTransfer(transfer_id, transfer.getPayer(), transfer.getPayee(), transfer.getValue(), transfer.getDate());
    }

    @DeleteMapping("/transfers/delete/{transfer_id}")
    public void deleteTransfer(@PathVariable int transfer_id){
        transferRepo.deleteTransfer(transfer_id);
    }
}
