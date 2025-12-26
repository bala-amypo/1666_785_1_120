package com.example.demo.repository;

import java.util.Optional;
import com.example.demo.entity.UserAccount;

public interface UserAccountRepository {
    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);
    UserAccount save(UserAccount user);
}
