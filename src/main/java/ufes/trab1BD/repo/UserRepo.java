package ufes.trab1BD.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ufes.trab1BD.modelo.User;


public interface UserRepo extends JpaRepository<User, Long>{
    
}
