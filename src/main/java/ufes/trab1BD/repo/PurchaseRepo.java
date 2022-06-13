package ufes.trab1BD.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ufes.trab1BD.modelo.Purchase;


public interface PurchaseRepo extends JpaRepository<Purchase, Long>{
    
}
