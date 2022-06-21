package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufes.trab1BD.modelo.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase, Long>{
    @Query(nativeQuery = true, value = 
            "SELECT * " +
            "FROM purchase " +
            "WHERE user_id = (:user_id)")
    List<Purchase> getPurchasesByUserId(int user_id);
}
