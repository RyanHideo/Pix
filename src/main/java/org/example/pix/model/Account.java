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

    @Column(name = "number_account",nullable = false, unique = true)
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

    public BigDecimal getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public StatusAccount getStatusAccount() {
        return statusAccount;
    }

    public void debitBalance(BigDecimal value){

        if (value == null){
            throw new IllegalArgumentException("O valor deve ser maior que 0");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Nao pode ser debitado um valor negativo");
        }

        if (this.balance.compareTo(value) < 0){
            throw new IllegalArgumentException("Não há saldo suficiente na conta");
        }

        this.balance = this.balance.subtract(value);
    }

    public void creditBalance(BigDecimal value){

        if(value == null){
            throw new IllegalArgumentException("O valor creditado não pode ser nulo");
        }
        if (value.signum() <=0){
            throw new IllegalArgumentException("O valor deve ser maior que 0");
        }
        this.balance = this.balance.add(value);
    }


}
