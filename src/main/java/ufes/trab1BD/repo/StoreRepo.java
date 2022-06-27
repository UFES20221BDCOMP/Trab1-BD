package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.Store;

public interface StoreRepo extends JpaRepository<Store, Long>{
    @Query(nativeQuery = true, value = 
        "SELECT * " +
        "FROM store " +
        "ORDER BY store_id")
    List<Store> readStores();

    @Query(nativeQuery = true, value =
        "SELECT * " +
        "FROM store " +
        "WHERE store_id = (:store_id)")
    Store readStoreById(int store_id);

    @Query(nativeQuery = true, value =
        "SELECT * " +
        "FROM store " +
        "WHERE owner_id = (:owner_id)")
    List<Store> readStoresByOwner(int owner_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "INSERT INTO store (name, balance, owner_id) VALUES (:name, :balance, :owner_id)")
    void createStore(String name, float balance, int owner_id);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = 
        "UPDATE store " +
        "SET " +
        "balance = (:balance) " +
        "WHERE store_id = (:store_id)")
    void updateStoreBalance(int store_id, float balance);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM store " +
        "WHERE store_id = (:store_id)")
    void deleteStore(int store_id);
}
