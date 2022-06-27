package ufes.trab1BD.repo;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufes.trab1BD.models.Purchase;

public interface GeneralRepo extends JpaRepository<Purchase, Long>{

    @Query(nativeQuery = true, value = 
            "select u.user_id, u.name, sum(balance + stores_balance) as total_balance " +
            "FROM user u JOIN (" +
                "SELECT u.user_id, SUM(s.balance) as stores_balance " + 
                "FROM user u join store s ON u.user_id = s.owner_id " +
                "GROUP BY user_id) as Q " +
            "on u.user_id = Q.user_id " +
            "GROUP BY user_id " +
            "ORDER BY total_balance DESC;")
    List<Object[]> findRichest();

    @Query(nativeQuery = true, value = 
            "SELECT SUM(users_money + stores_money) as total_money " +
            "FROM " +
            "(SELECT SUM(balance) as users_money FROM user) as u " +
            "JOIN " +
            "(SELECT SUM(balance) as stores_money  FROM store) as s")
    Float readMoney();


}