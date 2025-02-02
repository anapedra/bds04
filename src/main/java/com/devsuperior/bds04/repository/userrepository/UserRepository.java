package com.devsuperior.bds04.repository.userrepository;

import com.devsuperior.bds04.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String name);
}
