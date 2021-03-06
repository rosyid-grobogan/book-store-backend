package com.rosyid.book.store.account.repository;

import com.rosyid.book.store.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    @Query(value = "SELECT u.id FROM public.users u WHERE u.username=?1", nativeQuery = true)
    Long findIdByUsername(String username);
}
