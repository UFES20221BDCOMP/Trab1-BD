package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.models.User;

public interface UserRepo extends JpaRepository<User, Long>{
    @Query(nativeQuery = true, value = 
        "SELECT * " +
        "FROM user " +
        "ORDER BY user_id")
    List<User> readUsers();

    @Query(nativeQuery = true, value =
        "SELECT * " +
        "FROM user " +
        "WHERE user_id = (:user_id)")
    User readUserById(int user_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "INSERT INTO user (name, document, date_of_birth, balance) VALUES (:name, :document, :date_of_birth, :balance)")
    void createUser(String name, String document, String date_of_birth, float balance);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = 
        "UPDATE user " +
        "SET " +
        "balance = (:balance) " +
        "WHERE user_id = (:user_id)")
    void updateUserBalance(int user_id, float balance);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
        "DELETE " +
        "FROM user " +
        "WHERE user_id = (:user_id)")
    void deleteUser(int user_id);
}
