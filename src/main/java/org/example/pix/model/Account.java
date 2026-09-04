package org.example.pix.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Client client;

    @Column(name = "agency")
    private String agency;

    @Column(name = "number_account",nullable = false)
    private String numberAccount;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAccount statusAccount;

    public Account() {
    }

    public Account(Client client, String agency, String numberAccount, BigDecimal balance, StatusAccount statusAccount) {
        this.client = client;
        this.agency = agency;
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.statusAccount = statusAccount;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public StatusAccount getStatusAccount() {
        return statusAccount;
    }
}
