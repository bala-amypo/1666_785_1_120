package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.*;

public interface UserAccountRepository {
    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);
    UserAccount save(UserAccount u);
}
