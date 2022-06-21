package ufes.trab1BD.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufes.trab1BD.modelo.User;

public interface UserRepo extends JpaRepository<User, Long>{
    @Query(nativeQuery = true, value = 
            "SELECT * " +
            "FROM user " +
            "ORDER BY user_id")
    List<User> getUsers();

    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM user " +
            "WHERE user_id = ?1")
    User getUserById(int user_id);

    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM user " +
            "WHERE name LIKE (%:name%)")
    List<User> getUsersByName(String name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "INSERT INTO user (name, date_of_birth, balance) VALUES " +
            "(:name, :date_of_birth, :balance)")
    void addUser(String name, String date_of_birth, float balance);
}
