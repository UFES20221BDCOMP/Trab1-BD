package ufes.trab1BD.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufes.trab1BD.models.Purchase;

public interface GeneralRepo extends JpaRepository<Purchase, Long>{
    @Query(nativeQuery = true, value = 
            "select Q.user_id, Q.name, sum(balance + stores_balance) as total_balance" +
            "FROM (" +
                "SELECT u.user_id, u.name, u.balance , SUM(s.balance) as stores_balance " + 
                "FROM user u join store s ON u.user_id = s.owner_id " +
                "GROUP BY user_id) as Q" +
            "GROUP BY user_id" +
            "ORDER BY total_balance DESC;"
        )
    String readRichest();

}