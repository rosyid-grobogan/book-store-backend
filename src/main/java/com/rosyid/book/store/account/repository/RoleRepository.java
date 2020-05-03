package com.rosyid.book.store.account.repository;

import com.rosyid.book.store.account.entity.EnumRole;
import com.rosyid.book.store.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(EnumRole name);
}
