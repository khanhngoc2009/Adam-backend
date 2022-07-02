package com.example.adambackend.repository;

import com.example.adambackend.entities.Account;
import com.example.adambackend.payload.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    @Query(value = "select * from accounts where phone_number=?1",nativeQuery = true)
  Optional<Account> findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from Account a where a.roleName=?1", nativeQuery = true)
    List<Account> findByRoleName(String role);

    @Query("SELECT a FROM Account a WHERE a.verificationCode = ?1")
    public Account findByVerificationCode(String code);

    @Query(value = "select id as id, username as username, full_name as fullName, email as email, phone_number as phoneNumber, password as password, " +
            "role as role, is_active as isActive, is_deleted as isDeleted, priority as priority from accounts", nativeQuery = true)
    public List<AccountResponse> findAlls();

}
