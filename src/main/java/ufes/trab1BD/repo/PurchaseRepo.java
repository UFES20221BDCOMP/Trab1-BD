package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase, Long>{
    @Query(nativeQuery = true, value = 
        "SELECT * " +
        "FROM purchase " +
        "ORDER BY purchase_id")
    List<Purchase> readPurchases();

    @Query(nativeQuery = true, value =
        "SELECT * " +
        "FROM purchase " +
        "WHERE purchase_id = (:purchase_id)")
    Purchase readPurchaseById(int purchase_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "INSERT INTO purchase (user_id, store_id, value, date) VALUES (:user_id, :store_id, :value, :date)")
    void createPurchase(int user_id, int store_id, float value, String date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM purchase " +
        "WHERE purchase_id = (:purchase_id)")
    void cancelPurchase(int purchase_id);
}
