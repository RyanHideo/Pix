package org.example.pix.repository;

import org.example.pix.model.Account;
import org.example.pix.model.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKey, Long> {
    Optional<PixKey> findByPixKey(String pixKey);
}
