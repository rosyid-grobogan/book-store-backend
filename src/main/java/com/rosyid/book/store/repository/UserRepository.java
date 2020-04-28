package com.rosyid.book.store.repository;

import com.rosyid.book.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

//    @Query(value = "SELECT username FROM public.users WHERE users.email= ?1 ", nativeQuery = true)
//    User getUsernameByEmail(String email);

}
