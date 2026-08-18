package model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_client", unique = true, nullable = false)
    private Long idClient;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "status")
    private Status status;

}
