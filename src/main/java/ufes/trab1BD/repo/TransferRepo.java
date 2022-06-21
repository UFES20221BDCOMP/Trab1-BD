package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufes.trab1BD.modelo.Transfer;



public interface TransferRepo extends JpaRepository<Transfer, Long>{
    @Query(nativeQuery = true, value = 
            "SELECT * " +
            "FROM transfer " +
            "WHERE payee_id = (:user_id) or payer_id = (:user_id)")
    List<Transfer> getTransfersByUserId(int user_id);
}
