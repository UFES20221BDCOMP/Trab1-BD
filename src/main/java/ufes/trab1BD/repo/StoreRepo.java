package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.Store;
import ufes.trab1BD.models.User;

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

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "INSERT INTO store (name, balance, owner) VALUES (:name, :balance, :owner)")
    void createStore(String name, float balance, User owner);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = 
        "UPDATE store " +
        "SET " +
        "name = (:name), " +
        "balance = (:balance), " +
        "owner = (:owner) " +
        "WHERE store_id = (:store_id)")
    void updateStore(int store_id, String name, float balance, User owner);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM store " +
        "WHERE store_id = (:store_id)")
    void deleteStore(int store_id);
}
