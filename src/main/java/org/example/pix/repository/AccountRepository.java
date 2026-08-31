package org.example.pix.repository;

import org.example.pix.model.Account;
import org.example.pix.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByClient(Client client);
}
