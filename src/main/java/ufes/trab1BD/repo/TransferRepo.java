package ufes.trab1BD.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ufes.trab1BD.modelo.Transfer;


public interface TransferRepo extends JpaRepository<Transfer, Long>{
    
}
