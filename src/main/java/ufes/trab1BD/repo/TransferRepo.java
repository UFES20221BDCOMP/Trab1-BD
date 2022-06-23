package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.Transfer;
import ufes.trab1BD.models.User;

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
        "INSERT INTO transfer (payer, payee, value, date) VALUES (:payer, :payee, :value, :date)")
    void createTransfer(User payer, User payee, float value, String date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = 
        "UPDATE transfer " +
        "SET " +
        "payer = (:payer), " +
        "payee = (:payee), " +
        "value = (:value), " +
        "date = (:date) " +
        "WHERE transfer_id = (:transfer_id)")
    void updateTransfer(int transfer_id, User payer, User payee, float value, String date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM transfer " +
        "WHERE transfer_id = (:transfer_id)")
    void deleteTransfer(int transfer_id);
}
