package org.example.pix.repository;

import org.example.pix.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{

    Optional<Transfer> findByTransactionCode(String transactionCode);
}
