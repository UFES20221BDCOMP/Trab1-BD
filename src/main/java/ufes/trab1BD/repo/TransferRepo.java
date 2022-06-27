package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.Transfer;

public interface TransferRepo extends JpaRepository<Transfer, Long>{
    @Query(nativeQuery = true, value = 
        "SELECT * " +
        "FROM transfer " +
        "ORDER BY transfer_id")
    List<Transfer> readTransfers();

    @Query(nativeQuery = true, value =
        "SELECT * " +
        "FROM transfer " +
        "WHERE transfer_id = (:transfer_id)")
    Transfer readTransferById(int transfer_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "INSERT INTO transfer (payer_id, payee_id, value, date) VALUES (:payer_id, :payee_id, :value, :date)")
    void createTransfer(int payer_id, int payee_id, float value, String date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM transfer " +
        "WHERE transfer_id = (:transfer_id)")
    void cancelTransfer(int transfer_id);
}
