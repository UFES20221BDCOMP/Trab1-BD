package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.Purchase;
import ufes.trab1BD.models.Store;
import ufes.trab1BD.models.User;

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
        "INSERT INTO purchase (user, store, value, date) VALUES (:user, :store, :value, :date)")
    void createPurchase(User user, Store store, float value, String date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = 
        "UPDATE purchase " +
        "SET " +
        "user = (:user), " +
        "store = (:store), " +
        "value = (:value), " +
        "date = (:date) " +
        "WHERE purchase_id = (:purchase_id)")
    void updatePurchase(int purchase_id, User user, Store store, float value, String date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM purchase " +
        "WHERE purchase_id = (:purchase_id)")
    void deletePurchase(int purchase_id);
}
